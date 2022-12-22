import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelButton extends JButton implements ActionListener {
    private String label;
    private LevelFrame lframe;

    public LevelButton(String l, LevelFrame lf){
        super(l);
        if(l.equals(""))
            this.setIcon(new ImageIcon(new ImageIcon("src/image/9.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        label = l;
        lframe = lf;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if(label.equals("Easy"))
            new MineBoard(9,9,10);
        if(label.equals("Normal"))
            new MineBoard(16,16,40);
        if(label.equals("Hard"))
            new MineBoard(25,25,150);
        if(label.equals(""))
            new MineBoard(9,9,80);
        lframe.setVisible(false);

    }
}
