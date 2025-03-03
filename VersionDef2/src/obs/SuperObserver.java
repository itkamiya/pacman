package obs;

import app.Frame;

public abstract class SuperObserver {

	protected Frame frame;

	public SuperObserver(Frame frame) {
		this.frame = frame;
	}

	public void update() {
		this.frame.updateDisplay();
	}

}
