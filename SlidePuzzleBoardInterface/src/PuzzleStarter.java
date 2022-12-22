public class PuzzleStarter {
	public static void main(String[] args) {
		SlidePuzzleBoardInterface puzzle = new SlidePuzzleBoard();
		PuzzleWriterInterface writer = new PuzzleWriter(puzzle);
		new PuzzleController(puzzle, writer).play();
	}
}
