import java.time.LocalDate;

public class ViewOut {
	public void showDate(LocalDate d, String message) {
		int year = d.getYear();
		int month = d.getMonthValue();
		int day = d.getDayOfMonth();
		System.out.print(message);
		System.out.println(year + "년 " + month + "월 " + day + "일 입니다.");
	}
	
	public void showDate(LocalDate d0, LocalDate d100, String message) {
		int year = d0.getYear();
		int month = d0.getMonthValue();
		int day = d0.getDayOfMonth();
		System.out.println(year + "년 " + month + "월 " + day + "일 " + message);
		
		int years = d100.getYear();
		int months = d100.getMonthValue();
		int days = d100.getDayOfMonth();
		System.out.println(years + "년 " + months + "월 " + days + "일 입니다.");
	}
}
