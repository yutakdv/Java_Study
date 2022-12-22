import javax.swing.*;
import java.awt.event.*;

public class FrameButton extends JButton implements ActionListener {
	private SudokuWriter frame;
	private int row;
	private int col;
	private Storage storage;
	
	public FrameButton(String number, SudokuWriter f, Storage s, int row, int col) {
		super(number);
		frame = f;
		storage = s;
		this.row = row;
		this.col = col;
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (getText() == "") {
			storage.setSelected(this);
			frame.cliked_FrameButton(row, col);
		}
	}
}
