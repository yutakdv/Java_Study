import java.awt.Color;

public class Cell {
    
    private Color color;
    private boolean revealed;
    
    public Cell(Color c) {
        color = c;
    }
    
    public Color color() { 
        return color; 
    }

    public boolean revealed() { 
        return revealed; 
    }
    
    public void reveal() {
        revealed = true;
    }
    
    public void hide() {
        revealed = false;
    }
    
    public boolean isATwinWith(Cell c) {
    	 return this.color == c.color;
    }
}