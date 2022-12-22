import javax.swing.*;

public class HumanPlayer extends CardPlayer {
	private String name;
	public int chips;
	
	public HumanPlayer(int max_cards, String n) {
		super(max_cards);
		name = n;
	}
	
	public boolean wantsACard() {
		String response = JOptionPane.showInputDialog("한장 더 드릴까요? (Y/N)");
		return response.equals("Y");
	}
	
	public void youWin() {
		System.out.println(name + " 승리");
		chips++;
	}
	
	public void youWinBlackjack() {
		System.out.println("블랙잭! " + name + " 승리");
		chips += 2;
	}
	
	public void youLose() {
		System.out.println("딜러 승리");
		chips--;
	}
	
	public void youDraw() {
		System.out.println("비김");
	}
	
	public int chips() {
		return chips;
	}
}
