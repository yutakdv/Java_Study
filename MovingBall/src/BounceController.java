/** BounceController - 상자 안에서 움직이는 공 제어 */
public class BounceController {
    private MovingBall ball1;
    private MovingBall ball2;
    private AnimationWriter writer; // 애니메이션 객체 (Output-View)

    /** Constructor BounceController 컨트롤러 초기화
     * @param b - 공 객체 (Model)
     * @param w - 애니메이션 객체 (Output-View)  */
    public BounceController(MovingBall b1, MovingBall b2, AnimationWriter w) {
        ball1 = b1;
        ball2 = b2;
        writer = w;
    }

    /** runAnimation - 내부 시계를 활용하여 애니메이션 구동 */
    public void runAnimation() {
        int time_unit = 0;    // 애니메이션 스텝의 시간 단위
        int painting_delay = 20;  // 다시 그리기 사이의 지연 시간 간격
        while (true) {
            delay(painting_delay);
            ball1.move(time_unit);
            ball2.move(time_unit);
            if (collide(ball1, ball2)) {
            	ball1.reverse();
            	ball2.reverse();
            }
            writer.repaint();
        }
    }
    
    private boolean collide(MovingBall b1, MovingBall b2) {
    	int r = b1.radiusOf();
    	int x_position = b1.xPosition() - b2.xPosition();
    	int y_position = b1.yPosition() - b2.yPosition();
    	double distance = Math.sqrt(Math.pow(x_position, 2) + Math.pow(y_position, 2));
    	
    	return distance <= r * 2;
    	
    }

    /** delay - how_long millisecond 동안 실행 정지  */
    private void delay(int how_long) {
        try { Thread.sleep(how_long); }
        catch (InterruptedException e) { }
    }
}