package logica_jogo;

public class Hero extends Character {
	
	public Hero(int x, int y) {
		super('H',x,y);
	}
	
	public void Arm(){
		placeHolder='A';
	}
	public boolean isArmed(){
		return placeHolder=='A';
	}

}
