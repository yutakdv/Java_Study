import java.awt.Color;
import java.util.Random;

public class GameBoard {
    
    private Cell[][] square;
    private int point;
    
    public Cell[][] square() { 
        return square; 
    }
    
    public int point() { 
        return point; 
    }
    
    public void upgrade() { 
        point += 2; 
    }
    
    public GameBoard() {
        square = new Cell[4][4];
        Color[] colors = {Color.BLUE, Color.CYAN, Color.PINK, Color.RED, 
                          Color.GRAY, Color.GREEN, Color.MAGENTA, Color.ORANGE};
        Cell[] twins = createTwinCells(colors);
        makeSquare(shuffle(twins));
    }
    
    private void makeSquare(Cell[] cells) {
        // to be filled...
    	for (int i = 0; i < square.length; i++) {
    		for (int j = 0; j < square.length; j++) {
    			square[i][j] = cells[j];
    		}
    	}
    }
    
    private Cell[] createTwinCells(Color[] cs) {
//         to be filled...
    	Cell[] first = new Cell[2];
    	Cell[] second = new Cell[2];
    	Cell[] third = new Cell[2];
    	Cell[] fourth = new Cell[2];
    	first[0] = new Cell(cs[0]);
    	first[1] = new Cell(cs[1]);
    	second[0] = new Cell(cs[2]);
    	second[1] = new Cell(cs[3]);
    	third[0] = new Cell(cs[4]);
    	third[1] = new Cell(cs[5]);
    	fourth[0] = new Cell(cs[6]);
    	fourth[1] = new Cell(cs[7]);
    	Cell[] one_dimension_arr = new Cell[4];
    	
    	int index = 0;
    	for (Cell i: first) {
    		one_dimension_arr[index++] = i;
    	}
    	for (Cell i: second) {
    		one_dimension_arr[index++] = i;
    	}
    	for (Cell i: third) {
    		one_dimension_arr[index++] = i;
    	}
    	for (Cell i: fourth) {
    		one_dimension_arr[index++] = i;
    	}
    	return one_dimension_arr;
    }
    
    private Cell[] shuffle(Cell[] twins) {
        int index;
        Cell temp;
        Random random = new Random();
        for (int i = twins.length - 1; i > 0; i--) {
            index = random.nextInt(i+1);
            temp = twins[index];
            twins[index] = twins[i];
            twins[i] = temp;
        }
        return twins;
    }

}