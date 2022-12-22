import java.util.*;
import javax.swing.*;

public class Sudoku extends JButton {
	private int[][] solution = new int[9][9];
	private int hole_count;
	private int[][] puzzle_board = new int[9][9];
	
	public Sudoku(int count) {
		createSolutionBoard();
		hole_count = count;
		createPuzzleBoard(hole_count);
	}
	
	public int[][] getPuzzleBoard() {
		return puzzle_board;
	}
	
	public int[][] getSolutionBoard() {
		return solution;
	}
	
	public int countHoles() {
		return hole_count;
	}
	
	private void createSolutionBoard() {
		int random_sequence[] = new int[9];
		for (int i = 0; i < 9; i++) {
			Random random = new Random();
			random_sequence[i] = random.nextInt(9) + 1;
			for (int j = 0; j < i; j++) {
				if (random_sequence[i] == random_sequence[j])
					i--;
			}
		}
		
		solution[0][0] = solution[1][6] = solution[2][3] = solution[3][2] = solution[4][8] = solution[5][5] = solution[6][1] = solution[7][7] = solution[8][4] = random_sequence[0];
		solution[0][1] = solution[1][7] = solution[2][4] = solution[3][0] = solution[4][6] = solution[5][3] = solution[6][2] = solution[7][8] = solution[8][5] = random_sequence[1];
		solution[0][2] = solution[1][8] = solution[2][5] = solution[3][1] = solution[4][7] = solution[5][4] = solution[6][0] = solution[7][6] = solution[8][3] = random_sequence[2];
		solution[0][3] = solution[1][0] = solution[2][6] = solution[3][5] = solution[4][2] = solution[5][8] = solution[6][4] = solution[7][1] = solution[8][7] = random_sequence[3];
		solution[0][4] = solution[1][1] = solution[2][7] = solution[3][3] = solution[4][0] = solution[5][6] = solution[6][5] = solution[7][2] = solution[8][8] = random_sequence[4];
		solution[0][5] = solution[1][2] = solution[2][8] = solution[3][4] = solution[4][1] = solution[5][7] = solution[6][3] = solution[7][0] = solution[8][6] = random_sequence[5];
		solution[0][6] = solution[1][3] = solution[2][0] = solution[3][8] = solution[4][5] = solution[5][2] = solution[6][7] = solution[7][4] = solution[8][1] = random_sequence[6];
		solution[0][7] = solution[1][4] = solution[2][1] = solution[3][6] = solution[4][3] = solution[5][0] = solution[6][8] = solution[7][5] = solution[8][2] = random_sequence[7];
		solution[0][8] = solution[1][5] = solution[2][2] = solution[3][7] = solution[4][4] = solution[5][1] = solution[6][6] = solution[7][3] = solution[8][0] = random_sequence[8];
			
		// 가로줄 바꾸기
		shuffleRibbons();
		
		// 세로줄 바꾸기
		shuffleRibbons();
		transpose();
	}
	
	private int[] generateRandomPermutation(int n) {
		Random random = new Random();
		int[] permutation = new int[n];
		
		for (int i = 0; i < n; i++) {
			int d = random.nextInt(i + 1);
			permutation[i] = permutation[d];
			permutation[d] = i;
		}
		
		return permutation;
	}
	
	private void shuffleRibbons() {
		int[][] shuffled = new int[9][9];
		int[] random_index;
		for (int i = 0; i < 3; i++) {
			random_index = generateRandomPermutation(3);
			for (int j = 0; j < 3; j++) 
				shuffled[i * 3 + random_index[j]] = solution[i * 3 + j];
		}
		solution = shuffled;
	}
	
	private void transpose() {
		int[][] transposed = new int[9][9];
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				transposed[i][j] = solution[j][i];
		solution = transposed;
	}
	
	// [배점 = 0.5/2.0]
	private void createPuzzleBoard(int count) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				puzzle_board[i][j] = solution[i][j];
			}
		}
		
		for (; count > 0; count--) {
			int random_row = new Random().nextInt(9);
			int random_col = new Random().nextInt(9);
			
			while (puzzle_board[random_row][random_col] == 0) {
				random_row = new Random().nextInt(9);
				random_col = new Random().nextInt(9);
			}
			
			puzzle_board[random_row][random_col] = 0;
		}
	}
	
	// [배점 0.5/2.0]
    public boolean check(int digit, int row, int col) {
    	
    	// 세로 체크 
    	for (int i = 0; i < 9; i++)
    		if (i != row)
    			if (puzzle_board[i][col] == digit) return false;
    	
    	// 가로 체크
    	for (int i = 0; i < 9; i++)
    		if (i != col)
    			if (puzzle_board[row][i] == digit) return false;
    	
    	// 박스 체크 
    	int row_check = (int)(row / 3) * 3;
    	int col_check = (int)(col / 3) * 3;
    	
    	for (int i = row_check; i < row_check + 3; i++) {
    		for (int j = col_check; j < col_check + 3; j++) {
    			if (puzzle_board[row_check][col_check] == digit) return false;
    		}
    	}

    	hole_count--;
    	return true;
    }
}
