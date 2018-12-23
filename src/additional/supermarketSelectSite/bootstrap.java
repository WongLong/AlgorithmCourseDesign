package additional.supermarketSelectSite;

import additional.supermarketSelectSite.entity.GraphPane;
import additional.supermarketSelectSite.entity.SpaceLabel;
import additional.supermarketSelectSite.entity.Util;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class bootstrap extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		final ScrollPane sp = new ScrollPane();
		sp.setPrefSize(1000, 800);

		GraphPane<String> gPane = new GraphPane<>(false);
		gPane.setMinSize(1000, 800);
		sp.setContent(gPane);

		Label label = new Label("开始顶点:");
		label.setPrefHeight(40);
		final TextField text = new TextField();
		BorderPane bp1 = new BorderPane();
		bp1.setPrefHeight(40);
		bp1.setCenter(text);

		Button dfs = new Button("展示深度优先遍历树");
		BorderPane bp2 = new BorderPane();
		bp2.setPrefHeight(40);
		bp2.setCenter(dfs);

		Button bfs = new Button("展示广度优先遍历树");
		BorderPane bp3 = new BorderPane();
		bp3.setPrefHeight(40);
		bp3.setCenter(bfs);

		final RadioButton radioBt = new RadioButton("带权图");
		BorderPane bp4 = new BorderPane();
		bp4.setPrefHeight(40);
		bp4.setCenter(radioBt);

		HBox bottom = new HBox();
		bottom.getChildren().addAll(label, new SpaceLabel(), bp1, new SpaceLabel(), bp2, new SpaceLabel(), bp3,
				new SpaceLabel(), bp4);

		final VBox body = new VBox();
		body.getChildren().addAll(sp, bottom);

		// 带权图的部分面板
		Label start = new Label("开始顶点:");
		start.setPrefHeight(40);
		final TextField startInput = new TextField();
		BorderPane bp5 = new BorderPane();
		bp5.setPrefHeight(40);
		bp5.setCenter(startInput);

		Label end = new Label("结束顶点:");
		end.setPrefHeight(40);
		final TextField endInput = new TextField();
		BorderPane bp6 = new BorderPane();
		bp6.setPrefHeight(40);
		bp6.setCenter(endInput);

		Button displayPath = new Button("展示最短路径");
		BorderPane bp7 = new BorderPane();
		bp7.setPrefHeight(40);
		bp7.setCenter(displayPath);

		Button displayMST = new Button("展示最小生成树");
		BorderPane bp8 = new BorderPane();
		bp8.setPrefHeight(40);
		bp8.setCenter(displayMST);

		Button findSite = new Button("寻找超市地址");
		BorderPane bp9 = new BorderPane();
		bp9.setPrefHeight(40);
		bp9.setCenter(findSite);

		HBox weightGraphPart = new HBox();
		weightGraphPart.getChildren().addAll(start, new SpaceLabel(), bp5, new SpaceLabel(), end, new SpaceLabel(), bp6,
				new SpaceLabel(), bp7, new SpaceLabel(), bp8, new SpaceLabel(), bp9, new SpaceLabel());

		body.getChildren().add(weightGraphPart);
		weightGraphPart.setVisible(false);

		Scene scene = new Scene(body);

		primaryStage.setScene(scene);
		primaryStage.setTitle("超市选址问题");
		primaryStage.show();

		dfs.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String vertice = text.getText();
				if (vertice.compareTo("") == 0) {
					Util.Alert("输入有误！！！");
					return;
				}

				@SuppressWarnings("unchecked")
				GraphPane<String> gPane = (GraphPane<String>) sp.getContent();
				if (!gPane.isExistVertice(vertice)) {
					Util.Alert("该顶点不存在！！！");
					return;
				}

				gPane.dfs(vertice);
			}
		});

		bfs.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String vertice = text.getText();
				if (vertice.compareTo("") == 0) {
					Util.Alert("输入有误！！！");
					return;
				}

				@SuppressWarnings("unchecked")
				GraphPane<String> gPane = (GraphPane<String>) sp.getContent();
				if (!gPane.isExistVertice(vertice)) {
					Util.Alert("该定点不存在！！！");
					return;
				}

				gPane.bfs(vertice);
			}
		});

		displayPath.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String startVertice = startInput.getText();
				String endVertice = endInput.getText();
				if (startVertice.compareTo("") == 0 || endVertice.compareTo("") == 0) {
					Util.Alert("输入有误！！！");
					return;
				}

				@SuppressWarnings("unchecked")
				GraphPane<String> gPane = (GraphPane<String>) sp.getContent();
				if (!gPane.isExistVertice(startVertice) || !gPane.isExistVertice(endVertice)) {
					Util.Alert("顶点不存在！！！");
					return;
				}

				gPane.getShortestPath(startVertice, endVertice);
			}
		});

		displayMST.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String startVertice = startInput.getText();
				if (startVertice.compareTo("") == 0) {
					Util.Alert("输入有误！！！");
					return;
				}

				@SuppressWarnings("unchecked")
				GraphPane<String> gPane = (GraphPane<String>) sp.getContent();
				if (!gPane.isExistVertice(startVertice)) {
					Util.Alert("该顶点不存在！！！");
					return;
				}

				gPane.displayMST(startVertice);
			}
		});

		findSite.setOnAction(new EventHandler<ActionEvent>() {
			@SuppressWarnings("unchecked")
			@Override
			public void handle(ActionEvent event) {
				GraphPane<String> gPane = (GraphPane<String>) sp.getContent();
				gPane.findSite();
			}
		});

		radioBt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (radioBt.isSelected()) {
					GraphPane<String> gPane = new GraphPane<>(true);
					gPane.setMinSize(1000, 800);
					sp.setContent(gPane);
					weightGraphPart.setVisible(true);
				} else {
					GraphPane<String> gPane = new GraphPane<>(false);
					gPane.setMinSize(1000, 800);
					sp.setContent(gPane);
					weightGraphPart.setVisible(false);
				}

			}
		});

	}

}
