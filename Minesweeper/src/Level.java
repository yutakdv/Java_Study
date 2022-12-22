public class Level {
    static Level[] arrPredefinedLevels = {
        new Level("Beginner",9,9,10),
        new Level("Medium", 16,16,40),
        new Level("Expert", 30,16,99),
        new Level("Custom", 9,9,10)
    };
    
    String sName;
    public int nCols,nRows,nTotalMines;
    final private int MIN_ROWS=9,MAX_ROWS=30,MIN_COLS=9,MAX_COLS=24,MIN_MINES=10; // limits
        
    Level(String name, int cols,int rows, int mines)
    {
        sName=name;
        setParameters(cols,rows,mines);
    }
    
    public void setParameters(int cols,int rows, int mines)
    {   
        // Assign values and make sure they aren't out of range (otherwise correct them)
        nCols=cols;
        nRows=rows;
        nTotalMines=mines;
        if(nCols<MIN_COLS) nCols=MIN_COLS;
        else if(nCols>MAX_COLS) nCols=MAX_COLS;
        if(nRows<MIN_ROWS) nRows=MIN_ROWS;
        else if(nRows>MAX_ROWS) nRows=MAX_ROWS;
        if(nTotalMines<MIN_MINES) nTotalMines=MIN_MINES;
        else if(nTotalMines>(nRows-1)*(nCols-1)) nTotalMines=(nRows-1)*(nCols-1);
    }
        
    public String toString() { return sName; }
}