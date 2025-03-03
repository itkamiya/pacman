package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Création de la grille, des placements des gommes, des fantomes et du pacman
 */
public class Grille {
	private final int taille_grille = 25;
	private char[][] grille;
	private static final int NOMBRE_PACMANS_MAX = 1;
	private static final int NOMBRE_FANTOMES_MAX = 4;
	private List<int[]> coordonneesFantomesList = new ArrayList<>();
	private int initPacmanX, initPacmanY;

	public Grille() {
		this.grille = new char[taille_grille][taille_grille];
		initialisation_grille();
		placerPointVertAleatoire();
		placerPointVioletAleatoire();
		placerPointOrangeAleatoire();
		print_console_grille(grille);
	}

	public char[][] getGrille() {
		return grille;
	}

	public int getTailleGrille() {
		return taille_grille;
	}

	public void initialisation_grille() {
		for (int i = 0; i < taille_grille; i++) {
			for (int j = 0; j < taille_grille; j++) {
				if (i == 0 || i == taille_grille - 1 || j == 0 || j == taille_grille - 1) {
					grille[i][j] = '#';
				} else {
					char[] ligne1 = { '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.',
							'.', '.', '.', '.', '.', '.', '.', '.', '#' };
					char[] ligne2 = { '#', '.', '#', '#', '#', '.', '#', '#', '#', '#', '#', '.', '#', '#', '#', '#',
							'#', '#', '.', '#', '.', '#', '#', '.', '#' };
					char[] ligne3 = { '#', '.', '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.',
							'#', '.', '.', '#', '.', '#', '#', '.', '#' };
					char[] ligne4 = { '#', '.', '#', '.', '#', '.', '#', '#', '.', '#', '#', '.', '#', '#', '#', '.',
							'#', '.', '#', '#', '.', '#', '#', '.', '#' };
					char[] ligne5 = { '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.',
							'.', '.', '.', '#', '.', '.', '.', '.', '#' };
					char[] ligne6 = { '#', '.', '#', '#', '#', '.', '#', '.', '#', '#', '.', '#', '.', '.', '#', '#',
							'#', '.', '#', '#', '#', '.', '#', '.', '#' };
					char[] ligne7 = { '#', '.', '.', '.', '.', '.', '#', '.', '.', '.', '.', '#', '.', '.', '.', '.',
							'.', '.', '.', '#', '#', '.', '#', '.', '#' };
					char[] ligne8 = { '#', '.', '#', '#', '#', '.', '#', '.', '#', '.', '.', '#', '#', '.', '#', '#',
							'#', '.', '.', '.', '.', '.', '#', '.', '#' };
					char[] ligne9 = { '#', '.', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.', '.',
							'.', '.', '.', '.', '#', '#', '#', '.', '#' };
					char[] ligne10 = { '#', '.', '#', '#', '.', '#', '.', '.', '.', '.', '#', '.', '.', '#', '.', '.',
							'#', '.', '#', '.', '.', '.', '#', '.', '#' };
					char[] ligne11 = { '#', '#', '#', '.', '.', '.', '.', '#', '#', '#', '#', '&', '&', '#', '.', '.',
							'.', '.', '.', '#', '#', '.', '#', '.', '#' };
					char[] ligne12 = { '#', 'T', '.', '.', '#', '.', '.', '.', '.', '.', '#', '.', '.', '#', '.', '#',
							'#', '#', '#', '#', 'T', '.', '#', '.', '#' };
					char[] ligne13 = { '#', '#', '#', '.', '.', '.', '.', '.', '.', '.', '#', '.', '.', '#', '.', '.',
							'.', '.', '.', '#', '.', '.', '.', '.', '#' };
					char[] ligne14 = { '#', '.', '.', '.', '#', '.', '#', '.', '#', '#', '#', '#', '#', '#', '#', '.',
							'#', '.', '#', '#', '#', '.', '#', '.', '#' };
					char[] ligne15 = { '#', '.', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
							'#', '#', '#', '.', '.', '#', '#', '.', '#' };
					char[] ligne16 = { '#', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.',
							'.', '.', '.', '.', '.', '.', '.', '.', '#' };
					char[] ligne17 = { '#', '.', '#', '#', '#', '.', '#', '#', '#', '#', '#', '#', '.', '#', '#', '#',
							'.', '#', '#', '#', '.', '#', '#', '.', '#' };
					char[] ligne18 = { '#', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.', '.', '.', '#',
							'.', '.', '.', '.', '.', '.', '.', '.', '#' };
					char[] ligne19 = { '#', '.', '#', '#', '#', '.', '#', '#', '#', '#', '.', '#', '.', '#', '.', '#',
							'#', '.', '#', '.', '#', '.', '#', '.', '#' };
					char[] ligne20 = { '#', '.', '#', '.', '.', '.', '.', '.', '#', '#', '.', '.', '.', '.', '.', '#',
							'.', '.', '.', '.', '.', '.', '.', '.', '#' };
					char[] ligne21 = { '#', '.', '#', '#', '#', '.', '#', '#', '#', '#', '.', '#', '#', '#', '#', '#',
							'.', '#', '#', '#', '.', '#', '#', '.', '#' };
					char[] ligne22 = { '#', '.', '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.',
							'.', '.', '.', '.', '.', '#', '#', '#', '#' };
					char[] ligne23 = { '#', '.', '.', '.', '#', '.', '.', '#', '#', '#', '#', '.', '.', '.', '#', '.',
							'#', '#', '#', '#', '.', '.', '.', '.', '#' };
					grille[1] = ligne1;
					grille[2] = ligne2;
					grille[3] = ligne3;
					grille[4] = ligne4;
					grille[5] = ligne5;
					grille[6] = ligne6;
					grille[7] = ligne7;
					grille[8] = ligne8;
					grille[9] = ligne9;
					grille[10] = ligne10;
					grille[11] = ligne11;
					grille[12] = ligne12;
					grille[13] = ligne13;
					grille[14] = ligne14;
					grille[15] = ligne15;
					grille[16] = ligne16;
					grille[17] = ligne17;
					grille[18] = ligne18;
					grille[19] = ligne19;
					grille[20] = ligne20;
					grille[21] = ligne21;
					grille[22] = ligne22;
					grille[23] = ligne23;
				}
			}
		}
	}

