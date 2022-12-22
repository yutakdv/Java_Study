import javax.swing.*;
import java.awt.event.*;

public class Button extends JButton implements ActionListener {
	private Frame frame;
	private Head head;
	private int mode;
	
	public Button(String l, Frame f, Head h, int m) {
		super(l);
		frame = f;
		head = h;
		mode = m;
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (mode == 1) {
			try {
				head.getSelected().setText(this.getText());
			}
			catch (NullPointerException e1) {
				System.out.println("과목이 선택되지 않았습니다.");
			}
		} else {
			head.setSelected(this);
		}
	}
}
