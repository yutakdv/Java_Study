package hello;

public class Helloworld {

	public static void main(String[] args) {
		model m = new model();
		view v = new view();
		new Controller().control(m, v);

	}
}
