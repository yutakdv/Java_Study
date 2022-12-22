
public class Head {
	private String state;
	private String score;
	private Button selected;
	
	public Head() {
		state = "";
		score = "";
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getScore() {
		return score;
	}
	
	public void setScore(String score) {
		this.score = score;
	}
	
	public Button getSelected() {
		return selected;
	}
	
	public void setSelected(Button selected) {
		this.selected = selected;
	}
	
	
}