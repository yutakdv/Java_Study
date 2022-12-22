package hello;

import javax.swing.JOptionPane;

public class view {
	public void show(String message) {
		System.out.println("Hello, World!");
	}
	public void showSwing(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
}
