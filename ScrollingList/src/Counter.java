public class Counter {
	private int count;
	
	public Counter(int n) {
		count = n;
	}
	
	public void increment() {
		count++;
	}
	
	public int count() {
		return count;
	}
}