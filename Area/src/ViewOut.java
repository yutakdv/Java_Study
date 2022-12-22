import java.text.*; // for using DeciamlFormat 
import javax.swing.*;

public class ViewOut {
	public void show(int radius, double area, String precision) {
		DecimalFormat formatter = new DecimalFormat(precision);
		System.out.print("반지름 " + radius + "인 원의 면적은 ");
		System.out.println(formatter.format(area) + " 입니다.");
	}
}
