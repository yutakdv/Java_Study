public class SudokuController {
	private Storage storage;
	private Sudoku sudoku;
	
	public SudokuController(Storage s) {
		storage = s;
	}
	
	public void playSudokuPuzzle() {
		new LevelButtonFrame(storage);
	}
}
