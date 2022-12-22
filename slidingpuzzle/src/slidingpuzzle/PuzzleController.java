package slidingpuzzle;

import javax.swing.*;

public class PuzzleController {
	private SlidePuzzleBoard puzzle;
	private PuzzleWriter writer;
	
	public PuzzleController(SlidePuzzleBoard p, PuzzleWriter w) {
		puzzle = p;
		writer = w;
	}
	
	public void play() {
		while (true) {
			writer.displayPuzzleBoard();
			String input = JOptionPane.showInputDialog("Your move: ");
			int n = Integer.parseInt(input);
			if (!puzzle.move(n)) writer.showNoMove("Cannot move.");
		}
	}
}
