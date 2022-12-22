import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

// Responsible for painting the minefield
public class GameGrid extends Game {
    final static int CELL_WIDTH=20; // cell width in pixels
    final static int CELL_HEIGHT=20; // cell height in pixels
    HashMap<String,Image> icons;
    
    public void paint(Graphics graphics) {
        for (int i = 0; i < nRows; i++)
            for (int j = 0; j < nCols; j++)
            {
                Image currentCellImage=null;
                switch(minefield[i][j].state)
                {      
                    case COVERED: { // cell is covered
                        currentCellImage = icons.get("covered");
                        break;
                    }
                    case UNCOVERED: {
                        if(minefield[i][j].type==Cell.Type.MINED)
                        { 
                            currentCellImage = icons.get("mined"); // cell is open and contains a mine
                        }
                        else currentCellImage = icons.get(Integer.toString(minefield[i][j].nMinesAround)); // cell is open and clear
                        break;
                    }
                    case FLAGGED: { // cell is covered and flagged
                        currentCellImage = icons.get("covered_flagged");
                        break;
                    }
                    case FLAGGED_DEFUSED: { // cell is open, mined and was previously marked with a flag
                        currentCellImage = icons.get("mined_flagged");
                        break;
                    }   
                    default: break;
                }
                if(currentCellImage!=null)
                    graphics.drawImage(currentCellImage, j*CELL_WIDTH, i*CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT,null); // draw current cell
            }
    }
        
    @Override
    public Dimension getPreferredSize()
    {
        // report grid dimensions in order to adjust parent's size
        return new Dimension(CELL_WIDTH*nCols, CELL_HEIGHT*nRows);
    };
    
    public GameGrid(int cols, int rows, int mines) {
        super(cols, rows, mines);
        icons_init();
    }
    
    private void icons_init()
    {
        // load icons that represent different cell types and states
        icons = new HashMap<String,Image>();
        try {
        for(int i=0; i<=8; i++)
            {
                // Current cell is open and has N (0-8) mines around (horiz., vert. and diag.)
                icons.put(Integer.toString(i), ImageIO.read(GameGrid.class.getResource("images/"+Integer.toString(i)+".png")));
            }
            icons.put("covered", ImageIO.read(GameGrid.class.getResource("images/coveredCell.png"))); // cell is covered (and has no flag)
            icons.put("covered_flagged", ImageIO.read(MainWindow.class.getResource("images/coveredFlaggedCell.png"))); // cell is covered and has a flag
            icons.put("mined", ImageIO.read(GameGrid.class.getResource("images/minedCell.png"))); // cell is open and mined
            icons.put("mined_flagged", ImageIO.read(GameGrid.class.getResource("images/minedFlaggedCell.png"))); // cell is open, mined and was previously marked with a flag
        }
        catch(IOException ex)
        {
            ex.printStackTrace(); // could not load icon(s) for some reason
        }
    }
    
    public Cell getCellFromCoords(int x, int y) {
        return minefield[y/CELL_HEIGHT][x/CELL_WIDTH]; // get cell from it's coords
    }
}