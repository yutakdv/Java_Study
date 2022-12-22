import java.awt.event.*;
import javax.swing.*;

public class ResetButton extends JButton implements ActionListener {
    private BoardFrame frame;
    public ResetButton(BoardFrame f) {
        super("Reset");
        frame = f;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        frame.setVisible(false);
        //게임 재시작
        new LevelFrame();
    }

}