package recursive.drawpolygon.entity;

public class PolygonPoint {
	private double x;
	private double y;
	
	public PolygonPoint() {
		
	}
	
	public PolygonPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getDistance(PolygonPoint point) {
		return Math.pow(Math.pow(this.getX() - point.getX(), 2) + Math.pow(this.getY()- point.getY(), 2), 1.0 / 2);
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
