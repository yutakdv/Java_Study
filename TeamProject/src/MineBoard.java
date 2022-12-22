import java.util.Random;

public class MineBoard {
    private int row;
    private int col;
    private int size;
    private int minesLeft;
    private BoardPiece[] board;
    public MineBoard(int r, int c, int mine){
        row = r;
        col = c;
        size = row * col;
        board = new BoardPiece[size];
        minesLeft = mine;
        createBoard();
        new BoardFrame(this);
    }

    public int getMinesLeft(){return minesLeft;}
    public void increaseMinesLeft(){minesLeft++;}
    public void decreaseMinesLeft(){minesLeft--;}
    public int getSize(){return size;}
    public BoardPiece getBoardPiece(int s){return board[s];}

    public void createBoard() {
        //0으로 초기화
        for (int i = 0; i < size; i++) {
            board[i] = new BoardPiece();
            board[i].setFace(0);
        }

        Random r = new Random();
        int random[] = new int[minesLeft];
        for (int i = 0; i < minesLeft; i++) {
            random[i] = r.nextInt(size);
            for (int j = 0; j < i; j++)
                if (random[i] == random[j])
                    i--;
        }

        for (int i = 0; i < minesLeft; i++) {
            board[random[i]].setFace(9);
        }

        int cell;
        for (int i = 0; i < minesLeft; i++) {
            int current_col = random[i] % col;

            if (current_col > 0) {
                //좌측 상단
                cell = random[i] - 1 - col;
                if (cell >= 0) {
                    if (board[cell].getFace() != 9) {
                        board[cell].increase();
                    }
                }
                // 좌측
                cell = random[i] - 1;
                if (cell >= 0) {
                    if (board[cell].getFace() != 9) {
                        board[cell].increase();
                    }
                }
                //좌측 하단
                cell = random[i] + col - 1;
                if (cell < size) {
                    if (board[cell].getFace() != 9) {
                        board[cell].increase();
                    }
                }
            }

            cell = random[i] - col; //상단
            if (cell >= 0) {
                if (board[cell].getFace() != 9) {
                    board[cell].increase();
                }
            }

            cell = random[i] + col; //하단
            if (cell < size) {
                if (board[cell].getFace() != 9) {
                    board[cell].increase();
                }
            }

            if (current_col < (col - 1)) {
                cell = random[i] - col + 1; //우측 상단
                if (cell >= 0) {
                    if (board[cell].getFace() != 9) {
                        board[cell].increase();
                    }
                }
                cell = random[i] + col + 1; //우측 하단
                if (cell < size) {
                    if (board[cell].getFace() != 9) {
                        board[cell].increase();
                    }
                }
                cell = random[i] + 1; //우측
                if (cell < size) {
                    if (board[cell].getFace() != 9) {
                        board[cell].increase();
                    }
                }
            }
        }


    }


}
