public class GameController {
    
    private GameBoard board;
    private BoardWriter writer;
    private Reader reader;
    
    public GameController(GameBoard b, BoardWriter w, Reader r) {
        board = b;
        writer = w;
        reader = r;
    }
    
    public void play() {
        Cell[][] square = board.square();
      
    }
    
    /** delay - how_long millisecond 동안 실행 정지  */
    private void delay(int how_long) { 
        try { Thread.sleep(how_long); }
        catch (InterruptedException e) { }
    }

}