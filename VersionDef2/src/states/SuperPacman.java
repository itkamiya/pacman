package states;

import java.awt.Color;

public class SuperPacman extends PacmanState {

	public State getState() {
		return State.SUPERPACMAN;
	}

	public Color getColor() {
		return new Color(70, 130, 180);
	}

	public int getTimeout() {
		return 15000;
	}
}
