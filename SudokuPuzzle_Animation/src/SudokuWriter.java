import java.awt.*;
import javax.swing.*;

public class SudokuWriter extends JFrame {
	private Sudoku sudoku;
	private int first = 0;
	private int this_row;
	private int this_col;
	private Storage storage;
	
	public SudokuWriter(String level, Storage st, Sudoku s) {
		sudoku = s;
		storage = st;
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		JLabel label = new JLabel(level + " Mode");
		cp.add(label, BorderLayout.NORTH);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel p1 = new JPanel(new GridLayout(9, 9));
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) { 
				String number = Integer.toString(sudoku.getPuzzleBoard()[i][j]);
				if (Integer.parseInt(number) == 0) {
					number = "";
				}
				p1.add(new FrameButton(number, this, storage, i, j));
			}
		}
		cp.add(p1, BorderLayout.CENTER);
		
		JPanel p2 = new JPanel(new GridLayout(1, 9));
		for (int i = 1; i < 10; i++)
			p2.add(new Digitbutton(i, storage, sudoku, this));
		cp.add(p2, BorderLayout.SOUTH);
		
		setTitle("Sudoku");
		setSize(300, 400);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public void cliked_FrameButton(int row, int col) {
		this_row = row;
		this_col = col;
		if (first == 0) {
			first = 1;
		}
	}
	
	public int first() {
		return first;
	}
	
	public int row() {
		return this_row;
	}
	
	public int col() {
		return this_col;
	}
}