public class PurseController {
	private PurseReader reader;
	private PurseWriter writer_won;
	private PurseWriter writer_yen;
	private PurseWriter writer; // remembers active writer private Purse purse_won;
	private Purse purse_won;
	private Purse purse_yen;
	private Purse purse; // remembers active purse
	
	public PurseController(PurseReader r, PurseWriter w1, PurseWriter w2, Purse p1, Purse p2) {
		reader = r;
		writer_won = w1;
		writer_yen = w2;
		purse_won = p1;
		purse_yen = p2;
		writer = w1;
		purse = p1;
		writer_won.showTransaction("활성"); 
		writer_yen.showTransaction("비활성");
	}
	
	public void processTransactions() {
		String message = "+, -, K, J, Q 키 중 하나를 누르고 OK 단추를 누르세요.\n"; 
		message += "수입 +, 지출 -, 한화 K, 일화 J, 종료 Q";
		char request = reader.readRequest(message);
		message = "금액을 입력하고 OK 단추를 누르세요.";
		int amount;
		if (request == 'Q' || request == 'q' ) {
			writer_won.showTransaction("서비스를 마칩니다."); 
			writer_yen.showTransaction("서비스를 마칩니다."); 
			return;
		}
		else if (request == '+') {
			amount = reader.readAmount(message); 
			if (purse.earn(amount))
				writer.showTransaction(amount, "수입"); 
			else
				writer.showTransaction("수입 실패");
		}
		else if (request == '-') {
			amount = reader.readAmount(message); 
			if (purse.spend(amount)) writer.showTransaction(amount, "지출"); 
			else writer.showTransaction("지출 실패");
		}
		else if (request == '>') {
			amount = reader.readAmount(message);
			int amount_in_yen = amount / 10;
			if (purse_won.spend(amount) && purse_yen.earn(amount / 10)) {
				writer_won.showTransaction(amount, "환전 지출");
				writer_yen.showTransaction(amount_in_yen, "환전 수입");
			} else {
				writer_won.showTransaction("환전 실패");
				writer_yen.showTransaction("환전 실패");
			}
		}
		else if (request == '<') {
			amount = reader.readAmount(message);
			int amount_in_won = amount / 10;
			if (purse_yen.spend(amount) && purse_won.earn(amount_in_won)) {
				writer_yen.showTransaction(amount, "환전 지출");
				writer_won.showTransaction(amount_in_won, "환전 수입");
			}
			else {
				writer_won.showTransaction("환전 실패");
				writer_yen.showTransaction("환전 실패");
			}
		}
		else if (request == 'K' || request == 'k') 
			switchPurse(writer_won, purse_won); 
		else if (request == 'J' || request == 'j')
			switchPurse(writer_yen, purse_yen);
		else writer.showTransaction("요청 오류"); this.processTransactions();
	}
	
	private void switchPurse(PurseWriter w, Purse p) { 
		writer.showTransaction("비활성");
		writer = w;
		purse = p;
		writer.showTransaction("활성"); 
	}
}
