package gui;

import javax.management.openmbean.TabularData;

import logica_jogo.Tabuleiro;
import main_package.InputHandler;



public class GUI_InputHandler implements InputHandler {

	private Tabuleiro theTab;
	private gameWindow window;
	

	public GUI_InputHandler() {
		theTab=null;
		setupWindow();
	}
	public GUI_InputHandler(Tabuleiro tab) {
		theTab=tab;
		setupWindow();
	}

	@Override
	public int getDimention() {
		return window.getControlPanel().getLabSizeSlider().getValue();
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

	private void setupWindow(){
		window=new gameWindow();
		window.makeVisible();
	}
	@Override
	public void setTabuleiro(Tabuleiro newTab) {
	
		theTab=newTab;
	}
	@Override
	public void HandleGameInput() {
		//TODO setup listeners
	}
}
