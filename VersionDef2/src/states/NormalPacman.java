package states;

import java.awt.Color;

public class NormalPacman extends PacmanState {

	public State getState() {
		return State.NORMAL;
	}

	public Color getColor() {
		return new Color(255, 255, 0);
	}

	public int getTimeout() {
		return -1; // -1 pour dire ya pas de fin
	}
}
