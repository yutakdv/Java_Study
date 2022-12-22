import java.awt.event.*;
import javax.swing.*;

public class ListButton extends JButton implements ActionListener {
	private Counter2[] counters;
	private ListFrame frame;
	
	public ListButton(String label, Counter2[] c, ListFrame v) {
		super(label);
		counters = c;
		frame = v;
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		int choice = frame.getSelection();
		if (choice != -1) {
			counters[choice].increment();
			frame.update();
		}
	}
}