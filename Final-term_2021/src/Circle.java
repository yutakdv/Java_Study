
public class Circle extends Shape {
	private int radius;
	
	public Circle(int m, int n, int r) {
		super(m, n);
		radius = r;
	}
	
	public int radius() {
		return radius;
	}
	
	public double area() {
		return Math.PI * Math.pow(radius, 2);
	}
}
