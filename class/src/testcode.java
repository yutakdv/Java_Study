import javax.swing.*;

public class testcode {
    public static void main(String[] args) {
  
        String int_number = JOptionPane.showInputDialog("정수를 주세요.");
        int sum = 0;
        int number = Integer.parseInt(int_number);
        if (number < 0) number = -number;
        
        while (number > 0) {
        	sum += number % 10;
        	number /= 10;
        }
        
        boolean result = sum % 9 == 0 ? true : false;
        
        JOptionPane.showMessageDialog(null, "정수 " + int_number + "은(는) 9로 나누어 떨어지냐? " + result);
    }
}
