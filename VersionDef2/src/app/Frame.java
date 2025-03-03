package app;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import obs.FantomeObservable;
import obs.PacmanObservable;
import states.PacmanState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Frame extends JFrame {
	
	private char[][] grille;
	private PacmanObservable pacmanObservable;
	private FantomeObservable fantomeObservable;
	private Jeu jeu;
	private Image coeurImage;
	private int score;
	private int nombreDeVies;

	public Frame(Jeu jeu) {
		this.jeu = jeu;
		jeu.setFrame(this);
		this.grille = jeu.getGrille();
		pacmanObservable = jeu.getPacmanObservable();
		score = pacmanObservable.getScore();
		pacmanObservable = new PacmanObservable(jeu.getGrilleObject());
		fantomeObservable = jeu.getFantomeObservable(); // Initialisation du déplacement des fantômes
		fantomeObservable.startGameLoop(true);
		pacmanObservable = jeu.getPacmanObservable();
		coeurImage = new ImageIcon("RIBEIROGONCALVESAlexandre_CHANMichel\\Image\\heart.png").getImage();
		setTitle("Pacman Game");
		setSize(700, 730);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new PacmanPanel());
		requestFocusInWindow();
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					pacmanObservable.deplacerHaut();
					break;
				case KeyEvent.VK_DOWN:
					pacmanObservable.deplacerBas();
					break;
				case KeyEvent.VK_LEFT:
					pacmanObservable.deplacerGauche();
					break;
				case KeyEvent.VK_RIGHT:
					pacmanObservable.deplacerDroite();
					break;
				default:
					// Autres actions pour les touches non gérées
				}
			}
		});
		setVisible(true);
	}

	private class PacmanPanel extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			setBackground(Color.BLUE);

			int cellSize = getWidth() / grille.length;
			int smallerCellSize = cellSize / 3;

			for (int i = 0; i < grille.length; i++) {
				for (int j = 0; j < grille[i].length; j++) {
					if (grille[i][j] == '#') {
						g.setColor(Color.BLACK); // Contours en noir
						g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
					} else if (grille[i][j] == '&') {
						g.setColor(Color.GRAY); // Contours en noir
						g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
					} else if (grille[i][j] == 'T') {
						g.setColor(Color.WHITE); // Contours en noir
						g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
					} else if (grille[i][j] == '.') {
						g.setColor(Color.cyan); // Contours en noir
						g.fillOval(j * cellSize + cellSize / 3, i * cellSize + cellSize / 3, smallerCellSize,
								smallerCellSize);
					} else if (grille[i][j] == 'P') {
						g.setColor(pacmanObservable.getState().getColor());
						g.fillArc(j * cellSize, i * cellSize, cellSize, cellSize, 45, 270);
					} else if (grille[i][j] == 'V') {
						g.setColor(Color.GREEN); // Couleur verte pour le point vert
						g.fillOval(j * cellSize + cellSize / 3, i * cellSize + cellSize / 3, smallerCellSize,
								smallerCellSize);
					} else if (grille[i][j] == 'O') {
						g.setColor(Color.ORANGE); // Couleur orange pour le point orange
						g.fillOval(j * cellSize + cellSize / 3, i * cellSize + cellSize / 3, smallerCellSize,
								smallerCellSize);
					} else if (grille[i][j] == 'M') {
						g.setColor(Color.MAGENTA); // Couleur violette pour le point violet
						g.fillOval(j * cellSize + cellSize / 3, i * cellSize + cellSize / 3, smallerCellSize,
								smallerCellSize);
					} else if (grille[i][j] == 'F') {
						if (pacmanObservable.getState().getState().equals(PacmanState.State.SUPERPACMAN)) {
							g.setColor(new Color(1, 198, 255));
							g.fillOval(j * cellSize, i * cellSize, cellSize, cellSize);
							int eyeSize = cellSize / 6; // Ajustez la taille des yeux selon vos besoins
							int eye1X = j * cellSize + cellSize / 3 - eyeSize / 2;
							int eye1Y = i * cellSize + cellSize / 4 - eyeSize / 2;
							int eye2X = j * cellSize + 2 * cellSize / 3 - eyeSize / 2;
							int eye2Y = i * cellSize + cellSize / 4 - eyeSize / 2;

							g.setColor(Color.BLACK); // Couleur des yeux
							g.fillOval(eye1X, eye1Y, eyeSize, eyeSize);
							g.fillOval(eye2X, eye2Y, eyeSize, eyeSize);
							int mouthWidth = cellSize / 4; 
							int mouthHeight = cellSize / 6; 
							int mouthX = j * cellSize + cellSize / 2 - mouthWidth / 2;
							int mouthY = i * cellSize + 2 * cellSize / 3;
							g.fillArc(mouthX, mouthY, mouthWidth, mouthHeight, 0, -180);
						} else {
							g.setColor(Color.orange);
							g.fillOval(j * cellSize, i * cellSize, cellSize, cellSize);
							int eyeSize = cellSize / 6;
							int eye1X = j * cellSize + cellSize / 3 - eyeSize / 2;
							int eye1Y = i * cellSize + cellSize / 4 - eyeSize / 2;
							int eye2X = j * cellSize + 2 * cellSize / 3 - eyeSize / 2;
							int eye2Y = i * cellSize + cellSize / 4 - eyeSize / 2;
							g.setColor(Color.BLACK); // Couleur des yeux
							g.fillOval(eye1X, eye1Y, eyeSize, eyeSize);
							g.fillOval(eye2X, eye2Y, eyeSize, eyeSize);
							int mouthWidth = cellSize / 4; 
							int mouthHeight = cellSize / 6;
							int mouthX = j * cellSize + cellSize / 2 - mouthWidth / 2;
							int mouthY = i * cellSize + 2 * cellSize / 3;
							g.fillArc(mouthX, mouthY, mouthWidth, mouthHeight, 0, -180);
						}
					}
				}
			}
			g.setColor(Color.WHITE); // Couleur du texte en blanc
			g.drawString("Score: " + score, 10, 20); // Afficher le score en haut à gauche

			nombreDeVies = pacmanObservable.getNbVies();
			int espaceEntreLesCoeurs = 15;
			int scoreTextWidth = g.getFontMetrics().stringWidth("Score: " + score);
			int coeursX = 15 + scoreTextWidth;
			int coeursY = 0;
			int heartSize= cellSize;
			for (int vie = 0; vie < nombreDeVies; vie++) {
				/*g.drawImage(coeurImage, coeursX + vie * (espaceEntreLesCoeurs + coeurImage.getWidth(null)), coeursY,
						this);*/	
				 g.setColor(Color.RED);
				    g.fillOval(coeursX + vie * (espaceEntreLesCoeurs + heartSize / 4), coeursY, heartSize/2, heartSize / 2);
			}
		}
	}

	public void changeDelayTimer(boolean doubler) {
		if (doubler) {
			this.fantomeObservable.doublerDelay();
		} else {
			this.fantomeObservable.diviserDelay();
		}
	}

	public void updateDisplay() {
		score = pacmanObservable.getScore();
		nombreDeVies = pacmanObservable.getNbVies();
		if (score >= 5000) {
			pacmanObservable.reduireScoreEtAugmenterVie();
		}

		if (nombreDeVies == 0) {
			gameOver();
		} else {
			int pacGommesRestantes = jeu.compterPacGommesRestantes(jeu.getGrille());
			if (pacGommesRestantes == 0) {
				gameGagne();
			}
		}
		repaint();
	}

	public void gameOver() {
		String message = "Game Over! Votre score est : " + score;
		JOptionPane.showOptionDialog(this, message, "Game Over", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, new String[] { "Quitter" }, "Quitter");
		System.exit(0);
	}

	public void gameGagne() {
		int scoreFinal = score + (pacmanObservable.getNbVies() * 5000); // Calcule le score final
		String message = "Félicitations! Vous avez gagné avec un score de : " + scoreFinal;
		JOptionPane.showOptionDialog(this, message, "Victoire!", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, new String[] { "Quitter" }, "Quitter");

		System.exit(0);

	}
}
