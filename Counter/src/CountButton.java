import java.awt.event.*;
import javax.swing.*;

public class CountButton extends JButton implements ActionListener{
	Counter counter;
	CounterFrame frame;
	Drawing panel;
	
	public CountButton(Counter c, CounterFrame f, Drawing d) {
		super("OK"); // super -> construct method를 호출한게 아니라 JButton에 있는 method를 사용하는  // construct method는 하나만 실행된다
		counter = c;
		frame = f;
		panel = d;
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		counter.increment();
		frame.update();
		panel.repaint();
	}
}
