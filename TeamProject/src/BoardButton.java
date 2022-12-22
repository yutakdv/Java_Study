import java.awt.event.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;

public class BoardButton extends JButton implements ActionListener {

    private MineBoard board;
    private BoardFrame frame;
    private int face;
    private int row, col;
    private boolean check = true;
    private boolean flag = false;
    private ImageIcon image;
    private JLabel label;
    private String level;

    public BoardButton(int value, int r, int c, JLabel l, String lv, MineBoard b, BoardFrame f) {
        level = lv;
        if (lv.equals("Easy") || lv.equals("???"))
            this.setIcon(new ImageIcon(new ImageIcon("src/image/10.png").getImage().getScaledInstance(63, 51, Image.SCALE_SMOOTH))); // Easy Mode || ??? Mode
        else if (lv.equals("Normal"))
            this.setIcon(new ImageIcon(new ImageIcon("src/image/10.png").getImage().getScaledInstance(36, 28, Image.SCALE_SMOOTH))); // Medium Mode
        else
            this.setIcon(new ImageIcon(new ImageIcon("src/image/10.png").getImage().getScaledInstance(23, 20, Image.SCALE_SMOOTH))); // Hard Mode

        face = value;
        row = r;
        col = c;
        board = b;
        label = l;
        frame = f;
        
        addActionListener(this);
        addMouseListener(new MinesAdapter(this));
    }
    public int getFace(){return face;}
    public void off(){check = false;}
    public boolean check(){return check;}
    public void isFlag(){
        if(!flag) flag = true;
        else flag = false;
    }


    public void actionPerformed(ActionEvent e) {
        if(check) {
            frame.open(row, col);
            if (this.face == 9)
                frame.gameOver();
            frame.gameClear();
        }
    }

    public class MinesAdapter extends MouseAdapter {
        BoardButton button;
        public MinesAdapter(BoardButton b){
            button = b;
        }
        @Override
        public void mousePressed(MouseEvent e) {
            image = new ImageIcon("src/image/11.png");
            if(e.getButton() == MouseEvent.BUTTON3 && check){
                isFlag();
                if(board.getMinesLeft() > 0) {
                    if(flag){
                        if (level.equals("Easy") || level.equals("???"))
                            button.setIcon(new ImageIcon(image.getImage().getScaledInstance(63, 51, Image.SCALE_SMOOTH))); // Easy Mode || ??? Mode
                        else if (level.equals("Normal"))
                            button.setIcon(new ImageIcon(image.getImage().getScaledInstance(36, 28, Image.SCALE_SMOOTH))); // Normal Mode
                        else
                            button.setIcon(new ImageIcon(image.getImage().getScaledInstance(23, 20, Image.SCALE_SMOOTH))); // Hard Mode

                        board.decreaseMinesLeft();
                    }
                    else {
                        image = new ImageIcon("src/image/10.png");
                        if (level.equals("Easy") || level.equals("???"))
                            button.setIcon(new ImageIcon(image.getImage().getScaledInstance(63, 51, Image.SCALE_SMOOTH))); // Easy Mode || ??? Mode
                        else if (level.equals("Normal"))
                            button.setIcon(new ImageIcon(image.getImage().getScaledInstance(36, 28, Image.SCALE_SMOOTH))); // Normal Mode
                        else
                            button.setIcon(new ImageIcon(image.getImage().getScaledInstance(23, 20, Image.SCALE_SMOOTH))); // Hard Mode
                        board.increaseMinesLeft();
                    }
                }
                else if(board.getMinesLeft() == 0 && flag){
                    isFlag();
                    image = new ImageIcon("src/image/10.png");
                    if (level.equals("Easy") || level.equals("???"))
                        button.setIcon(new ImageIcon(image.getImage().getScaledInstance(63, 51, Image.SCALE_SMOOTH))); // Easy Mode || ??? Mode
                    else if (level.equals("Normal"))
                        button.setIcon(new ImageIcon(image.getImage().getScaledInstance(36, 28, Image.SCALE_SMOOTH))); // Normal Mode
                    else
                        button.setIcon(new ImageIcon(image.getImage().getScaledInstance(23, 20, Image.SCALE_SMOOTH))); // Hard Mode
                    board.increaseMinesLeft();
                }
                label.setText(" (Mines : " + board.getMinesLeft() + ")");
                label.setFont(new Font("Consolas", Font.BOLD, 12));
                label.setForeground(Color.BLUE);
            }
        }
    }
}
