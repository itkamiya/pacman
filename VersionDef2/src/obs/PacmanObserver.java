package obs;

import app.Frame;

public class PacmanObserver extends SuperObserver {

	public PacmanObserver(Frame frame) {
		super(frame);
	}

	public void update() {
		super.update();
	}
	
	public void updateDelayForFantome(boolean doubler) {
		this.frame.changeDelayTimer(doubler);
	}

}
