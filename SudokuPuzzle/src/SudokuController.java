import javax.swing.JOptionPane;

public class SudokuController {
	private Sudoku sudoku;
	private PlayerInput reader;
	private SudokuWriter writer;
	
	public SudokuController() {
		reader = new PlayerInput();
		int hole_count = reader.selectLevel();
		sudoku = new Sudoku(hole_count);
		writer = new SudokuWriter(sudoku);
	}
	
	// [배점 0.5/2.0]
    public void playSudokuPuzzle() {
    	
    	// 가로줄 입력 
    	String row_number = JOptionPane.showInputDialog("가로줄 번호를 넣어주세요.");
    	while (1 > Integer.parseInt(row_number) || Integer.parseInt(row_number) > 9) {
    		row_number = JOptionPane.showInputDialog("가로줄 번호를 넣어주세요.");
    	}
    	
    	// 세로줄 입력 
    	String column_number = JOptionPane.showInputDialog("세로줄 번호를 넣어주세요.");
    	while (1 > Integer.parseInt(column_number) || Integer.parseInt(column_number) > 9) {
    		column_number = JOptionPane.showInputDialog("세로줄 번호를 넣어주세요.");
    	}
    	
    	int row = Integer.parseInt(row_number) - 1 ;
    	int column = Integer.parseInt(column_number) - 1;
    	
    	int number = reader.selectNumber("숫자를 넣어주세요.");
    	if (sudoku.check(number, row, column)) {
    		sudoku.getPuzzleBoard()[row][column] = number;
    		writer.repaint();
    	}
    	if (sudoku.countHoles() == 0) System.exit(0);
    	playSudokuPuzzle();
    }
}
