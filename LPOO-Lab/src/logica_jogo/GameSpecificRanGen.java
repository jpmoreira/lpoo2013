package logica_jogo;

import java.io.Serializable;

public class GameSpecificRanGen extends RandomGenerator implements Serializable{

	private int[] dragonSleepList;
	private int sleepIndex;
	private int[] dragonWakeList;
	private int wakeIndex;
	private int[] dragonMoveList;
	private int moveIndex;
	
	
	
	public boolean dragonShouldSleep(){
		if(trueRandomMode || dragonSleepList==null){
			return theGen.nextInt(6)==0;
		}
		else{
			if(sleepIndex<dragonSleepList.length){
				return dragonSleepList[sleepIndex++]==0; 				
			}
			else{//repeat sequence again
				sleepIndex=0;
				return dragonSleepList[sleepIndex++]==0;
			}
		}
	}
	public boolean dragonShouldWake(){
		if(trueRandomMode || dragonWakeList==null){
			return theGen.nextInt(3)==0;
		}
		else{
			if(wakeIndex<dragonWakeList.length){
				return dragonWakeList[wakeIndex++]==0;
			}
			else{//repeat sequence again
				wakeIndex=0;
				return dragonWakeList[wakeIndex]==0;
			}
		}
		
	}
	public int dragonMoveNumber(){
		if(trueRandomMode || dragonMoveList==null){
			return theGen.nextInt(4);
		}
		else{
			if(moveIndex<dragonMoveList.length){
				return dragonMoveList[moveIndex++];				
			}
			else{//repeat sequence again
				moveIndex=0;
				return dragonMoveList[moveIndex++];
			}
		}
		
	}
	
	@Override
	public void randomModeOn() {
		super.randomModeOn();
		dragonSleepList=null;
		dragonWakeList=null;
		dragonMoveList=null;
	}

	public void randomModeOff(int[] sleep, int[] wake, int[] move) {
		super.randomModeOff();
		dragonSleepList=sleep;
		dragonMoveList=move;
		dragonWakeList=wake;
		sleepIndex=0;
		moveIndex=0;
		wakeIndex=0;
	}

	GameSpecificRanGen(){
		super();
	}
}
