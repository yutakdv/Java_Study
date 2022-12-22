import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
	
	public Frame() {
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(new Button("A+"));
		cp.add(new Button("A0"));
		cp.add(new Button("B+"));
		cp.add(new Button("B0"));
		setTitle("Your Score");
		setSize(300, 100);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new Frame();
	}
}
