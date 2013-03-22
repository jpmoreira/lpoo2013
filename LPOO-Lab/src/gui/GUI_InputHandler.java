package gui;

import javax.management.openmbean.TabularData;

import logica_jogo.Tabuleiro;
import main_package.InputHandler;

public class GUI_InputHandler implements InputHandler {

	private Tabuleiro theTab;
	
	public GUI_InputHandler(Tabuleiro tab) {
		theTab=tab;
	}
	@Override
	public void getInGameInput() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getDimention() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfDragons() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPlayingMode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
