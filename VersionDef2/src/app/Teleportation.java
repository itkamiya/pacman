package app;

import java.util.ArrayList;
import java.util.List;

import obs.PacmanObservable;

/**
 * La classe Teleportation représente les emplacements de téléportation dans la
 * grille du jeu Pac-Man. Elle permet de chercher un emplacement de
 * téléportation et de téléporter Pac-Man vers cet emplacement.
 */
public class Teleportation {
	private char[][] grille; // La grille du jeu Pac-Man
	private List<int[]> emplacementsTeleportation = new ArrayList<>(); // Liste des emplacements de téléportation

	/**
	 * Constructeur de la classe Teleportation.
	 *
	 * @param grille La grille du jeu Pac-Man.
	 */
	public Teleportation(char[][] grille) {
		this.grille = grille;
		initialiserEmplacementsTeleportation();
	}

	/**
	 * Initialise la liste des emplacements de téléportation en parcourant la
	 * grille.
	 */
	private void initialiserEmplacementsTeleportation() {
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[i].length; j++) {
				if (grille[i][j] == 'T') {
					int[] emplacement = { i, j };
					emplacementsTeleportation.add(emplacement);
				}
			}
		}
	}

	/**
	 * Récupère la liste des emplacements de téléportation.
	 *
	 * @return Une liste d'entiers représentant les emplacements de téléportation
	 *         sous forme de coordonnées [x, y].
	 */
	public List<int[]> getEmplacementsTeleportation() {
		return emplacementsTeleportation;
	}

	/**
	 * Restaure les emplacements de téléportation en remettant le caractère 'T' dans
	 * la grille.
	 */
	public void restaurerEmplacementsTeleportation() {
		for (int[] emplacement : emplacementsTeleportation) {
			int emplacementX = emplacement[0];
			int emplacementY = emplacement[1];
			grille[emplacementX][emplacementY] = 'T';
		}
	}

	/**
	 * Cherche un emplacement de téléportation et téléporte Pac-Man s'il en trouve
	 * un.
	 *
	 * @param deplacement_pacman L'objet de déplacement de Pac-Man.
	 */
	public void ChercherEtTeleporter(PacmanObservable deplacement_pacman) {
		int pacmanX = deplacement_pacman.getPositionX();
		int pacmanY = deplacement_pacman.getPositionY();

		for (int[] emplacement : emplacementsTeleportation) {
			int emplacementX = emplacement[0];
			int emplacementY = emplacement[1];

			if (emplacementX != pacmanX || emplacementY != pacmanY) {
				grille[pacmanX][pacmanY] = ' ';
				deplacement_pacman.setPacmanPosition(emplacementX, emplacementY);
				return;
			}
		}
	}
}
