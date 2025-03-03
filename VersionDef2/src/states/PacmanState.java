package states;

import java.awt.Color;

public abstract class PacmanState {
	
	protected int TIMEOUT;
	
	public static enum State {
		NORMAL, INVISIBLE, SUPERPACMAN
	};

	public abstract State getState();

	public abstract Color getColor();
	
	public abstract int getTimeout();
	
}
