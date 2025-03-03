package obs;

import java.util.Timer;
import java.util.TimerTask;
import app.Grille;
import states.*;

public class FantomeObservable extends SuperObservable {
	
	private Timer timer;
	private TimerTask gameLoopTask;
	private int delay;
	
	private PacmanObservable pacmanObservable;

	public FantomeObservable(Grille grille, PacmanObservable pacmanObservable) {
		super(grille);
		this.delay = 100;
		this.initGameLoopTask();
		
		this.pacmanObservable = pacmanObservable;
	}

	private void initGameLoopTask() {
		timer = new Timer();
		gameLoopTask = new TimerTask() {
			@Override
			public void run() {
				try {
					deplacerFantome();
				} catch (Exception e) {};
			}
		};
	}
	
	public void doublerDelay() {
    	this.delay = this.delay * 2;
    	this.startGameLoop(true);
    }
    
    public void diviserDelay() {
    	this.delay = this.delay / 2;
    	this.startGameLoop(true);
    }

    public void startGameLoop(boolean needReset) {
    	if (needReset) {
    		timer.cancel();
    		this.initGameLoopTask();
    	}
        timer.scheduleAtFixedRate(gameLoopTask, 0, this.delay);
    }

	public void deplacerFantome() {
		int[][] coordonneesFantomes = grille.getCoordonneesFantomes();
		char[][] grille = this.grille.getGrille();

		for (int i = 0; i < coordonneesFantomes.length; i++) {
			int positionX = coordonneesFantomes[i][0];
			int positionY = coordonneesFantomes[i][1];

			int nouvellePositionX = positionX;
			int nouvellePositionY = positionY;

			// Choisir une nouvelle direction aléatoirement
			int direction = (int) (Math.random() * 4);
			switch (direction) {
				case 0: // Haut
					nouvellePositionX = positionX - 1;
					break;
				case 1: // Bas
					nouvellePositionX = positionX + 1;
					break;
				case 2: // Gauche
					nouvellePositionY = positionY - 1;
					break;
				case 3: // Droite
					nouvellePositionY = positionY + 1;
					break;
				default:
					nouvellePositionX = positionX;
					nouvellePositionY = positionY;
			}

			if (estValide(nouvellePositionX, nouvellePositionY) ) {
				if (pacmanObservable.getPositionX() == positionX && pacmanObservable.getPositionY() == positionY) {
					grille[positionX][positionY] = 'P';
				} else if (historique[positionX][positionY] == 'P') {
					grille[positionX][positionY] = ' ';
				} else {
					grille[positionX][positionY] = historique[positionX][positionY];
				}
				coordonneesFantomes[i][0] = nouvellePositionX;
				coordonneesFantomes[i][1] = nouvellePositionY;
				historique[nouvellePositionX][nouvellePositionY] = grille[nouvellePositionX][nouvellePositionY];
				if (grille[nouvellePositionX][nouvellePositionY] == 'P' && pacmanObservable.getState().getState().equals(PacmanState.State.NORMAL)) {
					pacmanObservable.perdreNVies(1);;
					this.grille.reinitialiserFantomes();
					pacmanObservable.reinitialiserPacman();
					grille[nouvellePositionX][nouvellePositionY] = 'F';
				} else if (pacmanObservable.getState().getState().equals(PacmanState.State.SUPERPACMAN) && grille[nouvellePositionX][nouvellePositionY] == 'P') {
					coordonneesFantomes[i][0] = 12;
					coordonneesFantomes[i][1] = 11;
					grille[nouvellePositionX][nouvellePositionY] = 'P';
				} else {
					grille[nouvellePositionX][nouvellePositionY] = 'F';
				}
			}
			
			this.notifyObserver();
		}
	}
	// Méthode pour vérifier si la nouvelle position est valide
	public boolean estValide(int x, int y) {
		return x >= 0 && x < grille.getGrille().length && y >= 0 && y < grille.getGrille()[0].length && grille.getGrille()[x][y] != '#'
				&& grille.getGrille()[x][y] != 'F';
	}

}
