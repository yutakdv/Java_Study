import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
	private JLabel label = new JLabel("Score: ");
	private Score score;
	
	public Frame(Score s) {
		score = s;
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		JPanel pText = new JPanel(new FlowLayout());
		JPanel pButton = new JPanel(new FlowLayout());
		pText.add(label);
		cp.add(pText, BorderLayout.NORTH);
		pButton.add(new Button("A+", score, this));
		pButton.add(new Button("A0", score, this));
		pButton.add(new Button("B+", score, this));
		pButton.add(new Button("B0", score, this));
		cp.add(pButton, BorderLayout.SOUTH);
		setTitle("Your Score");
		setSize(400, 100);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void update() {
		label.setText("Score: " + score.getScore());
	}
}
