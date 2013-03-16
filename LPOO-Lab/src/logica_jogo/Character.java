package logica_jogo;


//a new Comment
abstract public class Character extends Element{

	
	public Character(char pH,int x, int y) {
		super(pH,x,y);
		
	}
	
	public void moveTo(int theNewX,int theNewY){
		position.setXandY(theNewX, theNewY);
	}

}
