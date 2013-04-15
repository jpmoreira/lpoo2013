package logica_jogo;

/**
 * 
 * A random number generator able to operate in a random or non-random way By
 * default behaves as a random generator
 * 
 * 
 * 
 */
public class GameSpecificRanGen extends RandomGenerator implements
		java.io.Serializable {

	private int[] dragonSleepList;
	private int sleepIndex;
	private int[] dragonWakeList;
	private int wakeIndex;
	private int[] dragonMoveList;
	private int moveIndex;

	public boolean dragonShouldSleep() {
		if (trueRandomMode || dragonSleepList == null) {
			return theGen.nextInt(6) == 0;
		} else {
			if (sleepIndex < dragonSleepList.length) {
				return dragonSleepList[sleepIndex++] == 0;
			} else {// repeat sequence again
				sleepIndex = 0;
				return dragonSleepList[sleepIndex++] == 0;
			}
		}
	}

	public boolean dragonShouldWake() {
		if (trueRandomMode || dragonWakeList == null) {
			return theGen.nextInt(3) == 0;
		} else {
			if (wakeIndex < dragonWakeList.length) {
				return dragonWakeList[wakeIndex++] == 0;
			} else {// repeat sequence again
				wakeIndex = 0;
				return dragonWakeList[wakeIndex] == 0;
			}
		}

	}

	public int dragonMoveNumber() {
		if (trueRandomMode || dragonMoveList == null) {
			return theGen.nextInt(4);
		} else {
			if (moveIndex < dragonMoveList.length) {
				return dragonMoveList[moveIndex++];
			} else {// repeat sequence again
				moveIndex = 0;
				return dragonMoveList[moveIndex++];
			}
		}

	}

	@Override
	public void randomModeOn() {
		super.randomModeOn();
		dragonSleepList = null;
		dragonWakeList = null;
		dragonMoveList = null;
	}

	/**
	 * 
	 * A method to turn random mode off.
	 * 
	 * @param sleep
	 *            An array of values that will be used to determine if the
	 *            dragon should sleep. (0 to set to Sleep, other value
	 *            otherwise). After the end of the array is reached the first
	 *            element is returned in an endless loop.
	 * @param wake
	 *            An array of values that will be used to determine if the
	 *            dragon should wake. (0 to set to wake, other value otherwise).
	 *            After the end of the array is reached the first element is
	 *            returned in an endless loop.
	 * @param move
	 *            An array of values that will be used to determine if the
	 *            dragon should sleep. After the end of the array is reached the
	 *            first element is returned in an endless loop.
	 */

	public void randomModeOff(int[] sleep, int[] wake, int[] move) {
		super.randomModeOff();
		dragonSleepList = sleep;
		dragonMoveList = move;
		dragonWakeList = wake;
		sleepIndex = 0;
		moveIndex = 0;
		wakeIndex = 0;
	}

	GameSpecificRanGen() {
		super();
	}
}
