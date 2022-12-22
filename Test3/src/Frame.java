import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{
	private Head head;
	
	public Frame(Head h) {
		Container cp = getContentPane();
		head = h;
		cp.setLayout(new BorderLayout());
		JPanel pTitle = new JPanel(new FlowLayout());
		JPanel pButton = new JPanel(new FlowLayout());
		JPanel pList = new JPanel(new GridLayout(3, 2, 10, 10));
		pTitle.add(new JLabel("Score"));
		pList.add(new JLabel("프로그램설계방법론"));
		pList.add(new Button(" ", this, head, 0));
		pList.add(new JLabel("시스템프로그래밍기초"));
		pList.add(new Button(" ", this, head, 0));
		pList.add(new JLabel("이산수학"));
		pList.add(new Button(" ", this, head, 0));
		pButton.add(new Button("A+", this, head, 1));
		pButton.add(new Button("A0", this, head, 1));
		pButton.add(new Button("B+", this, head, 1));
		pButton.add(new Button("B0", this, head, 1));
		cp.add(pTitle, BorderLayout.NORTH);
		cp.add(pList, BorderLayout.CENTER);
		cp.add(pButton, BorderLayout.SOUTH);
		setTitle("Your Score");
		setSize(300, 300);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
