package backtrack.sudoku.GUI;

import java.util.List;

import backtrack.sudoku.GUI.entity.SpaceLabel;
import backtrack.sudoku.GUI.entity.SudokuPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class bootstrap extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox head = new HBox();
		Button findSolution = new Button("找出数独解");
		Button reset = new Button("重置");
		head.getChildren().addAll(findSolution, new SpaceLabel(), reset);
		head.setPadding(new Insets(10, 20, 10, 20));

		SudokuPane pane = new SudokuPane();

		VBox main = new VBox();
		main.getChildren().addAll(head, pane);

		Scene scene = new Scene(main);
		primaryStage.setScene(scene);
		primaryStage.setTitle("数独问题找解");
		primaryStage.setResizable(false);
		primaryStage.show();

		findSolution.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int[][] oldSudoku = pane.getSudoku();
				List<int[][]> result = pane.findSudokuSolve();
				if (result.size() == 0) {
					Alert alert = new Alert(AlertType.WARNING, "该数独无解", ButtonType.YES);
					alert.showAndWait();

					return;
				}

				int page = 0;
				BorderPane head = new BorderPane();
				head.setPadding(new Insets(10, 20, 10, 20));
				Button last = new Button("上一个");
				Label label = new Label((page + 1) + "\t/\t" + result.size());
				Button next = new Button("下一个");
				head.setLeft(last);
				head.setRight(next);
				head.setCenter(label);

				Pane pane = new Pane();
				SudokuPane sudoku = new SudokuPane(result.get(page), oldSudoku);
				pane.getChildren().add(sudoku);

				VBox main = new VBox();
				main.getChildren().addAll(head, pane);

				Scene scene = new Scene(main);

				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setTitle("数独所有解");
				stage.setResizable(false);
				stage.show();

				last.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						String[] array = label.getText().split("\t");
						Integer page = Integer.parseInt(array[0]) - 1;
						if (page == 0) {
							Alert alert = new Alert(AlertType.ERROR, "当前页数为第一页", ButtonType.YES);
							alert.showAndWait();

							return;
						}

						page--;
						pane.getChildren().clear();
						pane.getChildren().add(new SudokuPane(result.get(page), oldSudoku));
						label.setText((page + 1) + "\t/\t" + result.size());
					}
				});

				next.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						String[] array = label.getText().split("\t");
						Integer page = Integer.parseInt(array[0]) - 1;
						if (page == result.size() - 1) {
							Alert alert = new Alert(AlertType.ERROR, "当前页数为最后一页", ButtonType.YES);
							alert.showAndWait();

							return;
						}
						
						page++;
						pane.getChildren().clear();
						pane.getChildren().add(new SudokuPane(result.get(page), oldSudoku));
						label.setText((page + 1) + "\t/\t" + result.size());
					}
				});
			}
		});

		reset.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				pane.reset();
			}
		});
	}

}
