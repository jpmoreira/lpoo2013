package logica_jogo;

import algorithms.dijkstra.engine.DijkstraAlgorithm;

public class Eagle extends Character {

	/** True if eagle is waiting on position or hero shoulder */
	private boolean sleeping;
	/** True if eagle is attached to hero shoulder */
	private boolean attached;
	private boolean reached = false;

	private boolean lastx = false;
	/** Coordinates of the Eagle when is sleeping */
	private int[] pos;

	public Eagle(int x, int y) {
		super('E', x, y);

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

	public void sleep() {
		sleeping = true;
	}

	/* TODO: Adapt Diijkstra Algorithm and improvate later to A* */

	public void getSword(int x, int y) {
		sleeping = false;
		attached = false;
		if (!reached) {
			if (this.getX() == x && this.getY() == y) {
				reached = true;

			}

			if (!reached && this.getX() > x && this.getY() > y) {
				this.moveTo(this.getX() - 1, this.getY() - 1);
			} else if (!reached && this.getX() < x && this.getY() < y) {
				this.moveTo(this.getX() + 1, this.getY() + 1);
			} else if (!reached && this.getX() < x && this.getY() == y) {
				this.moveTo(this.getX() + 1, this.getY());
			} else if (!reached && this.getX() > x && this.getY() == y) {
				this.moveTo(this.getX() - 1, this.getY());
			} else if (!reached && this.getY() < y && this.getX() == x) {
				this.moveTo(this.getX(), this.getY() + 1);

			} else if (!reached && this.getY() > y && this.getX() == x) {
				this.moveTo(this.getX(), this.getY() - 1);

			}
		}
		if (reached){
			
		}
	}

}
