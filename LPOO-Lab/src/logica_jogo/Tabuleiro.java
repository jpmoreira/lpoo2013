package logica_jogo;

/**
 * A class to process the most basic game logic operations
 * 
 * 
 * @author mppl
 *
 */

public class Tabuleiro implements java.io.Serializable {

	Hero hero;
	Element sword;
	Element exit;
	Eagle eagle;
	int mode;
	Dragon[] dragonArray;
	GameSpecificRanGen theGen;
	boolean terminated;
	boolean debugging;

	char[][] layout;
	char[][] cleanLayout;

	/**
	 * Constructor that initializes a instance of Tabuleiro with the following
	 * parameters
	 * 
	 * 
	 * @param playerX
	 * @param playerY
	 * @param swordX
	 * @param swordY
	 * @param lab The maze of the game
	 * @param mod
	 *            Int that setups game mode, 0 for Static Dragons , 1 for no
	 *            Sleeping Dragons, 2 for Sleep/Awaken Dragons
	 * @param nrDrag
	 */
	public Tabuleiro(int playerX, int playerY, int swordX, int swordY,
			char[][] lab, int mod, int nrDrag) {
		System.out.println("starting");

		theGen = new GameSpecificRanGen();
		cleanLayout = lab;
		layout = new char[lab.length][lab.length];

		//printCleanLayout();
		// setup Hero
		setupHero(playerX, playerY);
		setupEagle();
		// setup sword
		setupSword(swordX, swordY);

		// setup dragon
		setupDragons(nrDrag);

		grabExit();

		mode = mod;
		terminated = false;
		debugging = false;

		updateLayout();
		System.out.println("will return");
	}

	/**
	 * Prints Game Maze on console with all it's elements
	 */
	public void printLayout() {
		System.out.println("printing layout");
		for (int l = 0; l <= Coordinate.getBounds().getY(); l++) {
			for (int c = 0; c <= Coordinate.getBounds().getX(); c++) {
				System.out.print(" " + layout[l][c] + " ");
			}
			System.out.println("");

		}
	}

	/**
	 * Prints clean maze on console without any game element in it.
	 */
	public void printCleanLayout() {
		for (int l = 0; l <= Coordinate.getBounds().getY(); l++) {
			for (int c = 0; c <= Coordinate.getBounds().getX(); c++) {
				System.out.print(" " + cleanLayout[l][c] + " ");
			}
			System.out.println("");

		}
	}

	/**
	 * Parses the Maze (cleanLayout) finds exit position and attributes it to
	 * variable exit.
	 */
	private void grabExit() {
		System.out.println("grabing");
		for (int i = 0; i <= Coordinate.getBounds().getX(); i++) {
			if (cleanLayout[0][i] == 'S') {
				exit = new Element('S', i, 0);
				return;
			} else if (cleanLayout[Coordinate.getBounds().getY()][i] == 'S') {
				exit = new Element('S', i, Coordinate.getBounds().getY());
				return;
			}
		}
		for (int i = 0; i <= Coordinate.getBounds().getY(); i++) {
			if (cleanLayout[i][0] == 'S') {
				exit = new Element('S', 0, i);
				return;
			}

			if (cleanLayout[i][Coordinate.getBounds().getX()] == 'S') {
				exit = new Element('S', Coordinate.getBounds().getY(), i);
				return;
			}
		}
		System.out.println("no ret");
	}

	private void exitLab() {
		if (hero.isArmed()) {// if armed
			terminated = true;
			if (!debugging) {
				System.exit(0);// we terminate only if we are not debugging
			}
		}
	}

	public void movePlayer(int dx, int dy) {
		int newX = hero.getPosition().getX() + dx;
		int newY = hero.getPosition().getY() + dy;
		Coordinate newCord = new Coordinate(newX, newY);
		moveDragons();
		moveEagle();
		if (Coordinate.validCoordinate(newX, newY)) {

			if (newCord.equals(sword.getPosition()) && !sword.isOverlaped()) {// se
																				// encontramos
																				// a
																				// espada
																				// e
																				// ela
																				// n
																				// esta
																				// fora
																				// de
																				// jogo
																				// por
																				// o
																				// dragao
																				// estar
																				// la
				getEspada();
				hero.moveTo(newX, newY);
			} else if (newCord.equals(exit.getPosition())) {// if we are trying
															// to exit
				attemptExit();
			}

			else if (layout[newY][newX] == ' ') {// if not a wall
				hero.moveTo(newX, newY);
			}

		}

		HeroVsDragons();
		updateLayout();
		verifyDoorState();
	}

