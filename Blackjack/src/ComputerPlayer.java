public class ComputerPlayer extends CardPlayer {
	
	public ComputerPlayer(int max_cards) {
		super(max_cards);
	}
	
	public boolean wantsACard() {
		boolean decision;
		Card[] cards = hand();
		int total = 0;
		for (int i = 0; i < cards.length; i++) {
			total += cards[i].rank();
		}
		
		decision = (total <= 16) ? true : false;
		
		return decision;
	}
}
