
public class Rectangle extends Shape {
	private int garo;
	private int sero;
	
	public Rectangle(int m, int n, int g, int s) {
		super(m, n);
		garo = g;
		sero = s;
	}
	
	public int garo() {
		return garo;
	}
	
	public int sero() {
		return sero;
	}
	
	public int area() {
		return garo * sero;
	}
}
