package logica_jogo;

public class Dragon extends Character {

	private boolean sleeping;
	public Dragon(int x,int y) {
		super('D',x,y);
		sleeping=false;
	}
	public boolean isSleeping(){
		return sleeping;
	}
	public void sleep(){
		sleeping=true;
		placeHolder='d';
	}
	public void awake(){
		sleeping=false;
		placeHolder='D';
	}
	public void fortify(){
		placeHolder='F';
	}
	public void defortify(){
		placeHolder='D';
		sleeping=false;
		
	}
}
