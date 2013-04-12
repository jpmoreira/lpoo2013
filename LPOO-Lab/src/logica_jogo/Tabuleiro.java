package logica_jogo;

public class Tabuleiro implements java.io.Serializable {

	Hero hero;
	Element sword;

	Eagle eagle;
	// Dragon dragon;
	int mode;
	Dragon[] dragonArray;
	GameSpecificRanGen theGen;
	Coordinate theExit;
	boolean terminated;
	boolean debugging;

	char[][] layout;

	public Tabuleiro(int playerX, int playerY, int swordX, int swordY,
			char[][] lab, int mod, int nrDrag) {

		theGen = new GameSpecificRanGen();
		layout = lab;
		dragonArray = new Dragon[nrDrag];

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

	}

	public void printLayout() {
		for (int l = 0; l <= Coordinate.getBounds().getY(); l++) {
			for (int c = 0; c <= Coordinate.getBounds().getX(); c++) {
				System.out.print(" " + layout[l][c] + " ");
			}
			System.out.println("");

		}
	}

	private void grabExit() {
		for (int i = 0; i <= Coordinate.getBounds().getX(); i++) {
			if (layout[0][i] == 'S') {
				theExit = new Coordinate(0, i);
				return;
			} else if (layout[Coordinate.getBounds().getY()][i] == 'S') {
				theExit = new Coordinate(Coordinate.getBounds().getY(), i);
				return;
			}
		}
		for (int i = 0; i <= Coordinate.getBounds().getY(); i++) {
			if (layout[i][0] == 'S') {
				theExit = new Coordinate(i, 0);
				return;
			}

			if (layout[i][Coordinate.getBounds().getX()] == 'S') {
				theExit = new Coordinate(i, Coordinate.getBounds().getY());
				return;
			}
		}
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
		System.out.println("hero newx= "+newX+" newY= "+newY);
		System.out.println("hero x= "+hero.getX()+" hero y="+hero.getY());
		moveDragons();
		moveEagle();
		if (Coordinate.validCoordinate(newX, newY)) {

			switch (layout[newY][newX]) {

			case ' ':
				switchPlace(hero, newX, newY);
				break;
			case 'S':
				exitLab();
				break;
			case 'E':
				getEspada();
			default:
				break;
			}

		}

		HeroVsDragons();
	}

	private void moveDragon(Dragon dragon) {

		if (dragon.isPlaying() && !dragon.isSleeping()) {

			if (theGen.dragonShouldSleep() && mode == 3) {// put to sleep only
															// if the random
															// number returns
															// zero and we are
															// in mode tree
				dragon.sleep();
				layout[dragon.getY()][dragon.getX()] = dragon.getPlaceHolder();
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
																		// of
																		// bounds
																		// and
																		// dragon
																		// isn't
																		// supposed
																		// to be
																		// still

				if (emptyPlace(newX, newY)) {
					if (dragon.getX() != sword.getX()
							|| dragon.getY() != sword.getY()) {// we were not at
																// the sword
																// place
						switchPlace(dragon, newX, newY);
					} else {// we are at the sword place
						layout[dragon.getY()][dragon.getX()] = 'S';// leave the
																	// sword
																	// there
						dragon.moveTo(newX, newY);
						dragon.defortify();// get back our placeholder
						layout[dragon.getY()][dragon.getX()] = dragon
								.getPlaceHolder();// draw the draagon
					}
				} else if (newX == sword.getX() && newY == sword.getY()) {// if
																			// we
																			// are
																			// going
																			// to
																			// the
																			// sword
					layout[dragon.getY()][dragon.getX()] = ' ';
					dragon.fortify();
					dragon.moveTo(newX, newY);
					layout[dragon.getY()][dragon.getX()] = dragon
							.getPlaceHolder();

				}

			}

		}

		else if (dragon.isPlaying()) {// if sleeping

			if (theGen.dragonShouldWake()) {// 1/3 chance to awake
				dragon.awake();
				layout[dragon.getY()][dragon.getX()] = dragon.getPlaceHolder();
			}
		}

	}

	private void getEspada() {
		hero.Arm();
		layout[hero.getY()][hero.getX()] = ' ';
		layout[sword.getY()][sword.getX()] = hero.getPlaceHolder();
		hero.moveTo(sword.getX(), sword.getY());
		sword.vanish();
	}

