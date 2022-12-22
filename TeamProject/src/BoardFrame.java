import javax.swing.*;
import java.awt.*;

public class BoardFrame extends JFrame {

    private MineBoard board;
    private JPanel panel;
    private JLabel mode;
    private JLabel leftMine;
    private BoardButton[][] button;
    private int allCells;
    private int size;
    private int buttonsLeft;
    private String level;

    public BoardFrame(MineBoard b) {
        Container cp = getContentPane();
        board = b;

        cp.setLayout(new BorderLayout());
        JPanel p1 = new JPanel(new FlowLayout());
        if (board.getMinesLeft() == 10) {
            mode = new JLabel("   Easy Mode ");
            level = "Easy";
        } else if (board.getMinesLeft() == 40) {
            mode = new JLabel("Normal Mode ");
            level = "Normal";
        } else if (board.getMinesLeft() == 150) {
            mode = new JLabel("Hard Mode ");
            level = "Hard";
        } else {
            mode = new JLabel("??? ");
            level = "???";
        }

        mode.setFont(new Font("Consolas",Font.BOLD, 20));
        mode.setHorizontalAlignment(JLabel.CENTER);
        p1.add(mode);

        leftMine = new JLabel(" (Mines : " + board.getMinesLeft() + ")");
        leftMine.setFont(new Font("Consolas", Font.BOLD, 12));
        leftMine.setForeground(Color.BLUE);
        leftMine.setHorizontalAlignment(JLabel.RIGHT);
        if(!level.equals("???"))
            p1.add(leftMine);



        allCells = board.getSize();
        size = (int) Math.sqrt(allCells);
        buttonsLeft = allCells - board.getMinesLeft();

        button = new BoardButton[size][size];
        panel = new JPanel(new GridLayout(size, size));

        int row, col;
        for (int i = 0; i < allCells; i++) {
            row = i / size;
            col = i % size;
            BoardPiece piece = board.getBoardPiece(i);
            button[row][col] = new BoardButton(piece.getFace(), row, col, leftMine, level, board, this);
            panel.add(button[row][col]);
        }
        panel.setBackground(Color.GRAY);

        JPanel p3 = new JPanel(new FlowLayout());

        p3.add(new ResetButton(this));
        p3.add(new ExitButton());

        cp.add(p1, BorderLayout.NORTH);
        cp.add(panel, BorderLayout.CENTER);
        cp.add(p3, BorderLayout.SOUTH);
        
        setLocation(600, 250);
        setResizable(false);
        setTitle("MineSweeper");
        setSize(480, 500);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void open(int r, int c) {
        if (!button[r][c].check() || !button[r][c].isEnabled() || button[r][c].getFace() == 9) {
            return;
        } else if (button[r][c].getFace() != 0) {
            if (level.equals("Easy") || level.equals("???"))
                button[r][c].setIcon(new ImageIcon(new ImageIcon("src/image/" + button[r][c].getFace() + ".png").getImage().getScaledInstance(61, 53, Image.SCALE_SMOOTH))); // Easy Mode
            else if (level.equals("Normal"))
                button[r][c].setIcon(new ImageIcon(new ImageIcon("src/image/" + button[r][c].getFace() + ".png").getImage().getScaledInstance(36, 28, Image.SCALE_SMOOTH))); // Normal Mode
            else
                button[r][c].setIcon(new ImageIcon(new ImageIcon("src/image/" + button[r][c].getFace() + ".png").getImage().getScaledInstance(23, 20, Image.SCALE_SMOOTH))); // Hard Mode
            button[r][c].off(); // check > false

            buttonsLeft--;
        } else {
            buttonsLeft--;
            button[r][c].setEnabled(false);
            if(r >= 1)
                open(r - 1, c);
            if(r < size - 1)
                open(r + 1, c);
            if(c >= 1)
                open(r, c - 1);
            if(c < size - 1)
                open(r, c + 1);
        }
    }

    public void gameOver(){
        mode.setText("Game Over");
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(button[i][j].getFace() == 9){
                    if (level.equals("Easy") || level.equals("???"))
                        button[i][j].setIcon(new ImageIcon(new ImageIcon("src/image/9.png").getImage().getScaledInstance(63, 51, Image.SCALE_SMOOTH))); // Easy Mode
                    else if (level.equals("Normal"))
                        button[i][j].setIcon(new ImageIcon(new ImageIcon("src/image/9.png").getImage().getScaledInstance(36, 28, Image.SCALE_SMOOTH))); // Normal Mode
                    else
                        button[i][j].setIcon(new ImageIcon(new ImageIcon("src/image/9.png").getImage().getScaledInstance(23, 20, Image.SCALE_SMOOTH))); // Hard Mode

                }
                button[i][j].off();
            }
        }
    }

    public void gameClear(){
        if(buttonsLeft == 0){
            mode.setText("Game Clear!");
            for(int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    button[i][j].off();
                }
            }
        }
    }
}
