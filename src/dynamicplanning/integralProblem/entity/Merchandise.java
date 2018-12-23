package dynamicplanning.integralProblem.entity;

public class Merchandise {
	private int value;
	private int integral;
	
	public Merchandise() {
		
	}
	
	public Merchandise(int value, int integral) {
		this.value = value;
		this.integral = integral;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getIntegral() {
		return integral;
	}
	public void setIntegral(int integral) {
		this.integral = integral;
	}	
}
