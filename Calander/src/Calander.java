
public class Calander {

	public static void main(String[] args) {
		Model model = new Model();
		ViewOut output = new ViewOut();
		ViewIn input = new ViewIn();
		new Controller().control(model, output);
		new Controller().control(model, input, output);
	}

}
