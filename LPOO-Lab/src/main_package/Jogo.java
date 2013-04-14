package main_package;
import gui.GUI_InputHandler;

import java.io.Serializable;

import logica_jogo.Tabuleiro;
import cli.CLI_InputHandler;


public class Jogo implements Serializable{

	
	static Tabuleiro tab;
	static InputHandler inpHandler;
	
	
	public static void main(String[] args) {
		
		inpHandler=new CLI_InputHandler();
				
	
	int playingMode=inpHandler.getPlayingMode();
	
	if(playingMode==0){//switch to GUI mode
		inpHandler=new GUI_InputHandler();
	}
	
		
	//TODO how to get input from window!!!
	inpHandler.makeGame();
		
		inpHandler.HandleGameInput();//deliever command to the inputHandler
		//if in cli mode this will result in a loop
		//if in a gui mode this will result in setting up the apropriate listeners

	}



	
	
 
	
	
	

}
