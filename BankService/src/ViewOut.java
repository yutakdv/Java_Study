import javax.swing.JOptionPane;

public class ViewOut {
	public void showMoney(int month, double total) {
		String message = month + "개월 후 받으실 금액은 " + (int) total + "원 입니다.";
		JOptionPane.showMessageDialog(null, message);
	}
}
