package obs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import app.Grille;
import app.Teleportation;
import states.*;

public class PacmanObservable extends SuperObservable {

	private int score, nb_vie;
	private PacmanState state;
	private Teleportation teleportation; // Gestion des emplacements de téléportation
	private int pacmanAncienneX; // Pour stocker l'ancienne position X de Pac-Man
	private int pacmanAncienneY; // Pour stocker l'ancienne position Y de Pac-Man
	
	public PacmanObservable(Grille grille) {
		super(grille);
		this.score = 0;
		this.nb_vie = 3; // vie par defaut
		this.state = new NormalPacman(); // par defaut
		this.teleportation = new Teleportation(grille.getGrille());
		initialiserPositionPacman();
	}

	public int getScore() {
		return score;
	}

	public void setScore(int newScore) {
		score = newScore;
	}

	public int getNbVies() {
		return nb_vie;
	}

	public void perdreNVies(int n) {
		nb_vie = nb_vie - n;
	}

	public void gagnerNVies(int n) {
		nb_vie += n;
	}

	public void reduireScoreEtAugmenterVie() {
		if (score >= 5000) {
			setScore(score - 5000); // Réduire le score de 5000
			gagnerNVies(1); // Augmenter le nombre de vies de 1
		}
	}

	public void collecterPoint(char[][] grille, int x, int y) {
		char cellule = grille[x][y];
		int pointsGagnes = 0;
		if (cellule == '.') {
			pointsGagnes = 100; // Collecter un point donne 10 points
		} else if (cellule == 'M') {
			pointsGagnes = 300; // Collecter un autre objet donne 300 points
		} else if (cellule == 'O') {
			pointsGagnes = 500; // Collecter un objet O donne 500 points
		} else if (cellule == 'V') {
			grille[11][11] = ' ';
			grille[11][12] = ' ';
			grille[15][11] = ' ';
			grille[15][12] = ' ';
			grille[14][11] = ' ';
			grille[14][12] = ' ';
			pointsGagnes = 1000; // Collecter un objet V donne 1000 points
		}
		setScore(getScore() + pointsGagnes);
		grille[x][y] = ' ';
	}

	@Override
	public boolean estValide(int x, int y) {
		char[][] grille = this.grille.getGrille();
		return x >= 0 && x < grille.length && y >= 0 && y < grille[0].length && grille[x][y] != '#'
				&& grille[x][y] != '&';
	}
	
	private void initialiserPositionPacman() {
		positionX = grille.getPacmanX();
		positionY = grille.getPacmanY();
	}
	
	public void setPacmanPosition(int x, int y) {
		pacmanAncienneX = positionX; // Stocker l'ancienne position X
		pacmanAncienneY = positionY; // Stocker l'ancienne position Y
		positionX = x; // Mettre à jour la nouvelle position X
		positionY = y; // Mettre à jour la nouvelle position Y
	}

	public int getPacmanAncienneX() {
		return pacmanAncienneX;
	}

	public int getPacmanAncienneY() {
		return pacmanAncienneY;
	}
	
	public PacmanState getState() {
		return this.state;
	}

	/**
	 * Déplace Pac-Man vers le haut.
	 */
	public void deplacerHaut() {
		deplacerPacman(-1, 0);
	}

	/**
	 * Déplace Pac-Man vers le bas.
	 */
	public void deplacerBas() {
		deplacerPacman(1, 0);
	}

	/**
	 * Déplace Pac-Man vers la gauche.
	 */
	public void deplacerGauche() {
		deplacerPacman(0, -1);
	}

	/**
	 * Déplace Pac-Man vers la droite.
	 */
	public void deplacerDroite() {
		deplacerPacman(0, 1);
	}

	private void deplacerPacman(int deltaX, int deltaY) {
		int nouvellePositionX = positionX + deltaX;
		int nouvellePositionY = positionY + deltaY;

		if (estValide(nouvellePositionX, nouvellePositionY)) {
			char[][] grille = this.grille.getGrille();

			if (grille[nouvellePositionX][nouvellePositionY] == 'T') {
				grille[positionX][positionY] = ' ';
				setPacmanPosition(nouvellePositionX, nouvellePositionY);
				teleportation.ChercherEtTeleporter(this);
			} else if (grille[nouvellePositionX][nouvellePositionY] == 'F') {
				if (!this.state.getState().equals(PacmanState.State.INVISIBLE) && !this.state.getState().equals(PacmanState.State.SUPERPACMAN)) {
					this.perdreNVies(1);
					this.grille.reinitialiserFantomes();
					reinitialiserPacman();
				} else if (this.state.getState().equals(PacmanState.State.SUPERPACMAN)) {
					int[][] coordonneesFantomes = this.grille.getCoordonneesFantomes();
					for (int i = 0; i < coordonneesFantomes.length; i++) {
						int positionX = coordonneesFantomes[i][0];
						int positionY = coordonneesFantomes[i][1];
						if (nouvellePositionX == positionX && nouvellePositionY == positionY) {
							coordonneesFantomes[i][0] = 12;
							coordonneesFantomes[i][1] = 11;
						}
					}
					grille[12][11] = 'F';
					this.deplacementClassic(nouvellePositionX, nouvellePositionY, false);
				} else {
					this.deplacementClassic(nouvellePositionX, nouvellePositionY, true);
				}
			} else if (grille[nouvellePositionX][nouvellePositionY] == 'O') {
				this.state = new SuperPacman();
				notifyObserverDelay(true);
				Timer couleurTemporaireTimer = new Timer(this.state.getTimeout(), new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						notifyObserverDelay(false);
						state = new NormalPacman();
					}
				});
				couleurTemporaireTimer.setRepeats(false);
				couleurTemporaireTimer.start();

				this.deplacementClassic(nouvellePositionX, nouvellePositionY, false);

			} else if (grille[nouvellePositionX][nouvellePositionY] == 'M') {
				this.state = new InvisiblePacman();
				Timer couleurTemporaireTimer = new Timer(15000, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						state = new NormalPacman();
					}

				});
				couleurTemporaireTimer.setRepeats(false);
				couleurTemporaireTimer.start();

				this.deplacementClassic(nouvellePositionX, nouvellePositionY, false);
			} else {
				this.deplacementClassic(nouvellePositionX, nouvellePositionY, false);
			}
			teleportation.restaurerEmplacementsTeleportation();
			
			this.notifyObserver();
		}
	}
	
	private void deplacementClassic(int nouvellePositionX, int nouvellePositionY, boolean surFantome) {
		if (grille.getGrille()[positionX][positionY] != 'F') {
			grille.getGrille()[positionX][positionY] = ' ';
		}
		this.collecterPoint(grille.getGrille(), nouvellePositionX, nouvellePositionY);
		setPacmanPosition(nouvellePositionX, nouvellePositionY);
		if (!surFantome) {
			grille.getGrille()[nouvellePositionX][nouvellePositionY] = 'P';
		} else {
			grille.getGrille()[nouvellePositionX][nouvellePositionY] = 'F';
		}
	}

	public void reinitialiserPacman() {
		char[][] grille = this.grille.getGrille();
		grille[positionX][positionY] = ' ';
		this.grille.placerPersonnagesAleatoires('P', 1);
		setPacmanPosition(this.grille.getPacmanX(), this.grille.getPacmanY());
		grille[positionX][positionY] = 'P';
	}
	
	private void notifyObserverDelay(boolean doubler) {
		for (SuperObserver observer : observers) {
			((PacmanObserver) observer).updateDelayForFantome(doubler);
		}
	}
}