	private void moveDragon(Dragon dragon) {

		if (dragon.isPlaying() && !dragon.isSleeping()) {

			if (theGen.dragonShouldSleep() && mode == 3) {// put to sleep only
															// if the random
															// number returns
															// zero and we are
															// in mode tree
				dragon.sleep();
				return;
			}

			int nr = theGen.dragonMoveNumber();
			int newX = dragon.getX();
			int newY = dragon.getY();

			switch (nr) {
			case 0:// move left
				newX -= 1;
				break;
			case 1:// move right
				newX += 1;
				break;
			case 2:// move up
				newY -= 1;
				break;
			default:// move down
				newY += 1;
				break;
			}

			if (Coordinate.validCoordinate(newX, newY) && mode != 1) {// if we
																		// are
																		// not
																		// out
																		// of
																		// bounds
																		// and
																		// dragon
																		// is
																		// supposed
																		// to
																		// move

				if (emptyPlace(newX, newY)) {
					if (!dragon.getPosition().equals(sword.getPosition())) {// we
																			// were
																			// not
																			// at
																			// the
																			// sword
																			// place
						dragon.moveTo(newX, newY);
					} else {// we are at the sword place
						dragon.defortify();
						sword.unhide();
						System.out.println("dragon will switch");

						dragon.moveTo(newX, newY);
					}
				} else if (newX == sword.getX() && newY == sword.getY()) {// if
																			// we
																			// are
																			// heading
																			// touards
																			// the
																			// sword
					dragon.fortify();
					sword.overlap();// signal the dragon is there
					dragon.moveTo(newX, newY);

				}

			}

		}

		else if (dragon.isPlaying()) {// if sleeping

			if (theGen.dragonShouldWake()) {// 1/3 chance to awake
				dragon.awake();
			}
		}

	}

	private void getEspada() {
		hero.Arm();
		sword.vanish();
	}

	/**
	 * 
	 * Verifies that a position is empty
	 * 
	 * @param x the x position of the tested cell
	 * @param y the y position of the tested cell
	 * @return true if the position is empty, false otherwise
	 */
	private boolean emptyPlace(int x, int y) {
		if (Coordinate.validCoordinate(x, y) && cleanLayout[y][x] == ' ') {
			return true;
		}
		return false;
	}

	private Coordinate findEmptyPosition() {
		int maxX = Coordinate.getBounds().getX();
		int maxY = Coordinate.getBounds().getY();
		int x = theGen.giveRandom(0, maxX);
		int y = theGen.giveRandom(0, maxY);

		while (!emptyPlace(x, y)) {
			x = theGen.giveRandom(0, maxX);
			y = theGen.giveRandom(0, maxY);
		}

		return new Coordinate(x, y);
	}

	private void setupDragons(int nrOfDragons) {
		dragonArray = new Dragon[nrOfDragons];
		for (int i = 0; i < nrOfDragons; i++) {
			Coordinate pos = findEmptyPosition();
			while (pos.equals(hero.getPosition())
					|| pos.equals(sword.getPosition())
					|| existsDragonAt(pos, i)) {
				pos = findEmptyPosition();
			}
			dragonArray[i] = new Dragon(pos.getX(), pos.getY());
		}
	}

	private void setupHero(int playerX, int playerY) {
		if (emptyPlace(playerX, playerY)) {
			System.out.println("is empty x="+playerX+" y"+playerY);
			hero = new Hero(playerX, playerY);
		} else {
			System.out.println("is not");
			Coordinate pos = findEmptyPosition();
			hero = new Hero(pos.getX(), pos.getY());
		}

	}

	private void setupSword(int swordX, int swordY) {
		Coordinate thePos = new Coordinate(swordX, swordY);
		if (emptyPlace(swordX, swordY) && !thePos.equals(hero.getPosition())) {
			sword = new Element('E', swordX, swordY);
		} else {
			thePos = findEmptyPosition();
			while (thePos.equals(hero.getPosition())) {
				thePos = findEmptyPosition();
			}
			sword = new Element('E', thePos.getX(), thePos.getY());
		}

	}

	private void setupEagle() {
		eagle = new Eagle();

	}

	/**
	 * Verifies if Dragon is close to hero and acts accordingly 
	 * 
	 * @param dragon
	 */
	private void verifyDragonProximity(Dragon dragon) {
		int dx = Math.abs(dragon.getX() - hero.getX());
		int dy = Math.abs(dragon.getY() - hero.getY());
		if ((dx <= 1 && dy == 0) || (dy <= 1 && dx == 0)) {
			if (hero.isArmed()) {
				// layout[dragon.getY()][dragon.getX()] = ' ';
				dragon.vanish();

			} else if (dragon.isPlaying() && !dragon.isSleeping()) {

				System.exit(0);
			}
		}
	}

	public void setLayout(char[][] newLO) {
		layout = newLO;
	}

