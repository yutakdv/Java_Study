import javax.swing.*;
import java.awt.*;

public class LevelFrame extends JFrame {
    private BoardFrame frame;
    public LevelFrame(){
        Container cp = getContentPane();
        cp.setLayout(null);
        JPanel p1 = new JPanel(new FlowLayout());
        JPanel p2 = new JPanel(new GridLayout(3,1,10,10));
        JButton btn1 = new LevelButton("",this);
        JButton btn2 = new LevelButton("Easy",this);
        JButton btn3 = new LevelButton("Normal",this);
        JButton btn4 = new LevelButton("Hard",this);
        JLabel label = new JLabel("<MineSweeper>");
        label.setFont(new Font("Consolas",Font.BOLD, 15));
        btn1.setBounds(30,20,80,80);
        label.setBounds(5, 70,130,100);
        btn2.setBounds(150,15,100,20);
        btn3.setBounds(150,55,100,20);
        btn4.setBounds(150,95,100,20);

        cp.add(btn1);
        cp.add(label);
        cp.add(btn2);
        cp.add(btn3);
        cp.add(btn4);
        
        setLocation(700, 430);
        setResizable(false);
        setTitle("Select Level");
        setSize(280, 180);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
