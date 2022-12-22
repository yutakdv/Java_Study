import java.time.*;
import javax.swing.*;

public class ViewIn {
	public LocalDate getDate(String message) {
		String input = JOptionPane.showInputDialog(message);
		int year = Integer.parseInt(input);
		
		input = JOptionPane.showInputDialog(message);
		int month = Integer.parseInt(input);
		
		input = JOptionPane.showInputDialog(message);
		int day = Integer.parseInt(input);
		
		return LocalDate.of(year, month, day);
	}
}
