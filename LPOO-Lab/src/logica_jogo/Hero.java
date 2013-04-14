package logica_jogo;

/**
 * 
 * A class that implements the functionalities of required of an Hero object.
 * 
 *
 */

public class Hero extends Character implements java.io.Serializable {
	
	private boolean usedEagle=false;
	
	public Hero(int x, int y) {
		super('H',x,y);
	}
	
	public void Arm(){
		placeHolder='A';
	}
	public boolean isArmed(){
		return placeHolder=='A';
	}
	public void useEagle(){
		usedEagle=true;
	}
	public boolean eagleUsed(){
		return usedEagle;
	}

}
