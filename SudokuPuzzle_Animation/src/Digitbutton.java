import java.awt.event.*;
import javax.swing.*;

public class Digitbutton extends JButton implements ActionListener {
	private SudokuWriter frame;
	private Storage storage;
	private Sudoku sudoku;
	private int row;
	private int col;
	private int number;
	private int tmp;
	
	public Digitbutton(int d, Storage s, Sudoku s1, SudokuWriter f) {
		super(Integer.toString(d));
		frame = f;
		storage = s;
		sudoku = s1;
		tmp = d;
		
		this.update();
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		row = frame.row();
		col = frame.col();
		number = Integer.parseInt(this.getText());
		if (frame.first() != 0) {			
			if (sudoku.check(number, row, col)) {
				sudoku.getPuzzleBoard()[row][col] = number;
				this.update();
				storage.getSelected().setText(Integer.toString(number));
			}
		}
		
//		if (sudoku.countHoles() == 0) System.exit(0);
	}
	
	public void update() {
		int[] tmp_ls = {1,2,3,4,5,6,7,8,9};
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (sudoku.getPuzzleBoard()[i][j] == 0) {
					tmp_ls[sudoku.getSolutionBoard()[i][j] - 1] = 0;
				}
			}
		}
		
		for (int i : tmp_ls) {
			if (i != 0 && i == tmp) {
				this.setText("");
				this.setEnabled(false);
			}
		}
	}
}
