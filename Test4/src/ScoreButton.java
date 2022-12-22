import javax.swing.*;
import java.awt.event.*;

public class ScoreButton extends JButton implements ActionListener {
	private Frame frame;
	private int idx;
	
	public ScoreButton(String l, Frame f, int index) {
		super(l);
		frame = f;
		idx = index;
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		frame.setCurrent(this);
	}
	
	public int getIdx() {
		return idx;
	}
}
