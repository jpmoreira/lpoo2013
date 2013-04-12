package logica_jogo;


public class Element implements java.io.Serializable{

	protected char placeHolder;
	protected Coordinate position;
	protected boolean playing;
	
	
	public Element(char pH,int x, int y) {
		placeHolder=pH;
		position=new Coordinate(x, y);
		playing=true;
		
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
	public void vanish(){
		playing=false;
		position.setXandY(-1, -1);
		
	}
}
