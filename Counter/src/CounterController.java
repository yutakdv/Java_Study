import java.awt.event.*;

public class CounterController implements ActionListener {
	Counter counter;
	CounterFrame frame;
	
	public CounterController(Counter c, CounterFrame f) {
		counter = c;
		frame = f;
	}
	
	public void actionPerformed(ActionEvent e) {
		counter.increment();
		frame.update();
	}
}
