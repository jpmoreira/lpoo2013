package main_package;

import java.util.Scanner;

import logica_jogo.Tabuleiro;

public interface InputHandler {
	

	
	public void getInGameInput();
	
	public int getDimention();

	public int getMode();
		
	public int getNumberOfDragons();

	public int getPlayingMode();
}
