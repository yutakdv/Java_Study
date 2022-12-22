import java.time.*;

public class Model {
	public LocalDate hundred_days_from_today() {
		LocalDate today = LocalDate.now();
		LocalDate hundred_plus_day = today.plusDays(100);
		return hundred_plus_day;
	}
	
	public LocalDate hundredDaysFrom(LocalDate d) {
		return d.plusDays(100);
	}
}
