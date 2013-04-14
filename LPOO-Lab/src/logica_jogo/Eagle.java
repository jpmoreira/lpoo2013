package logica_jogo;


/**
 * 
 * A class that implements the functionalities of required of an Eagle object.
 * 
 *
 *
 */


public class Eagle extends Character {


	private boolean flying;
	private Coordinate startPos;
	private Coordinate endingPos;
	private boolean hasSword;

	/**
	 * Eagle constructor that places eagle at position (-1,-1). Eagle starts as a vanished element.
	 * 
	 */
	public Eagle() {
		super('e', 0, 0);
		vanish();
		hasSword=false;
	}

	/**
	 * A method that initiates the Eagle usage, unvanishing it and placing it at the first position in her way from "h" to "s".
	 * This method should be called before moveEagle.
	 * 
	 * @param h An element the Eagle should use as the starting point.
	 * @param s An element the Eagle should use as the ending point
	 */
	public void StartEagle(Element h,Element s) {
		/* Method that should be called before moveEagle */
		if(!((Hero)h).eagleUsed()&&s.isPlaying()){
		startPos=new Coordinate(h.getPosition());
		endingPos=s.getPosition();
		
		unvanish();
		((Hero) h).useEagle();
		position=new Coordinate(h.getX(), h.getY());
		}
	}

	/**
	 * A function that returns a Coordinate object containing the next position the Eagle will go to.
	 * This function is based on the Bresenham algorithm, therefore no floating point arithmetic is used.
	 * 
	 * @return A Coordinate object representing the position the eagle is supposed to go to.
	 */

	public Coordinate newEaglePos() {
		
		int xf=endingPos.getX();
		int yf=endingPos.getY();
		
		int xi=position.getX();
		int yi=position.getY();

		  int w = xf - xi ;
		    int h = yf - yi ;
		    int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0 ;
		    if (w<0) dx1 = -1 ; else if (w>0) dx1 = 1 ;
		    if (h<0) dy1 = -1 ; else if (h>0) dy1 = 1 ;
		    if (w<0) dx2 = -1 ; else if (w>0) dx2 = 1 ;
		    int longest = Math.abs(w) ;
		    int shortest = Math.abs(h) ;
		    if (!(longest>shortest)) {
		        longest = Math.abs(h) ;
		        shortest = Math.abs(w) ;
		        if (h<0) dy2 = -1 ; else if (h>0) dy2 = 1 ;
		        dx2 = 0 ;            
		    }
		    int numerator = longest >> 1 ;
		        
		        numerator += shortest ;
		        if (!(numerator<longest)) {
		            numerator -= longest ;
		            xi += dx1 ;
		            yi += dy1 ;
		        } else {
		            xi += dx2 ;
		            yi += dy2 ;
		        }
		        return new Coordinate(xi, yi) ;

	}
	
	/**
	 * A function that should be called when the eagle has reached the sword.
	 * This method inverts the starting and ending position and changes the value of hasSword data member.
	 * 
	 */

	
	
	public void reachedSword(){
		Coordinate temp=startPos;
		startPos=endingPos;
		endingPos=temp;
		hasSword=true;
	}
	
	
	
	public boolean isFlying(){
		return flying;
	}
	public boolean hasSword(){
		return hasSword;
	}
	

}
