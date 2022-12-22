import java.awt.event.*;
import javax.swing.*;

public class NewButton extends JButton implements ActionListener {

    private ScoreBoard score_board;

    public NewButton(ScoreBoard b) {
        super("New");
        score_board = b;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        score_board.begin();
    }

}