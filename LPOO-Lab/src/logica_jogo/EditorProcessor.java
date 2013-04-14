package logica_jogo;

public class EditorProcessor {
	public enum ErrorCode {
	    NoExit,
	    NotEnoughSpace,
	    NoErr
	}
	
	static char[][] theLab;
	static ErrorCode errorCode;
	
	
	
	private static Coordinate[] getPositions (char[][] lab){
	
		theLab=lab;
		
		int nrOfDragons=getNrOfDragons(theLab);
		int emptyPlaces=0;
		Coordinate[] positionsArray=new Coordinate[nrOfDragons+2];//first position for hero second for sword, following 
		int nrOfDragonsParsed=0;
		
		for(int i=0;i<theLab.length;i++){
			for(int f=0;f<theLab.length;f++){
				switch (theLab[i][f]) {
				case 'H':
					positionsArray[0]=new Coordinate(f,i);
					break;
				case 'E':
					positionsArray[1]=new Coordinate(f,i);
					break;
				case 'D':
					positionsArray[2+nrOfDragonsParsed++]=new Coordinate(f,i);
					break;
				default:
					break;
				}
				
				if(theLab[i][f]!='x'){//clean everything but the walls
					theLab[i][f]=' ';
					++emptyPlaces;
				}
				
			}
		}
		
		if(positionsArray[0]==null){
			positionsArray[0]=new Coordinate(0,0);
		}
		if(positionsArray[1]==null){
			positionsArray[1]=new Coordinate(0,0);
		}
		
		
		if(emptyPlaces>=nrOfDragons+2){//if enough empty places
			return positionsArray;			
		}
		
		
		
		
		return null;
		
		
	}
	
	private static int getNrOfDragons(char[][] theLab){
		int nrOfDragons=0;
		
		for(int i=0;i<theLab.length;i++){
			for(int f=0;f<theLab.length;f++){
				if(theLab[i][f]=='D'){
					nrOfDragons++;
				}
			}
		}
		return nrOfDragons;
	}
	
	private static char[][] getCleanedLab(){
		return theLab;
	}

	public static Tabuleiro generateInitializedTab(char[][] labToParse){
		Coordinate[] positions=getPositions(labToParse);
		if(positions==null){//verify if there is enough empty positions
			errorCode=ErrorCode.NotEnoughSpace;
			return null;
		}
		Dragon[] dragons= getDragons(positions.length-2,positions);
		
		//TODO fill dragons array!!!
		int ret=updateLabWithExit();
		if(ret!=0){//verify if there's an exit
			errorCode=ErrorCode.NoExit;
			return null;
		}
		//TODO change mode
		Tabuleiro theTab=new Tabuleiro(positions[0].getX(), positions[0].getY(), positions[1].getX(), positions[1].getY(), theLab, 1, dragons.length);
		theTab.setDragonsArray(dragons);
		errorCode=ErrorCode.NoErr;
		return theTab;
	}
	
	private static int updateLabWithExit(){
		
		for(int i=0;i<theLab.length;i++){
			if(theLab[0][i]==' '){
				theLab[0][i]='S';
				return 0;
			}
		}
		for(int i=0;i<theLab.length;i++){
			if(theLab[theLab.length-1][i]==' '){
				theLab[theLab.length-1][i]='S';
				return 0;
			}
		}
		for(int i=0;i<theLab.length;i++){
			if(theLab[i][0]==' '){
				theLab[i][0]='S';
				return 0;
			}
		}
		for(int i=0;i<theLab.length;i++){
			if(theLab[i][theLab.length-1]==' '){
				theLab[i][theLab.length-1]='S';
				return 0;
			}
		}
		return 1;
	}
	public static ErrorCode getErrorCode(){
		return errorCode;
	}
	
	private static Dragon[] getDragons(int len,Coordinate[] coords){
		Dragon[] dragons=new Dragon[len];
		for(int i=0;i<dragons.length;i++){
			Coordinate pos=coords[i+2];
			dragons[i]=new Dragon(pos.getX(),pos.getY());
		}
		return dragons;
	} 
}
