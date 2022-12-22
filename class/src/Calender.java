import java.time.LocalDate;
import java.time.Period;

public class Calender {

	public static void main(String[] args) {
		LocalDate today = LocalDate.now();
		int year = today.getYear();
		LocalDate xmas = LocalDate.of(year, 12, 25);
		Period p = Period.between(today, xmas);
		int months = p.getMonths();
		int days = p.getDays();
		String message = "크리스마스까지 " + months + "달 " + days + "일 남았다.";
		System.out.println(message);
	}

}
