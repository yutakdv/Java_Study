import java.awt.event.*;
import javax.swing.*;

public class Button extends JButton implements ActionListener {
	public String score;
	
	public Button(String label) {
		super(label);
		score = label;
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println(score);
	}
}
