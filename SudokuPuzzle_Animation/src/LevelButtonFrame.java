import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LevelButtonFrame extends JFrame implements ActionListener {
	private Storage storage;
	
	public LevelButtonFrame(Storage s) {
		storage = s;
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		
		JButton btn1 = new JButton("Easy");
		cp.add(btn1);
		btn1.addActionListener(this);
		
		JButton btn2 = new JButton("Medium");
		cp.add(btn2);
		btn2.addActionListener(this);
		
		JButton btn3 = new JButton("Hard");
		cp.add(btn3);
		btn3.addActionListener(this);
		
		setTitle("Select Level");
		setSize(300, 70);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button.getText().equals("Easy")) {
			new SudokuWriter(button.getText(), storage, new Sudoku(36));
		}
		else if (button.getText().equals("Medium")) {
			new SudokuWriter(button.getText(), storage, new Sudoku(45));
		}
		else if (button.getText().equals("Hard")) {
			new SudokuWriter(button.getText(), storage, new Sudoku(54));
		}
		setVisible(false);
	}
}