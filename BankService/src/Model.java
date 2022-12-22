
public class Model {
	public double calculate(int money, int month, double rate) {
		double tmp = (1 + rate / 100);
		double total = money * Math.pow(tmp, month);
		
		return total;
	}
}
