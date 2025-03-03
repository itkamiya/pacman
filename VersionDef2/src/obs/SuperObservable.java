package obs;

import java.util.ArrayList;
import java.util.List;

import app.Grille;

public abstract class SuperObservable {
	protected List<SuperObserver> observers;
	
	protected int positionX, positionY;
    protected char[][] historique;
    protected Grille grille;

	public SuperObservable(Grille grille) {
		this.observers = new ArrayList<SuperObserver>();
		this.grille = grille;
        this.historique = new char[25][25];
	}

	public void register(SuperObserver fobserver) {
		observers.add(fobserver);
	}

	public void unregister(SuperObserver o) {
		observers.remove(o);
	}

	protected void notifyObserver() {
		for (SuperObserver observer : observers) {
			observer.update();
		}
	}
	
	public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }
    
    public abstract boolean estValide(int x, int y);

}
