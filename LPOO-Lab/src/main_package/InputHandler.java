package main_package;


/**
 * An interface with a set of methods every InputHandler should implement
 * 
 */

import java.util.Scanner;


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
