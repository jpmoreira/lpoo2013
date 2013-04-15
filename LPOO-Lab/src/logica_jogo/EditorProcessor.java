package logica_jogo;

/**
 * 
 * A class that parses and processes the information coming from the editor and
 * creates a new Tabuleiro instance based on the parsed information
 * 
 * 
 * 
 */

public class EditorProcessor {
	public enum ErrorCode {
		NoExit, NotEnoughSpace, NoErr
	}

	static char[][] theLab;
	static ErrorCode errorCode;

	/**
	 * A function that returns an array of Coordinate objects containing the
	 * positions of the various elements inside a given maze.Null is returned if
	 * an error occurs and the errorCode data member is set.
	 * 
	 * 
	 * @param lab
	 *            A char two dimensional array that is supposed to be parsed.
	 * @return An array of Coordinate objects containing the positions of the
	 *         various elements inside the maze. The first element is the
	 *         position of the hero. The second element is the position of the
	 *         Sword. The following ones (if there are any) are the various
	 *         Dragons' positions.
	 */

	private static Coordinate[] getPositions(char[][] lab) {

		theLab = lab;

		int nrOfDragons = getNrOfDragons(theLab);
		int emptyPlaces = 0;
		Coordinate[] positionsArray = new Coordinate[nrOfDragons + 2];// first
																		// position
																		// for
																		// hero
																		// second
																		// for
																		// sword,
																		// following
		int nrOfDragonsParsed = 0;

		for (int i = 0; i < theLab.length; i++) {
			for (int f = 0; f < theLab.length; f++) {
				switch (theLab[i][f]) {
				case 'H':
					positionsArray[0] = new Coordinate(f, i);
					break;
				case 'E':
					positionsArray[1] = new Coordinate(f, i);
					break;
				case 'D':
					positionsArray[2 + nrOfDragonsParsed++] = new Coordinate(f,
							i);
					break;
				default:
					break;
				}

				if (theLab[i][f] != 'x') {// clean everything but the walls
					theLab[i][f] = ' ';
					++emptyPlaces;
				}

			}
		}

		if (positionsArray[0] == null) {
			positionsArray[0] = new Coordinate(0, 0);
		}
		if (positionsArray[1] == null) {
			positionsArray[1] = new Coordinate(0, 0);
		}

		if (emptyPlaces >= nrOfDragons + 2) {// if enough empty places
			return positionsArray;
		}

		errorCode = ErrorCode.NotEnoughSpace;
		return null;

	}

	/**
	 * 
	 * A method that return the number of Dragons inside a given maze.
	 * 
	 * @param theLab
	 *            The maze to be parsed.
	 * @return The number of dragons inside a given maze
	 */

	private static int getNrOfDragons(char[][] theLab) {
		int nrOfDragons = 0;

		for (int i = 0; i < theLab.length; i++) {
			for (int f = 0; f < theLab.length; f++) {
				if (theLab[i][f] == 'D') {
					nrOfDragons++;
				}
			}
		}
		return nrOfDragons;
	}

	private static char[][] getCleanedLab() {
		return theLab;
	}

	/**
	 * A method that generates an Initialized Tabuleiro with the contents of a
	 * given maze. null is returned if an error occurs and the errorCode data
	 * member is set. This value can be used to check for errors during the
	 * execution of this method.
	 * 
	 * @param labToParse
	 *            The maze to be parsed.
	 * @return An object of type Tabuleiro initialized with the contents of a
	 *         given maze.
	 */

	public static Tabuleiro generateInitializedTab(char[][] labToParse, int theMode) {
		Coordinate[] positions = getPositions(labToParse);
		if (errorCode == ErrorCode.NotEnoughSpace) {// verify if there is enough
			System.out.println("no space");										// empty positions
			return null;
		}
		Dragon[] dragons = getDragons(positions.length - 2, positions);

		updateLabWithExit();//TODO change in corners cant have exit
		if (errorCode == ErrorCode.NoExit) {// verify if there's an exit
			System.out.println("no exit");
			return null;
		}
		Tabuleiro theTab = new Tabuleiro(positions[0].getX(),
				positions[0].getY(), positions[1].getX(), positions[1].getY(),
				theLab, theMode, dragons.length);
		theTab.setDragonsArray(dragons);
		errorCode = ErrorCode.NoErr;
		return theTab;
	}

	/**
	 * This method creates an exit on the maze currently being processed.
	 * errorCode variable is set if an error occurs.
	 * 
	 */

	private static void updateLabWithExit() {

		for (int i = 1; i < theLab.length-1; i++) {
			if (theLab[0][i] == ' ') {
				theLab[0][i] = 'S';
				errorCode = ErrorCode.NoErr;
				return;
			}
		}
		for (int i = 1; i < theLab.length-1; i++) {
			if (theLab[theLab.length - 1][i] == ' ') {
				theLab[theLab.length - 1][i] = 'S';
				errorCode = ErrorCode.NoErr;
				return;
			}
		}
		for (int i = 1; i < theLab.length-1; i++) {
			if (theLab[i][0] == ' ') {
				theLab[i][0] = 'S';
				errorCode = ErrorCode.NoErr;
				return;
			}
		}
		for (int i = 1; i < theLab.length-1; i++) {
			if (theLab[i][theLab.length - 1] == ' ') {
				theLab[i][theLab.length - 1] = 'S';
				errorCode = ErrorCode.NoErr;
				return;
			}
		}
		errorCode = ErrorCode.NoExit;
	}

	public static ErrorCode getErrorCode() {
		return errorCode;
	}

	/**
	 * 
	 * 
	 * @param len
	 *            The number of dragons present in the maze
	 * @param coords
	 *            An array containing the positions of the various elements in
	 *            the maze. The first two elements refer to the Hero and Sword.
	 *            The following (if any) are the positions of dragons.
	 * @return An array with the Dragons initialized
	 */

	private static Dragon[] getDragons(int len, Coordinate[] coords) {
		Dragon[] dragons = new Dragon[len];
		for (int i = 0; i < dragons.length; i++) {
			Coordinate pos = coords[i + 2];
			dragons[i] = new Dragon(pos.getX(), pos.getY());
		}
		return dragons;
	}
}
