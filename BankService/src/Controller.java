
public class Controller {
	public void control(Model m, ViewIn in, ViewOut out) {
		int money = in.get_money();
		int month = in.get_month();
		double rate = in.get_rate();
		
		double total = m.calculate(money, month, rate);
		out.showMoney(month, total);
	}
}
