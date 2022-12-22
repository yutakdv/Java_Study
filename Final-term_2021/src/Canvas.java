import java.awt.Shape;

public class Canvas {
	private Shape[] shapes;
	private int counter = 0;
	
	public Canvas(int n) {
		shapes = new Shape[n];
	}
	
	public void add(Shape s) {
		if (counter == shapes.length) {
			Shape[] temp = new Shape[counter * 2];
			for (int i = 0; i < counter; i++)
				temp[i] = shapes[i];
			shapes = temp;
		}
		shapes[counter] = s;
		counter++;
	}
	
	public double totalArea() {
		double total = 0;
		for (int i = 0; i < counter; i++)
			if (shapes[i] instanceof Rectangle)
				total += ((Rectangle)shapes[i]).area();
			else if (shapes[i] instanceof Circle)
				total += ((Circle)shapes[i]).area();
		return total;
	}
	
	public static void main(String[] args) {
		new Canvas(4);
	}
}
