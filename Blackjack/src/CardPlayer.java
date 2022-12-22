import javax.swing.*;

public abstract class CardPlayer implements CardPlayerBehavior {
	private Card[] hand; // 갖고 있는 카드 
	private int card_count; // 갖고 있는 카드의 장 
	private int max_card;

	public CardPlayer(int max_cards) {
		max_card = max_cards;
		hand = new Card[max_card];
		card_count = 0;
	}

	public boolean wantsACard() {
    	String message = JOptionPane.showInputDialog("카드 한 장을 더 받으시겠습니까? (Y/N)");
    	return message.equals("Y");
    }

	public boolean receiveCard(Card c) {
		if (card_count < hand.length) {
			hand[card_count] = c;
			card_count++;
			return true;
		} else {
			return false;
		}
	}

	public Card[] hand() {
		Card[] card = new Card[card_count];
		for (int i = 0; i < card_count; i++)
			card[i] = hand[i];
		return card;
	}
	
	public int totalScore() {
		int total = 0;
		for (int i = 0; i < card_count; i++) {
			if (this.hand[i].rank() == Card.ACE) {
				if (total <= 10) total += Card.ACE + 10;
				else total += Card.ACE;
			}
			else if (this.hand[i].rank() == Card.JACK || this.hand[i].rank() == Card.QUEEN || this.hand[i].rank() == Card.KING) 
				total += 10;
			else total += this.hand[i].rank();
		}
		
		return total;
	}
	
	public void Reset() {
		hand = new Card[max_card];
		card_count = 0;
	}
}
