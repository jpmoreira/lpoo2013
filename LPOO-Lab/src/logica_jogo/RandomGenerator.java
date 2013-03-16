package logica_jogo;

import java.util.Random;

public class RandomGenerator {
	
	protected boolean trueRandomMode;
	protected Random theGen;
	
	
	
	public RandomGenerator() {
		trueRandomMode=true;
		theGen=new Random();
	}
	
	public void randomModeOn(){
		trueRandomMode=true;
		
	}
	public void randomModeOff(){
		trueRandomMode=false;
		
		
	}

	public int giveRandom(int minNr, int maxNr){
		return theGen.nextInt(maxNr-minNr+1)+minNr;
	}

}
