import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
	private ScoreManagement score;
	private JLabel avg;
	private ScoreButton current;
	
	public Frame(ScoreManagement s) {
		score = s;
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		avg = new JLabel("Score");
		JPanel pList = new JPanel(new GridLayout(3, 2, 10, 10));
		JPanel pButton = new JPanel(new FlowLayout());
		pList.add(new JLabel("프로그램설계방법론"));
		pList.add(new ScoreButton("", this, 0));
		pList.add(new JLabel("시스템프로그래밍기초"));
		pList.add(new ScoreButton("", this, 1));
		pList.add(new JLabel("이산수학"));
		pList.add(new ScoreButton("", this, 2));
		pButton.add(new AttributeButton("A+", this, score));
		pButton.add(new AttributeButton("A0", this, score));
		pButton.add(new AttributeButton("B+", this, score));
		pButton.add(new AttributeButton("B0", this, score));
		
		cp.add(avg, BorderLayout.NORTH);
		cp.add(pList, BorderLayout.CENTER);
		cp.add(pButton, BorderLayout.SOUTH);
		setTitle("Your Score");
		setSize(400, 300);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void setCurrent(ScoreButton b) {
		current = b;
	}
	
	public ScoreButton getCurrent() {
		return current;
	}
	
	public void update() {
		avg.setText(score.getAverage() + "");
	}
}
