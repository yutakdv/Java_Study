public class SudokuPuzzle {
	public static void main(String[] args) {
		Storage storage = new Storage();
		new SudokuController(storage).playSudokuPuzzle();
	}
}
