package logica_jogo;

/**
 * A class intended to be subclassed that handles some of the common routines necessary of an element in a maze
 * 
 *
 *
 */


public class Element implements java.io.Serializable{

	protected char placeHolder;
	protected Coordinate position;
	protected boolean playing;
	protected boolean overlaped;
	
	/**
	 * Simple Element constructor
	 * 
	 * @param pH The placeHolder this element is supposed to have
	 * @param x This element's x value
	 * @param y This element's x value
	 */
	public Element(char pH,int x, int y) {
		placeHolder=pH;
		position=new Coordinate(x, y);
		playing=true;
		overlaped=false;
		
	}
	
	public Coordinate getPosition(){
		return position;
	}
	public int getX(){
		return position.getX();
	}
	public int getY(){
		return position.getY();
	}

	public char getPlaceHolder(){
		return placeHolder;
	}
	public boolean isPlaying(){
		return playing;
	}
	
	/**
	 * A method used to remove an element from the maze. Set's the position to (-1,-1).
	 * 
	 */
	public void vanish(){
		playing=false;
		position.setXandY(-1, -1);
		
	}
	/**
	 * A method used to place an element back in play. Does not set position to previous state.
	 * 
	 */
	public void unvanish(){
		playing=true;
	}
	/**
	 * A method that should be called when a particular element get's overlaped by another element.
	 * 
	 */
	
	public void overlap(){
		overlaped=true;
	}
	
	/**
	 * 
	 * A method that should be called when a particular element is no longer ovelaped by another.
	 * 
	 */
	
	public void unhide(){
		overlaped=false;
	}
	
	public boolean isOverlaped(){
		return overlaped;
	}
	
}
