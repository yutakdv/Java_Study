import java.awt.*;

/** BoxWriter - 상자를 그림  */
public class BoxWriter {
	
	private Box box;
    private int BOX_SIZE;   // 상자 객체

    /** Constructor BoxWriter
     * @param b - 상자 객체 */
    public BoxWriter(Box b) {
        box = b;
    }

    /** paint - 상자 그리기
     * @param g - 그래픽스 펜 */
    public void paintComponent(Graphics g) {
    	g.setColor(Color.WHITE);
    	g.fillRect(0, 0, BOX_SIZE, BOX_SIZE);
    	g.setColor(Color.BLACK);
    	g.drawRect(0, 0, BOX_SIZE, BOX_SIZE);
    	
    	int size = box.box_size();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, size, size);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, size, size);
        g.setColor(Color.GRAY);
        int x = box.obstacleX0();
        int y = size / 2 - size / 80;
        int width = box.obstacleWidth();
        int height = size / 40;
        g.fillRect(x, y, width, height);
    }
}