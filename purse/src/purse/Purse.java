package purse;

public class Purse {
	private int balance;
	
	public boolean earn(int amount) {
		if (amount < 0) return false;
		else balance += amount; return true;
	}
	
	public boolean spend(int amount) {
		if (amount < 0 || amount > balance) return false;
		else balance -= amount; return true;
	}
	
	public int getBalance() {
		return balance;
	}
	
	//test
	public static void main(String[] args) {
		Purse p = new Purse();
		System.out.println(p.earn(50000));
		System.out.println(p.spend(25000));
		System.out.println(p.getBalance());
	}
}
