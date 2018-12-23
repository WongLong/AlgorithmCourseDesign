package recursive.drawpolygon.entity;

import javafx.scene.layout.Pane;

public class RegularPolygonPane extends Pane {
	private RegularPolygon polygon;
	private double ratio;

	public RegularPolygonPane(int numOfEdges, int lenOfEdges, double ratio) {

		double r = 0.5 * lenOfEdges / Math.sin(Math.PI / numOfEdges);
		Double centerX = r * 1.0;
		Double centerY = r * 1.0;
		this.polygon = new RegularPolygon(numOfEdges, lenOfEdges, centerX, centerY);
		this.ratio = ratio;
		
		this.setPrefWidth(r * 2);
		this.setPrefHeight(r * 2);

		this.getChildren().add(polygon.drawPolygon());
		drawChildPolygon();
	}

	private void drawChildPolygon() {
		PolygonPoint[] point = polygon.getPoint();
		drawChildPolygon(point);
	}

	private void drawChildPolygon(PolygonPoint[] parentPoints) {
		if (parentPoints[0].getDistance(parentPoints[1]) < 20)
			return;

		PolygonPoint[] points = new PolygonPoint[parentPoints.length];
		for (int i = 1; i < parentPoints.length; i++) {
			PolygonPoint point1 = parentPoints[i - 1];
			PolygonPoint point2 = parentPoints[i];

			points[i - 1] = new PolygonPoint((point1.getX() - point2.getX()) * ratio + point2.getX(),
					(point1.getY() - point2.getY()) * ratio + point2.getY());
		}
		points[parentPoints.length - 1] = new PolygonPoint(
				(parentPoints[parentPoints.length - 1].getX() - parentPoints[0].getX()) * ratio + parentPoints[0].getX(),
				(parentPoints[parentPoints.length - 1].getY() - parentPoints[0].getY()) * ratio + parentPoints[0].getY());
		
		this.getChildren().add(new RegularPolygon(points).drawPolygon());
		drawChildPolygon(points);
	}

	public RegularPolygon getPolygon() {
		return polygon;
	}

	public void setPolygon(RegularPolygon polygon) {
		this.polygon = polygon;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
}
