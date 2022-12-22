import java.time.*;

public class Controller {
	public void control(Model m, ViewOut out) {
		LocalDate d = m.hundred_days_from_today();
		out.showDate(d, "오늘부터 100일 뒤는 며칠?\n");
	}
	
	public void control(Model m, ViewIn in, ViewOut out) {
		LocalDate d0 = in.getDate("년, 월,일을 차례로 입력해주세요.");
		LocalDate d100 = m.hundredDaysFrom(d0);
		out.showDate(d0, d100, "부터 100일 뒤는 며칠?\n");
	}
}
