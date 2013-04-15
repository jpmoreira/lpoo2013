package logica_jogo;

/**
 * A class that implements the functionalities of required of a Dragon object.
 * 
 * 
 * 
 */

public class Dragon extends Character implements java.io.Serializable {

	// TODO typedef for placeholder instead of hardcoding them
	private boolean sleeping;

	public Dragon(int x, int y) {
		super('D', x, y);
		sleeping = false;
	}

	public boolean isSleeping() {
		return sleeping;
	}

	public void sleep() {
		sleeping = true;
		placeHolder = 'd';
	}

	public void awake() {
		sleeping = false;
		placeHolder = 'D';
	}

	public void fortify() {
		placeHolder = 'F';
	}

	public void defortify() {
		placeHolder = 'D';
		sleeping = false;

	}
}
