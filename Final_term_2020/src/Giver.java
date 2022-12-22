class Giver {
  Purse purse;
  
  public Giver(Purse initial_money) {
	  purse = initial_money;
  }
  
  public int donate(int amount) {
    int answer = 0;
    if (purse.contents() >= amount) {
      purse.extract(amount);
      answer = amount;
    }
    else {
      answer = purse.contents();
      purse.extract(purse.contents());
    }
    return answer;
  }
}

