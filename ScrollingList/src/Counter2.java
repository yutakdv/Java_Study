public class Counter2 extends Counter {
	private int index;
	
	public Counter2(int n, int j) {
		super(n);
		index = j;
	}
	
	public String toString() {
		return "Counter " + index + " has " + count();
	}
}