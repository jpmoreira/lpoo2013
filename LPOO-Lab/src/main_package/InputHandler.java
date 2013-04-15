package main_package;

import logica_jogo.Tabuleiro;

public interface InputHandler {
	
	
	public int getDimention();

	public int getMode();
		
	public int getNumberOfDragons();

	public int getPlayingMode();
	
	public void HandleGameInput();
	public void setTabuleiro(Tabuleiro newTab);
	public void makeGame();
	public void saveGame(String name);
	public void loadGame(String name);
}
