package logica_jogo;





public class Eagle extends Character {

	/** True if eagle is waiting on position or hero shoulder */
	private boolean flying;

	/** Coordinates of the Eagle when is sleeping */
	private Coordinate startPos;
	private Coordinate endingPos;
	private boolean hasSword;

	public Eagle(int x, int y) {
		super('e', x, y);
		vanish();
		hasSword=false;
	}

	/**
	 * Updates the Coordinates of the Eagle depending on it relation to the hero
	 * Attached and/or sleeping
	 * 
	 * @param o
	 *            Object o (Hero)
	 * @return Position of eagle after update
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

	/* TODO: Adapt Dijkstra Algorithm and improve later to A* */

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
	

	
	public void reachedSword(){
		System.out.println("reached");
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
