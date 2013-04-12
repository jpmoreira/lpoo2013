package logica_jogo;

public class Hero extends Character implements java.io.Serializable {
	
	private boolean usedEagle=false;
	
	public Hero(int x, int y) {
		super('H',x,y);
	}
	
	public void Arm(){
		placeHolder='A';
	}
	public void EagleShoulder(){
		// G stands for Guardian / Guarded . Something like that :D
		placeHolder='G';
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
