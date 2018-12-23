package recursive.drawpolygon.entity;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class RegularPolygon extends Polygon {
	private double centerX;
	private double centerY;
	private int numOfEdges;
	private double lenOfEdges;
	private PolygonPoint[] points;

	public RegularPolygon(int numOfEdges, int lenOfEdges, Double centerX, Double centerY) {
		super();
		this.points = new PolygonPoint[numOfEdges];
		this.numOfEdges = numOfEdges;
		this.lenOfEdges = lenOfEdges;
		this.centerX = centerX;
		this.centerY = centerY;
		this.setFill(Color.WHITE);
		this.setStroke(Color.DARKBLUE);
		this.setStrokeWidth(2);

		addPoint();
	}

	public RegularPolygon(PolygonPoint[] points) {
		this.points = points;
		this.setFill(Color.WHITE);
		this.setStroke(Color.DARKBLUE);
		this.setStrokeWidth(2);
	}

	public RegularPolygon drawPolygon() {
		for (PolygonPoint p : points) {
			this.getPoints().add(p.getX());
			this.getPoints().add(p.getY());
		}
		return this;
	}

	// ÄæÊ±ÕëÌí¼Óµã
	private void addPoint() {
		double r = 0.5 * lenOfEdges / Math.sin(Math.PI / numOfEdges);

		for (int i = 0; i < numOfEdges; i++) {
			double x = centerX + Math.sin(i * 2 * Math.PI / numOfEdges + Math.PI / numOfEdges) * r;
			double y = centerY + Math.cos(i * 2 * Math.PI / numOfEdges + Math.PI / numOfEdges) * r;
			points[i] = new PolygonPoint(x, y);
		}
	}

	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}

	public int getNumOfEdges() {
		return numOfEdges;
	}

	public void setNumOfEdges(int numOfEdges) {
		this.numOfEdges = numOfEdges;
	}

	public double getLenOfEdges() {
		return lenOfEdges;
	}

	public void setLenOfEdges(double lenOfEdges) {
		this.lenOfEdges = lenOfEdges;
	}

	public PolygonPoint[] getPoint() {
		return this.points;
	}

	public void setPoint(PolygonPoint[] points) {
		this.points = points;
	}

}
