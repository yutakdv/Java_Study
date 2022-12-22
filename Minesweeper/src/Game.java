import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JPanel;

//responsible for game logic
public class Game extends JPanel {
    public enum GameStatus {
        LOST,VICTORY,RUNNING;
    }
    
    protected Cell[][] minefield; // 2d array representing a minefield
    protected int nCols, nRows, nMines, nUncoveredClearCells=0;
    protected GameStatus status; // state of the current game (running, lost or won)
    
    public Game(int cols, int rows, int mines)
    {
        // start a new game
        this.nCols=cols;
        this.nRows=rows;
        this.nMines=mines;
        this.status=GameStatus.RUNNING;
        field_init();
    }
    
    protected void field_init()
    {
        // initialize an array by filling it with cell objects
        this.minefield = new Cell[nRows][nCols];
        for (int i = 0; i < nRows; i++)
        {
            for (int j = 0; j < nCols; j++)
            {
                minefield[i][j] = new Cell(i, j);
            }  
        }
    }   
    
    public void plantMines(Cell firstClickedCell)
    {
        // randomly plant mines by generating random coords in 2d array
        // (like if it was one-dimensional)
        int nMinesPlanted=0;
        int nPos=0,nColIndex=0,nRowIndex=0;
        Cell currentCell;
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        List<Cell> nearbyCells;
        while(nMinesPlanted<nMines)
        {
            nPos = rnd.nextInt(0, nCols*nRows);
            nRowIndex = nPos/nCols; // get Y cell coord (row index)
            nColIndex = nPos%nCols; // get X cell coord (column index)
            currentCell=minefield[nRowIndex][nColIndex];
            if(currentCell.type!=Cell.Type.MINED && currentCell!=firstClickedCell)
            {
                currentCell.type=Cell.Type.MINED;
                nearbyCells = getCellNeighbours(currentCell); 
                for(Cell nearbyCell : nearbyCells) nearbyCell.nMinesAround++; // notify cell neighbours about new mine
                nMinesPlanted++;
            }
            
        }
    }
    
    protected List<Cell> getCellNeighbours(Cell myCell)
    {
        // find neighbours of current cell
        // and add them to the list
        List<Cell> cells = new ArrayList<Cell>();
        if(myCell.nColIndex-1>=0) cells.add(minefield[myCell.nRowIndex][myCell.nColIndex-1]); // left neighbour
        if(myCell.nColIndex+1<nCols) cells.add(minefield[myCell.nRowIndex][myCell.nColIndex+1]); //right neighbour
        if(myCell.nRowIndex-1>=0) cells.add(minefield[myCell.nRowIndex-1][myCell.nColIndex]); // top neighbour
        if(myCell.nRowIndex+1<nRows) cells.add(minefield[myCell.nRowIndex+1][myCell.nColIndex]); // bottom neighbour
        if(myCell.nRowIndex+1<nRows && myCell.nColIndex+1<nCols) // bottom-right neighbour
            cells.add(minefield[myCell.nRowIndex+1][myCell.nColIndex+1]);
        if(myCell.nRowIndex-1>=0 && myCell.nColIndex-1>=0) // top-left neighbour
            cells.add(minefield[myCell.nRowIndex-1][myCell.nColIndex-1]);
        if(myCell.nRowIndex+1<nRows && myCell.nColIndex-1>=0) // bottom-left neighbour
            cells.add(minefield[myCell.nRowIndex+1][myCell.nColIndex-1]);
        if(myCell.nRowIndex-1>=0 && myCell.nColIndex+1<nCols) // top-right neighbour
            cells.add(minefield[myCell.nRowIndex-1][myCell.nColIndex+1]);
        return cells;
    }
    
    
    public void clickCell(Cell selectedCell)
    {
        // Trying to open a mined cell - the game is lost
        if(selectedCell.type==Cell.Type.MINED) {
            status=GameStatus.LOST;
            uncoverAllMines();
        }
        else 
        {
            // Clicked cell is clear
            uncoverCells(selectedCell);
            if(isVictory()) { // Check if there are any covered cells without mines
                status=GameStatus.VICTORY; // there aren't))
                flagAllMines(); 
            }
        }
    }
    
    protected void uncoverCells(Cell selectedCell)
    {
        // Uncover a clear cell. If it has no mines around
        // we also open all its nearby cells
        // Then do the same for their neighbours and so on...
        Stack<Cell> stack = new Stack<Cell>();
        List<Cell> nearbyCells;
        stack.push(selectedCell);
        while(!stack.isEmpty()) // expand area around clicked cell while possible
        {
            Cell currentCell = stack.pop();
            if(currentCell.state!=Cell.State.UNCOVERED) // skip already uncovered cells
            {
                currentCell.state=Cell.State.UNCOVERED;
                nUncoveredClearCells++;
                if(currentCell.nMinesAround==0) // if ALL neighbours are clear, we open them too
                {
                    nearbyCells = getCellNeighbours(currentCell);
                    for(Cell nearbyCell : nearbyCells)
                    {
                        if(nearbyCell.isCovered()) { // skip already uncovered cells (x2)
                            stack.push(nearbyCell); 
                        }
                    }
                }
            }
        }
    }
    
    
    protected void uncoverAllMines()
    {
        // If we open a mined cell, the game is lost
        // In such situation other mined cells are opened automatically
        // If some of them were previously flagged then they are shown as defused
        Cell currentCell;
        for (int i = 0; i < nRows; i++)
            for (int j = 0; j < nCols; j++)
            {
                currentCell=minefield[i][j];
                if(currentCell.type==Cell.Type.MINED) {
                    if(currentCell.state==Cell.State.FLAGGED) currentCell.state=Cell.State.FLAGGED_DEFUSED;
                    else currentCell.state=Cell.State.UNCOVERED;
                }
            }
    }
    
    protected boolean isVictory()
    {
        // Check if we haven't opened all the clear cells yet
        // If it's already done - it's a victory. Otherwise we continue the game
        return (nCols*nRows-nMines==nUncoveredClearCells);
    }
    
    protected void flagAllMines()
    {
        // If there are no clear covered cells left then the game is won
        // Mined cells are automatically marked with flags
        Cell currentCell;
        for (int i = 0; i < nRows; i++)
            for (int j = 0; j < nCols; j++)
            {
                currentCell=minefield[i][j];
                if(currentCell.type==Cell.Type.MINED) {
                    currentCell.state=Cell.State.FLAGGED;
                }
            }
    }
    
    public void restartGame()
    {
        // If user wants to restart existing game
        // we just cover any open cells
        Cell currentCell;
        nUncoveredClearCells=0;
        status=GameStatus.RUNNING;
        for (int i = 0; i < nRows; i++)
            for (int j = 0; j < nCols; j++)
            {
                currentCell=minefield[i][j];
                currentCell.state=Cell.State.COVERED;
            }
    }
    
    public void setFlag(Cell selectedCell)
    {
        // Sets a flag on covered cell (or removes it if it's already present)
        if(selectedCell.state==Cell.State.COVERED) selectedCell.state=Cell.State.FLAGGED;
        else if(selectedCell.state==Cell.State.FLAGGED) selectedCell.state=Cell.State.COVERED;
    }
}       