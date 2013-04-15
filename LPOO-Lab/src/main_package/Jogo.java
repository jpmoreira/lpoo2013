package main_package;
import gui.GUI_Processor;

import java.io.Serializable;

import logica_jogo.Tabuleiro;
import cli.CLI_Processor;

/**
 * 
 * An initial class that does game initial launch and change of input method.
 * 
 * @author mppl
 *
 */

public class Jogo implements Serializable{

	
	static Tabuleiro tab;
	static InputHandler inpHandler;
	
	
	public static void main(String[] args) {
		
		inpHandler=new CLI_Processor();
				
	
	int playingMode=inpHandler.getPlayingMode();
	
	if(playingMode==0){//switch to GUI mode
		inpHandler=new GUI_Processor();
	}
	
		
	inpHandler.makeGame();
		
		inpHandler.HandleGameInput();//deliever command to the inputHandler
		//if in cli mode this will result in a loop
		//if in a gui mode this will result in setting up the apropriate listeners

	}



	
	
 
	
	
	

}
