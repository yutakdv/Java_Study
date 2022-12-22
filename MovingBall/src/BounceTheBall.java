import java.awt.*;

public class BounceTheBall {

    public static void main(String[] args) {
        int size = 200;
        Box box = new Box(size);
        MovingBall ball1 = new MovingBall(180, 0, 6, box);
        MovingBall ball2 = new MovingBall(200, 150, 6, box);
        BoxWriter box_writer = new BoxWriter(box);
        BallWriter ball_writer1 = new BallWriter(ball1,Color.RED);
        BallWriter ball_writer2 = new BallWriter(ball2,Color.BLUE);
        AnimationWriter writer = new AnimationWriter(box_writer, ball_writer1, ball_writer2, size);
        new BounceController(ball1, ball2, writer).runAnimation();
    }

}