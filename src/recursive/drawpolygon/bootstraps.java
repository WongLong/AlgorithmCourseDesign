package recursive.drawpolygon;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import recursive.drawpolygon.entity.RegularPolygonPane;

public class bootstraps extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Label label1 = new Label("边条数:");
		TextField numOfSides = new TextField();
		numOfSides.setPromptText("请输入边的条数");
		Label label2 = new Label("边长度:");
		TextField lenOfSides = new TextField();
		lenOfSides.setPromptText("请输入边的长度");
		Label label3 = new Label("比例:");
		TextField ratio = new TextField();
		ratio.setPromptText("请输入比例");
		Button ensure = new Button("确定");

		HBox head = new HBox();
		head.setSpacing(20);
		head.getChildren().addAll(label1, numOfSides, label2, lenOfSides, label3, ratio,  ensure);

		GridPane drawBody = new GridPane();
		drawBody.setAlignment(Pos.CENTER);
		drawBody.setPrefHeight(800);

		VBox main = new VBox();
		main.getChildren().addAll(head, drawBody);

		Scene scene = new Scene(main);
		primaryStage.setScene(scene);
		primaryStage.show();

		ensure.setOnAction(e -> {
			try {
				drawBody.getChildren().clear();

				Integer num_Sides = Integer.parseInt(numOfSides.getText());
				Integer len_Sides = Integer.parseInt(lenOfSides.getText());
				Double r = Double.parseDouble(ratio.getText());
				
				if(r > 1) return;

				RegularPolygonPane polygonPane = new RegularPolygonPane(num_Sides, len_Sides, r);
				drawBody.getChildren().add(polygonPane);
				primaryStage.setTitle("正" + num_Sides + "边形");
				drawBody.setPrefHeight(polygonPane.getPrefHeight());
				primaryStage.close();
				primaryStage.show();
			} catch (NumberFormatException exception) {
				return;
			}
		});
	}
}
