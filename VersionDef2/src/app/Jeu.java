package app;

import obs.FantomeObservable;
import obs.FantomeObserver;
import obs.PacmanObservable;
import obs.PacmanObserver;

public class Jeu {

	private Grille grille;
	private Frame frame;
	private PacmanObservable pacmanObservable;
	private FantomeObservable fantomeObservable;

	public Jeu() {
		this.grille = new Grille();
		this.grille.genererPacmansEtFantomesAleatoires();
		this.pacmanObservable = new PacmanObservable(this.grille);
		this.fantomeObservable = new FantomeObservable(this.grille, pacmanObservable);
		this.fantomeObservable.startGameLoop(false);
		Frame frame = new Frame(this);
        frame.updateDisplay();
        frame.setVisible(true);
        FantomeObserver fobserver = new FantomeObserver(frame);
        this.fantomeObservable.register(fobserver);
        PacmanObserver observer = new PacmanObserver(frame);
        this.pacmanObservable.register(observer);
	}

	public char[][] getGrille() {
		return grille.getGrille();
	}

	public PacmanObservable getPacmanObservable() {
		return pacmanObservable;
	}

	public Grille getGrilleObject() {
		return grille;
	}

	public void mettreAJourLeScore(int nouveauScore) {
		pacmanObservable.setScore(nouveauScore);
		frame.updateDisplay();
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public FantomeObservable getFantomeObservable() {
		return fantomeObservable;
	}

	public int compterPacGommesRestantes(char[][] grille) {
		int pacGommesRestantes = 0;
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[i].length; j++) {
				if (grille[i][j] == '.') {
					pacGommesRestantes++;
				}
			}
		}
		return pacGommesRestantes;
	}
}
