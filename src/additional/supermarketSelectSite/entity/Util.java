package additional.supermarketSelectSite.entity;

import java.util.List;

import additional.supermarketSelectSite.entity.AbstractGraph.Edge;
import additional.supermarketSelectSite.entity.GraphNode.LineNode;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.control.Alert;

public class Util {
	// œ˚œ¢Ã·–—øÚ
	public static void Alert(String str) {
		new Alert(AlertType.ERROR, str, ButtonType.OK).showAndWait();
	}

	public static int circleIndexOf(List<GraphNode> graphNodes, Circle circle) {
		for (int i = 0; i < graphNodes.size(); i++)
			if (graphNodes.get(i).verticeNode.circle.equals(circle))
				return i;

		return -1;
	}

	public static Line getLine(List<GraphNode> gNodes, Edge visitedEdge) {
		int index = visitedEdge.u;
		GraphNode gNode = gNodes.get(index);

		for (LineNode lNode : gNode.edgeNodes)
			for (Edge edge : lNode.edges)
				if (edge.isSameEdge(visitedEdge))
					return lNode.line;

		return null;
	}

	public static boolean isExistEdge(List<Edge> edges, int u, int v) {
		for (Edge edge : edges)
			if (edge.u == u && edge.v == v)
				return true;

		return false;
	}
}
