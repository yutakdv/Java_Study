import javax.swing.*;
import java.awt.event.*;

public class AttributeButton extends JButton implements ActionListener {
	private Frame frame;
	private ScoreManagement sm;
	
	public AttributeButton(String l, Frame f, ScoreManagement s) {
		super(l);
		frame = f;
		sm = s;
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			frame.getCurrent().setText(this.getText());
			sm.getScores()[frame.getCurrent().getIdx()] = this.getText();
			frame.update();
		}
		catch (NullPointerException e1) {
			System.out.println("설정되어있지 않습니다.");
		}
	}
}