	private boolean emptyPlace(int x, int y) {
		if (Coordinate.validCoordinate(x, y) && layout[y][x] == ' ') {
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
			dragonArray[i] = new Dragon(pos.getX(), pos.getY());
			layout[pos.getY()][pos.getX()] = dragonArray[i].getPlaceHolder();
		}
	}

	private void setupHero(int playerX, int playerY) {
		if (emptyPlace(playerX, playerY)) {
			hero = new Hero(playerX, playerY);
		} else {
			Coordinate pos = findEmptyPosition();
			hero = new Hero(pos.getX(), pos.getY());
		}

		layout[hero.getY()][hero.getX()] = hero.getPlaceHolder();
	}

	private void setupSword(int swordX, int swordY) {
		if (emptyPlace(swordX, swordY)) {
			sword = new Element('E', swordX, swordY);
		} else {
			Coordinate pos = findEmptyPosition();
			sword = new Element('E', pos.getX(), pos.getY());
		}

		layout[sword.getY()][sword.getX()] = sword.getPlaceHolder();
	}

	private void setupEagle() {
		eagle = new Eagle(hero.getX(), hero.getY());
		//hero.EagleShoulder();
		//layout[hero.getY()][hero.getX()] = ' ';
		//layout[eagle.getY()][eagle.getX()] = hero.getPlaceHolder();
		//hero.moveTo(eagle.getX(), eagle.getY());
		//eagle.vanish();
	}

	private void verifyDragonProximity(Dragon dragon) {
		int dx = Math.abs(dragon.getX() - hero.getX());
		int dy = Math.abs(dragon.getY() - hero.getY());
		if ((dx <= 1 && dy == 0) || (dy <= 1 && dx == 0)) {
			if (hero.getPlaceHolder() == 'A') {
				layout[dragon.getY()][dragon.getX()] = ' ';
				dragon.vanish();

			} else if (dragon.isPlaying() && !dragon.isSleeping()) {

				System.exit(0);
			}
		}
	}

	public void setLayout(char[][] newLO) {
		layout = newLO;
	}

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
	public Eagle getEagle(){
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
			switchPlace(dragonArray[0], x, y);
		}
	}

	private void switchPlace(Element el, int newX, int newY) {
		if (el instanceof Character) {// if it's movable
			if (layout[el.getY()][el.getX()] == el.getPlaceHolder()) {// if it's
																		// acctually
																		// there
				layout[el.getY()][el.getX()] = ' ';
				((Character) el).moveTo(newX, newY);
				layout[el.getY()][el.getX()] = el.getPlaceHolder();
			}

		}
	}

	public void debugOn() {
		debugging = true;
	}

	public boolean isFinished() {
		return terminated;
	}

	public char[][] getLayout() {
		return layout;
	}


	public Element getSword(){
		return sword;
	}
	
	private void moveEagle(){
		if(eagle.isPlaying()){
			Coordinate newPos=eagle.newEaglePos();
			System.out.println("eagle x= "+newPos.getX()+" y= "+newPos.getY());
			System.out.println("hero x="+hero.getX()+" y= "+hero.getY());
			System.out.println("sword x="+sword.getX()+" y= "+sword.getY());
			char behind='#';
			if(Coordinate.validCoordinate(newPos)){
				
				if(hero.getPosition().equals(newPos)){
					eagle.vanish();
					hero.Arm();
				}
				else if(sword.getPosition().equals(newPos)){
					System.out.println("cheguei");
					eagle.reachedSword();
					behind=' ';
					sword.vanish();
				}
				else if(!eagle.isFlying()){
					for(int i=0; i<dragonArray.length; i++){
						Dragon d=dragonArray[i];
						if(d.getPosition()==newPos){
							eagle.vanish();
							break;
						}
					}
				}
				
				if(behind=='#'){
					behind=layout[newPos.getY()][newPos.getX()];
				}
			
				layout[eagle.getPosition().getY()][eagle.getPosition().getX()]=eagle.moveTo(newPos.getX(), newPos.getY(),behind);
				layout[eagle.getPosition().getY()][eagle.getPosition().getX()]=eagle.getPlaceHolder();
				
			}
			
		}
	}

}