	/**
	 * Turns of the Random Generator Off See the specification of
	 * RandomGenerator
	 * 
	 * @param move
	 *            - Array of int that will be returned to type of dragon
	 *            moviment
	 * @param sleep
	 *            - Array of int that will be the return to the type of sleep
	 *            mode
	 * @param wake
	 *            - Array of int that will be the return for the type of wake
	 */
	public void makeNonRandom(int[] move, int[] sleep, int[] wake) {
		theGen.randomModeOff(sleep, wake, move);
	}

	public void moveDragons() {
		for (int i = 0; i < dragonArray.length; i++) {
			moveDragon(dragonArray[i]);
		}

	}

	public void HeroVsDragons() {
		for (int i = 0; i < dragonArray.length; i++) {
			verifyDragonProximity(dragonArray[i]);
		}
	}

	public Hero getHero() {
		return hero;
	}

	public Eagle getEagle() {
		return eagle;
	}

	public Element[] getDragons() {
		return dragonArray;
	}

	public void makeRandom() {
		theGen.randomModeOn();
	}

	public void placeDragonAt(int x, int y) {
		if (dragonArray.length == 1 && emptyPlace(x, y)) {
			dragonArray[0].moveTo(x, y);
		}
	}

	public void debugOn() {
		debugging = true;
	}

	public boolean isFinished() {
		return terminated;
	}

	public int getMode() {
		return mode;
	}

	public char[][] getLayout() {
		return layout;
	}

	public Element getSword() {
		return sword;
	}

	private void moveEagle() {
		if (eagle.isPlaying()) {
			Coordinate newPos = eagle.newEaglePos();
			if (Coordinate.validCoordinate(newPos)) {//if its a legit position

				if (hero.getPosition().equals(newPos) && eagle.hasSword()) {//if eagle has the sword and finds the hero at end
					eagle.vanish();
					hero.Arm();
					return;
				} else if (sword.getPosition().equals(newPos) && !eagle.hasSword()) {//if eagle has reached sword position
					eagle.reachedSword();
					sword.vanish();
				} else if (eagle.getPosition().equals(newPos)&& eagle.hasSword()) {// reached end and no hero there
					sword = new Element('E', eagle.getX(), eagle.getY());//place sword there
					eagle.vanish();
					return;

				} else if (!eagle.isFlying()) {//if the eagle is not flying
					for (int i = 0; i < dragonArray.length; i++) {//test proximity of all dragons
						Dragon d = dragonArray[i];
						if (d.getPosition() == newPos) {
							if (eagle.hasSword()) {
								sword = new Element('E', eagle.getX(),
										eagle.getY());// "leave" sword
							}
							eagle.vanish();

							return;
						}
					}

				}

				eagle.moveTo(newPos.getX(), newPos.getY());//if every test passed move eagle then

			}

		}
	}

	/**
	 * A function called when a hero tries to exit the lab
	 * 
	 */
	void attemptExit() {
		for (int i = 0; i < dragonArray.length; i++) {
			if (dragonArray[i].isPlaying()) {
				return;
			}
		}
		exitLab();// if no one is playing
	}

	public void updateLayout() {
		for (int i = 0; i < layout.length; i++) {
			for (int f = 0; f < layout.length; f++) {
				layout[i][f] = getSymbol(f, i);
			}
		}

	}

	private char getSymbol(int x, int y) {
		Coordinate cord = new Coordinate(x, y);
		Dragon d = getDragonAt(x, y);
		if (d != null) {// if there's a dragon
			return d.getPlaceHolder();
		} else if (hero.getPosition().equals(cord)) {
			return hero.getPlaceHolder();
		} else if (exit.getPosition().equals(cord)) {
			return exit.getPlaceHolder();
		} else if (sword.getPosition().equals(cord) && !sword.isOverlaped()) {
			return sword.getPlaceHolder();
		} else if (eagle.getPosition().equals(cord)) {
			return eagle.getPlaceHolder();
		} else {
			return cleanLayout[y][x];
		}

	}

	public Dragon getDragonAt(int x, int y) {
		for (int i = 0; i < dragonArray.length; i++) {
			if (dragonArray[i].getX() == x && dragonArray[i].getY() == y) {
				return dragonArray[i];
			}
		}
		return null;
	}

	public boolean existsDragonAt(Coordinate pos, int i) {
		for (int f = 0; f < i; f++) {
			if (dragonArray[f].getPosition().equals(pos)) {
				return true;
			}
		}
		return false;
	}

	public char[][] getBaseLayout() {
		return cleanLayout;
	}

	public void setDragonsArray(Dragon[] newArray) {
		dragonArray = newArray;
	}
	private void verifyDoorState(){
		for(int i=0 ;i<dragonArray.length;i++){
			if(dragonArray[0].isPlaying()){
				return;
			}
		}
		exit= new Element('s', exit.getX(), exit.getY());
	}
}
