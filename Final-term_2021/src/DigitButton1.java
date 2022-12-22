import java.awt.event.*;
import javax.swing.*;

public class DigitButton1 extends JButton implements ActionListener {
	private GugudanFrame frame;
	
	public DigitButton1(int d, GugudanFrame f) {
		super(Integer.toString(d));
		frame = f;
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		frame.cliked1(Integer.parseInt(this.getText()));
	}
}
