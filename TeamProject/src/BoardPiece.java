public class BoardPiece {
    private MineBoard board;
    private int face;
    public void setFace(int value){
        face = value;
    }
    public void increase(){face++;}
    public int getFace(){return face;}
}
