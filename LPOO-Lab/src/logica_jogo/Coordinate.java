package logica_jogo;

public class Coordinate {
	
	
	private static int MAX_X;
	private static int MAX_Y;
	
	private int x;
	private int y;
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int theX){
		x=theX;
	}
	public void setY(int theY){
		y=theY;
	}

	public void setXandY(int theX, int theY){
		setX(theX);
		setY(theY);
	}
	public Coordinate(int theX, int theY) {
		setX(theX);
		setY(theY);
		
	}
	public Coordinate(Coordinate c){
		x=c.x;
		y=c.y;
	}
public static boolean validCoordinate(int x, int y){
		
		if(x>=0 && x<=MAX_X && y>=0 && y<=MAX_Y){
			return true;
		}
		
		return false;
	}
	public static boolean validCoordinate(Coordinate point){
		return (point.getX()>=0 && point.getX()<=MAX_X && point.getY()>=0 && point.getY()<=MAX_Y);
	}
	public static void setBounds(int x_max,int y_max){

		MAX_X=x_max;
		MAX_Y=y_max;
	}
	public static Coordinate getBounds(){
		return new Coordinate(MAX_X, MAX_Y);
	}
	@Override
	public boolean equals(Object obj) {
		System.out.println("equals");
		if(obj instanceof Coordinate){
			//System.out.println("comparing this.x= "+x+" this.y= "+y+" other.x="+((Coordinate) obj).x+" other.y= "+((Coordinate) obj).y);
			return (x==((Coordinate) obj).x && y==((Coordinate) obj).y);
		}
		return false;
	}
	public Coordinate clone(){
		Coordinate newCord=new Coordinate(x, y);
		return newCord;
	}
}
