 package dealDiceGame;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {
    private int size;
    private Player player1;
    private Player player2;

    public GameBoard(Player p1, Player p2) {
        size = 400;

        player1 = p1;
        player2 = p2;

        JFrame f = new JFrame();
        f.getContentPane().add(this);
        f.setTitle("주사위 게임");
        f.setSize(size, size);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    protected void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, size, size);

        g.setColor(Color.BLACK);

        // player name
        g.drawString(player1.name(), 80, 100);
        g.drawString(player2.name(), 280, 100);

        // win points
        {
            String point_p1 = Integer.toString(player1.points());
            String point_p2 = Integer.toString(player2.points());
            g.drawString(point_p1, 80, 150);
            g.drawString(point_p2, 280, 150);
            this.repaint();
        }

        // who win?
        {
            if (player1.wins() == true) g.drawString("이겼다!", 80, 200);
            else if (player2.wins() == true) g.drawString("이겼다", 280, 200);
            else g.drawString("비겼다!", 180, 200);
            this.repaint();
        }

        // 주사위 한 쌍을 굴린 결과
        {
            String p1_first_number = Integer.toString(player1.rolled().face1());
            String p1_second_number = Integer.toString(player1.rolled().face2());
            String p2_first_number = Integer.toString(player2.rolled().face1());
            String p2_second_number = Integer.toString(player2.rolled().face2());
            g.drawString(p1_first_number, 80, 250);
            g.drawString(p1_second_number, 120, 250);
            g.drawString(p2_first_number, 280, 250);
            g.drawString(p2_second_number, 320, 250);
            this.repaint();
        }
    }
}