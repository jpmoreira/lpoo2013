package logica_jogo;



/**
 * 
 * A subclass of Element that gives some extra functionalities related to Element movements.
 * 
 * 
 *
 */
abstract public class Character extends Element implements java.io.Serializable{

	
	public Character(char pH,int x, int y) {
		super(pH,x,y);
		
	}
	
	
	public void moveTo(int theNewX,int theNewY){
		position.setXandY(theNewX, theNewY);
	}

}
