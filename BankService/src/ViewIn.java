import javax.swing.JOptionPane;

public class ViewIn {
	public int get_money() {
		String input = JOptionPane.showInputDialog("투자 원금을 원 단위로 입력해주세요.");
		int money = Integer.parseInt(input);
		return money;
	}
	
	public int get_month() {
		String input = JOptionPane.showInputDialog("투자 기간을 월 단위로 입력해주세요.");
		int month = Integer.parseInt(input);
		return month;
	}
	
	public double get_rate() {
		String input = JOptionPane.showInputDialog("월 수익률을 %단위로 입력해주세요.");
		double rate = Float.parseFloat(input);
		return rate;
	}
}
