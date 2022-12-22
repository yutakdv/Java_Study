
public class ScoreManagement {
	private String[] scores;
	
	public ScoreManagement(int amount) {
		scores = new String[amount];
	}
	
	public String[] getScores() {
		return scores;
	}
	
	public double getAverage() {
		double _sum = 0;
		int tmp = 0;
		for (int i = 0; i < scores.length; i++) {
			try {
				if (scores[i].equals("A+")) _sum += 4.5;
				else if (scores[i].equals("A0")) _sum += 4.0;
				else if (scores[i].equals("B+")) _sum += 3.5;
				else _sum += 3.0;
				tmp++;
			}
			catch (NullPointerException e) {}
		}
		return _sum / tmp;
	}
	
}
