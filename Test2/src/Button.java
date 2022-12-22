import javax.swing.*;
import java.awt.event.*;

public class Button extends JButton implements ActionListener {
	private String label;
	private Score score;
	private Frame frame;
	
	public Button(String l, Score s, Frame f) {
		super(l);
		label = l;
		score = s;
		frame = f;
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		score.setScore(label);
		frame.update();
	}
}
