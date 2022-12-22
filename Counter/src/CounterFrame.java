import javax.swing.*;
import java.awt.*;

public class CounterFrame extends JFrame {
	private Counter counter;
	JLabel label = new JLabel("count = 0");
	private Drawing panel;
	
	public CounterFrame(Counter c, Drawing d) {
		counter = c;
		panel = d;
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		JPanel p1 = new JPanel(new FlowLayout());
		JPanel p2 = new JPanel(new FlowLayout());
		
		p1.add(label);
		
		JButton button1 = new CountButton(counter, this, panel);
		JButton button2 = new ExitButton();
		p2.add(button1);
		p2.add(button2);
		
		cp.add(p1, BorderLayout.NORTH);
		cp.add(panel, BorderLayout.CENTER);
		cp.add(p2, BorderLayout.SOUTH);
		
		setTitle("Counter");
		setSize(200, 300);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void update() {
		label.setText("count = " + counter.count());
	}
}
