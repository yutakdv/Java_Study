
public class CounterStarter {
	public static void main(String[] args) {
		Counter counter = new Counter();
		Drawing panel = new Drawing(counter);
		new CounterFrame(counter, panel);
	}
}
