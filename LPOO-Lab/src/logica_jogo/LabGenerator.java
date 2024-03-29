package logica_jogo;

/**
 * A class designed to generate a maze.
 * 
 * 
 */
import java.util.Random;
import java.util.Stack;

public class LabGenerator {

	private static char[][] theLab;

	/**
	 * 
	 * 
	 * @param dimention
	 */
	static public void initLab(int dimention) {
		theLab = new char[dimention][dimention];
		for (int i = 0; i < theLab.length; i++) {
			for (int f = 0; f < theLab.length; f++) {
				theLab[i][f] = 'x';
			}
		}
	}

	/**
	 * 
	 * A method that tells the caller if the coordinate passed is a legit cell
	 * to be cleared in the lap creation process.
	 * 
	 * @param thePt
	 *            the point that is to be tested
	 * @return A boolean saying if this is a legit cell to be cleared or not.
	 */

	private static boolean isPossibleWay(Coordinate thePt) {

		if (thePt.getX() <= 0 || thePt.getX() >= theLab.length - 1
				|| thePt.getY() <= 0 || thePt.getY() >= theLab.length - 1) {// if
																			// it
																			// is
																			// a
																			// limit
																			// point
																			// or
																			// beyond
																			// bounds
			return false;
		}

		if (theLab[thePt.getY()][thePt.getX()] == ' ') {// if this point is
														// already cleared
			return false;
		}

		if (theLab[thePt.getY()][thePt.getX() - 1] == ' '
				&& theLab[thePt.getY() + 1][thePt.getX()] == ' '
				&& theLab[thePt.getY() + 1][thePt.getX() - 1] == ' ') {// if
																		// left,down
																		// and
																		// lower
																		// left
																		// corner
																		// are
																		// already
																		// cleared
			return false;
		}
		if (theLab[thePt.getY()][thePt.getX() - 1] == ' '
				&& theLab[thePt.getY() - 1][thePt.getX()] == ' '
				&& theLab[thePt.getY() - 1][thePt.getX() - 1] == ' ') {// if
																		// left,up
																		// and
																		// upper
																		// left
																		// corner
																		// are
																		// already
																		// cleared
			return false;
		}
		if (theLab[thePt.getY()][thePt.getX() + 1] == ' '
				&& theLab[thePt.getY() - 1][thePt.getX()] == ' '
				&& theLab[thePt.getY() - 1][thePt.getX() + 1] == ' ') {// if
																		// right,up
																		// and
																		// upper
																		// right
																		// corner
																		// are
																		// already
																		// cleared
			return false;
		}
		if (theLab[thePt.getY()][thePt.getX() + 1] == ' '
				&& theLab[thePt.getY() + 1][thePt.getX()] == ' '
				&& theLab[thePt.getY() + 1][thePt.getX() + 1] == ' ') {// if
																		// right,down
																		// and
																		// lower
																		// right
																		// corner
																		// are
																		// already
																		// cleared
			return false;
		}

		return true;
	}

	/**
	 * A method that generates a maze with a given dimension starting from a
	 * given starting point.
	 * 
	 * @param dimention
	 *            the dimension of the maze to be created
	 * @param startingPoint
	 *            A Coordinate object with the position of the starting point.
	 * @param exit
	 *            A Coordinate object with the position of the exit.
	 */
	private static void generateLab(int dimention, Coordinate startingPoint,
			Coordinate exit) {
		if (!isPossibleWay(startingPoint)
				&& (startingPoint.getX() != exit.getX() || startingPoint.getY() != exit
						.getY())) {
			return;
		}

		theLab[startingPoint.getY()][startingPoint.getX()] = ' ';

		int x = startingPoint.getX();
		int y = startingPoint.getY();
		Random generator = new Random();
		Coordinate[] array = { new Coordinate(x + 1, y),
				new Coordinate(x - 1, y), new Coordinate(x, y + 1),
				new Coordinate(x, y - 1) };
		Stack<Coordinate> ptStack = new Stack();
		int nr;
		while (array.length != 0) {
			nr = generator.nextInt(array.length);
			ptStack.push(array[nr]);
			Coordinate[] temp = new Coordinate[array.length - 1];
			for (int i = 0; i < nr; i++) {
				temp[i] = array[i];
			}
			for (int i = nr + 1; i <= temp.length; i++) {
				temp[i - 1] = array[i];
			}
			array = temp;
		}

		while (!ptStack.empty()) {
			Coordinate pt = ptStack.pop();
			generateLab(dimention, pt, exit);

		}

	}

	/**
	 * 
	 * A method that generates a maze with a given dimension
	 * 
	 * @param dimention
	 *            The dimension of the maze to be created.
	 */
	public static void prepareLab(int dimention) {

		initLab(dimention);
		Coordinate.setBounds(dimention - 1, dimention - 1);
		Coordinate exit = getExit(dimention);
		generateLab(dimention, exit, exit);
		theLab[exit.getY()][exit.getX()] = 'S';
	}

	/**
	 * A method that returns the lab previously generated. If no previous
	 * generation was done null could be returned.
	 * 
	 * @return The maze previously generated. null may be returned.
	 */

	public static char[][] getLab() {
		return theLab;
	}

	private static Coordinate getExit(int dimention) {
		Random generator = new Random();
		int x, y;
		if (generator.nextInt(2) == 0) {
			x = generator.nextInt(dimention - 2) + 1;
			if (generator.nextInt(2) == 0) {
				y = 0;
			} else {
				y = dimention - 1;
			}
		} else {
			y = generator.nextInt(dimention - 2) + 1;
			if (generator.nextInt(2) == 0) {
				x = 0;
			} else {
				x = dimention - 1;
			}
		}

		return new Coordinate(x, y);

	}

}
