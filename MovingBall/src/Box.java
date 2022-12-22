public class Box {
	private final int BOX_SIZE; // 상자의 크기
	private final int OBSTACLE_WIDTH;
	private final int OBSTACLE_X0;
	
	/** Constructor Box - 상자 생성 
	 * @param size - 상자의 크기 */
	public Box(int size) { 
		BOX_SIZE = size;
		int width = size / 4;
		OBSTACLE_WIDTH = width;
		OBSTACLE_X0 = (size - width) / 2;
	}
	
	public int box_size() { return BOX_SIZE; }
	public int obstacleWidth() { return OBSTACLE_WIDTH; }
	public int obstacleX0() { return OBSTACLE_X0; }
	
	/** inHorizontalContact - 공이 x축 방향으로 좌/우 벽에 도달 여부를 리턴
	 * @param x_position - 공의 x 좌표
	 * @return true, 공의 x 좌표가 좌우 벽의 x 좌표와 같거나 벗어났으면 true, 그렇지 않으면 false */
	public boolean inHorizontalContact(int x_position) { 
		return (x_position <= 0 ) || (x_position >= BOX_SIZE);
	}
	/** inVerticalContact - 공이 y축 방향으로 아래/위 벽에 도달 여부를 리턴
	* @param y_position - 공의 y 좌표
	* @return true, 공의 y 좌표가 아래위 벽의 y 좌표와 같거나 벗어났으면 true, 그렇지 않으면 false */
	public boolean inVerticalContact(int y_position) {
		return (y_position <= 0 ) || (y_position >= BOX_SIZE);
	}
	
	public boolean inObstacleContact(int x, int y) { 
		return OBSTACLE_X0 <= x && x <= OBSTACLE_X0 + OBSTACLE_WIDTH && y == BOX_SIZE / 2;
	}
	/** sizeOf - 상자의 크기를 리턴 */ 
	public int sizeOf() {
		return BOX_SIZE; 
	}
}