	public static void print_console_grille(char[][] grille) {
		// Parcourir la grille et afficher chaque élément
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[i].length; j++) {
				// System.out.print(grille[i][j] + " "); // Afficher chaque élément suivi d'un
				// espace
			}
			// System.out.println(); // Passer à la ligne suivante après avoir imprimé une
			// ligne complète
		}
	}

	public void placerPointVertAleatoire() {
		placerPacGommeAleatoire('V');
	}

	public void placerPointOrangeAleatoire() {
		placerPacGommeAleatoire('O');
	}

	public void placerPointVioletAleatoire() {
		placerPacGommeAleatoire('M');
	}

	private void placerPacGommeAleatoire(char couleur) {
		Random random = new Random();
		int pacGommeX;
		int pacGommeY;

		while (true) {
			pacGommeX = random.nextInt(taille_grille);
			pacGommeY = random.nextInt(taille_grille);

			if (grille[pacGommeX][pacGommeY] == '.' && grille[pacGommeX][pacGommeX] != grille[12][11]
					&& grille[pacGommeX][pacGommeX] != grille[13][11] && grille[pacGommeX][pacGommeX] != grille[12][12]
					&& grille[pacGommeX][pacGommeX] != grille[13][12]) {
				grille[pacGommeX][pacGommeY] = couleur;
				break;
			}
		}
	}

	public void genererPacmansEtFantomesAleatoires() {
		placerPersonnagesAleatoires('P', NOMBRE_PACMANS_MAX);
		placerFantomesPrecis('F', NOMBRE_FANTOMES_MAX);
	}

	public void placerPersonnagesAleatoires(char symbole, int nombrePersonnages) {
		Random random = new Random();

		for (int i = 0; i < nombrePersonnages; i++) {
			int x, y;

			do {
				x = random.nextInt(taille_grille);
				y = random.nextInt(taille_grille);
			} while (!estValide(x, y) && grille[x][y] != 'B' && grille[x][y] != 'V' && grille[x][y] != 'O'
					&& grille[x][y] != 'M' && (grille[x][y] != '&') && (grille[x][y] != grille[12][11])
					&& (grille[x][y] != grille[13][11]) && (grille[x][y] != grille[12][12])
					&& (grille[x][y] != grille[13][12]));

			grille[x][y] = symbole;

			if (symbole == 'P') {
				initPacmanX = x; // Mettre à jour la coordonnée X de Pac-Man
				initPacmanY = y; // Mettre à jour la coordonnée Y de Pac-Man
			}
		}
	}

	private void placerFantomesPrecis(char symbole, int nombreFantomes) {
		// Coordonnées précises pour les fantômes
		int[][] coordonneesFantomes = { { 12, 11 }, // Coordonnées du premier fantôme
				{ 13, 11 }, // Coordonnées du deuxième fantôme
				{ 12, 12 }, // Coordonnées du troisième fantôme
				{ 13, 12 } // Coordonnées du quatrième fantôme
		};

		for (int i = 0; i < nombreFantomes; i++) {
			int x = coordonneesFantomes[i][0];
			int y = coordonneesFantomes[i][1];

			if (estValide(x, y)) {
				grille[x][y] = symbole;

				// Ajouter les coordonnées du fantôme à la liste
				coordonneesFantomesList.add(new int[] { x, y });
			}
		}
	}

	public int[][] getCoordonneesFantomes() {
		int[][] coordonneesFantomes = new int[coordonneesFantomesList.size()][2];
		for (int i = 0; i < coordonneesFantomesList.size(); i++) {
			coordonneesFantomes[i] = coordonneesFantomesList.get(i);
		}
		return coordonneesFantomes;
	}

	public void setCoordonneesFantome(int index, int x, int y) {
		if (index >= 0 && index < coordonneesFantomesList.size()) {
			int[] coordonneesFantome = coordonneesFantomesList.get(index);
			coordonneesFantome[0] = x;
			coordonneesFantome[1] = y;
		}
	}

	private boolean estValide(int x, int y) {
		return x >= 0 && x < taille_grille && y >= 0 && y < taille_grille
				&& (grille[x][y] != '#' && grille[x][y] != 'T');
	}

	public void reinitialiserFantomes() {
		int[][] coordonneesFantomes = getCoordonneesFantomes();
		for (int i = 0; i < coordonneesFantomes.length; i++) {
			int xInitial = coordonneesFantomes[i][0];
			int yInitial = coordonneesFantomes[i][1];
			grille[coordonneesFantomes[i][0]][coordonneesFantomes[i][1]] = '.';
			grille[xInitial][yInitial] = 'F';
			setCoordonneesFantome(i, xInitial, yInitial);
		}
	}

	public int getPacmanX() {
		return initPacmanX;
	}

	public int getPacmanY() {
		return initPacmanY;
	}

}
