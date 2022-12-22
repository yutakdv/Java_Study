import javax.swing.*;

public class BlackjackController {
	private Dealer dealer;
	private HumanPlayer hand_player;
	private ComputerPlayer hand_dealer;
	
	public BlackjackController(Dealer d) {
		dealer = d;
		String name = JOptionPane.showInputDialog("이름을 입력해주세요."); 
		hand_player = new HumanPlayer(Card.SIZE_OF_ONE_SUIT, name);
		hand_dealer = new ComputerPlayer(Card.SIZE_OF_ONE_SUIT);
		manageBlackjack();
	}
	
	public void manageBlackjack() {
		CardDeck c = new CardDeck();
		for (int i = 0; i < 2; i++) {
			hand_player.receiveCard(c.newCard());
			hand_dealer.receiveCard(c.newCard());
		}
		
		System.out.print("HumanPlayer first & second Card : " + "(" + hand_player.hand()[0].suit() + ", " + hand_player.hand()[0].rank() + ")");
		System.out.println(" (" + hand_player.hand()[1].suit() + ", " + hand_player.hand()[1].rank() + ")");
		System.out.println("ComputerPlayer second Card : " + "(" + hand_dealer.hand()[1].suit() + ", " + hand_dealer.hand()[1].rank() + ")");
		
		if (hand_player.totalScore() == 21) { 
			hand_player.youWinBlackjack();
		}
		else if (hand_player.totalScore() < 21) {
			while (hand_player.totalScore() < 21 && hand_player.wantsACard()) {
				dealer.dealOneTo(hand_player);
				
				System.out.print("HumanPlayer : ");
				for (int i = 0; i < hand_player.hand().length; i++)
					System.out.print("(" + hand_player.hand()[i].suit() + ", " + hand_player.hand()[i].rank() + ") ");
				System.out.println();
				
				System.out.print("ComputerPlayer : ");
				for (int i = 1; i < hand_dealer.hand().length; i++)
					System.out.print("(" + hand_dealer.hand()[i].suit() + ", " + hand_dealer.hand()[i].rank() + ") ");
				System.out.println();
			}
			
			if (hand_player.totalScore() > 21) {
				hand_player.youLose();
			}
			else if (hand_player.totalScore() <= hand_dealer.totalScore()) hand_player.youLose();
			else if (hand_player.totalScore() == 21) hand_player.youWin();
			else if (hand_dealer.totalScore() <= 16) {
				while (hand_dealer.totalScore() <= 16) {
					dealer.dealOneTo(hand_dealer);
					
					System.out.print("HumanPlayer : ");
					for (int i = 0; i < hand_player.hand().length; i++)
						System.out.print("(" + hand_player.hand()[i].suit() + ", " + hand_player.hand()[i].rank() + ") ");
					System.out.println();
					
					System.out.print("ComputerPlayer : ");
					for (int i = 1; i < hand_dealer.hand().length; i++)
						System.out.print("(" + hand_dealer.hand()[i].suit() + ", " + hand_dealer.hand()[i].rank() + ") ");
					System.out.println();
				}
				
				if (hand_dealer.totalScore() > 21) {
					hand_player.youWin();
				}
				else if (hand_dealer.totalScore() == hand_player.totalScore()) {
					hand_player.youDraw();
				}
				else if (hand_player.totalScore() > hand_dealer.totalScore()) {
					hand_player.youWin();
				}
				else {
					hand_player.youLose();
				}
			}
		}
		
		System.out.println("Chips : " + hand_player.chips());
		System.out.println();
		
		String request = JOptionPane.showInputDialog("계속하시겠습니까? (Y / N)");
		if (request.equals("Y")) {
			hand_player.Reset();
			hand_dealer.Reset();
			manageBlackjack();
		} else System.exit(0);
	}
}
