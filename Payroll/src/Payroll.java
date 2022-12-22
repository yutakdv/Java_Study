import javax.swing.*;

public class Payroll {
	public static void main(String[] args) {
		String name_in = JOptionPane.showInputDialog("입력 파일명");
		String name_out = JOptionPane.showInputDialog("출력 파일명");
		if (name_in != null && name_out != null)
			new PayrollController().processPayroll(name_in, name_out);
	}
}
