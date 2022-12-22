import java.awt.*;
import javax.swing.*;

/** AnimationWriter - 상자와 공의 애니메이션 디스플레이 */
public class AnimationWriter extends JPanel {
    private BoxWriter box_writer;    // 상자 그리는 객체
    private BallWriter ball_writer1;
    private BallWriter ball_writer2;
    
    /** Constructor AnimationWriter - 상자와 공을 그리는 View 객체를 생성
     * @param b - 상자 그리는 객체
     * @param l - 공 그리는 객체
     * @param size - 프레임의 크기 */
    public AnimationWriter(BoxWriter b, BallWriter ball1, BallWriter ball2, int size) {
        box_writer = b;
        ball_writer1 = ball1;
        ball_writer2 =  ball2;
        JFrame f = new JFrame();
        f.getContentPane().add(this);
        f.setTitle("Bounce");
        f.setSize(size + 3, size + 30);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /** paintComponent - 공과 상자 그리기
     * @param g - 그래픽스 펜 */
    public void paintComponent(Graphics g) {
        box_writer.paintComponent(g);
        ball_writer1.paintComponent(g);
        ball_writer2.paintComponent(g);
    }
}