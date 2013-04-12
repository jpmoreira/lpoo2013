package logica_jogo;



public class Eagle extends Character {

	/** True if eagle is waiting on position or hero shoulder */
	private boolean sleeping;
	/** True if eagle is attached to hero shoulder */
	private boolean attached;
	private boolean reached = false;
	/** Int x y with position of hero when sends eagle */
	private int tempx;
	private int tempy;
	/** Coordinates of the Eagle when is sleeping */
	private int[] pos;

	public Eagle(int x, int y) {
		super('F', x, y);

	}

	/**
	 * Updates the Coordinates of the Eagle depending on it relation to the hero
	 * Attached and/or sleeping
	 * 
	 * @param o
	 *            Object o (Hero)
	 * @return Position of eagle after update
	 */
	public int[] SleepingPos(Object o) {

		if (sleeping && attached) {
			pos[0] = ((Hero) o).getX();
			pos[1] = ((Hero) o).getY();
		}
		this.moveTo(pos[0], pos[1]);
		return pos;
	}

	public void sleep(boolean state) {
		sleeping = state;
	}

	public void StartEagle(Object o) {
		/* Method that should be called before moveEagle */
		tempx = ((Hero) o).getX();
		tempy = ((Hero) o).getY();
	}

	/* TODO: Adapt Dijkstra Algorithm and improve later to A* */

	public void moveEagle(int x, int y) {
		sleeping = false;
		attached = false;

		if (!reached) {
			if (this.getX() == x && this.getY() == y) {
				reached = true;
				/* TODO: Change Tabuleiro method herovsdragon and verifyDragonProximity to work with obj o instead
				 * of harcoded hero */
			}

			if (this.getX() > x && this.getY() > y) {
				this.moveTo(this.getX() - 1, this.getY() - 1);
			} else if (this.getX() < x && this.getY() < y) {
				this.moveTo(this.getX() + 1, this.getY() + 1);
			} else if (this.getX() < x && this.getY() == y) {
				this.moveTo(this.getX() + 1, this.getY());
			} else if (this.getX() > x && this.getY() == y) {
				this.moveTo(this.getX() - 1, this.getY());
			} else if (this.getY() < y && this.getX() == x) {
				this.moveTo(this.getX(), this.getY() + 1);

			} else if (this.getY() > y && this.getX() == x) {
				this.moveTo(this.getX(), this.getY() - 1);

			}
		}
		if (reached) {
			if (this.getX() == tempx && this.getY() == tempy) {
				sleeping = true;
				/* TODO: do the eaglevsdragon too here and change routine to if sleeping be able to be killed by dragon */
			}

			if (this.getX() > tempx && this.getY() > tempy) {
				this.moveTo(this.getX() - 1, this.getY() - 1);
			} else if (this.getX() < tempx && this.getY() < tempy) {
				this.moveTo(this.getX() + 1, this.getY() + 1);
			} else if (this.getX() < tempx && this.getY() == tempy) {
				this.moveTo(this.getX() + 1, this.getY());
			} else if (this.getX() > tempx && this.getY() == tempy) {
				this.moveTo(this.getX() - 1, this.getY());
			} else if (this.getY() < tempy && this.getX() == tempx) {
				this.moveTo(this.getX(), this.getY() + 1);

			} else if (this.getY() > tempy && this.getX() == tempx) {
				this.moveTo(this.getX(), this.getY() - 1);

			}
		}

	}

}
