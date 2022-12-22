package hello;

public class Controller {
	public void control(model m, view v) {
		String message = m.createMessage();
		v.show(message);
		v.showSwing(message);
	}
}
