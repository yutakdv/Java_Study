import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class ClockWriter extends JPanel {
	private final int SIZE;
	private int diameter;
	
	public ClockWriter(int size) {
		SIZE = size;
		JFrame frame = new JFrame();
		frame.setTitle("Clock");
		frame.setSize(SIZE + 50, SIZE + 150);
		frame.getContentPane().add(this);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void paintComponent(Graphics g) {
        // 현재 시간 
        LocalTime now = LocalTime.now();

        String nowTime = DateTimeFormatter.ofPattern("HH:mm:ss").format(now);

		g.setColor(Color.BLUE);
		g.drawString(nowTime, 120, 50);
		g.setColor(Color.LIGHT_GRAY);
		g.fillOval(25, 100, SIZE, SIZE);

        // 반지름 
        int radius = SIZE / 2;
		
		// 눈금 그리기
		g.setColor(Color.MAGENTA);
		g.drawOval(25, 100, SIZE, SIZE); // 원 테두리

        // 90도 각도 시간 표시
        g.setFont(new Font("default", Font.BOLD, 15)); // 글씨체 설정
        g.setColor(Color.RED);
        g.drawString("9", 14, 105 + radius);
        g.drawString("12", 20 + radius, 95);
        g.drawString("3", 28 + SIZE, 103 + radius);
        g.drawString("6", 20 + radius, 113 + SIZE);

        // 시계 눈금 표시
        g.setColor(Color.MAGENTA);
		for (int i = 0; i <= 360; i += 6) { // 시계 테두리
			int x1 = (int) (radius * Math.sin(Math.PI * i / 180));
			int y1 = (int) (radius * Math.cos(Math.PI * i / 180));
			int x2 = (int) ((radius - 5) * Math.sin(Math.PI * i / 180));
			int y2 = (int) ((radius - 5) * Math.cos(Math.PI * i / 180));

			if (i % 30 == 0) { // 큰 눈금 표시
				x2 = (int) ((radius - 20) * Math.sin(Math.PI * i / 180));
				y2 = (int) ((radius - 20) * Math.cos(Math.PI * i / 180));
			}

            int x_center = 25 + radius;
            int y_center = 100 + radius;

			g.drawLine(x_center + x1, y_center + y1, x_center + x2, y_center + y2);
		}
		
		// 시계 중심 
		int x1 = 25 + radius;
		int y1 = 100 + radius;
		int diff = radius / 5;
		
		// 커져가는 초침 동심원
		int tmp = (SIZE - diameter) / 2;
		g.setColor(Color.PINK);
		g.fillOval(25 + tmp, 100 + tmp, diameter, diameter);
        diameter = SIZE * now.getSecond() / 60;
		
		// 분침 
		radius -= diff;
		double minute_angle = (now.getMinute() - 15) * Math.PI / 30;
		int x2 = x1 + (int)(radius * Math.cos(minute_angle));
		int y2 = y1 + (int)(radius * Math.sin(minute_angle));
		g.setColor(Color.RED);
		g.drawLine(x1, y1, x2, y2);
		
		// 시침 
		radius -= diff;
		double hour_angle = (now.getHour() - 3) * Math.PI / 6 + minute_angle / 12;
		x2 = x1 + (int)(radius * Math.cos(hour_angle));
		y2 = y1 + (int)(radius * Math.sin(hour_angle));
		g.setColor(Color.YELLOW);
		g.drawLine(x1, y1, x2, y2);
	}
}