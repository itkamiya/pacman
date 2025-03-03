package states;

import java.awt.Color;

public class InvisiblePacman extends PacmanState {

	public State getState() {
		return State.INVISIBLE;
	}

	public Color getColor() {
		return new Color(255, 250, 205);
	}

	public int getTimeout() {
		return 15000;
	}
}
