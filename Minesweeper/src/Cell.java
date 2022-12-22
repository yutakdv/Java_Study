// represents single cell
public class Cell {
    public enum Type {
        MINED,EMPTY
    }
    
    public enum State {
        COVERED,UNCOVERED,FLAGGED,FLAGGED_DEFUSED
    }
    
    public int nColIndex,nRowIndex; // cell position inside the grid
    
    public int nMinesAround; // total number of mines in nearby cells
    
    protected Type type;
    protected State state;
    
    public Cell(int row, int col) { // by default cell is clear and covered
        this.nColIndex=col;
        this.nRowIndex=row;
        this.type = Type.EMPTY;
        this.state = State.COVERED;
    }
    
    public boolean isCovered()
    {
        return (this.state==State.COVERED || this.state==State.FLAGGED);
    }
}