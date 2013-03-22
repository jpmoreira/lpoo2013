package main_package;
import gui.GUI_InputHandler;
import gui.gameWindow;

import java.io.ObjectInputStream.GetField;
import java.util.Currency;
import java.util.Random;
import java.util.Stack;

import javax.swing.JFrame;

import logica_jogo.LabGenerator;
import logica_jogo.Tabuleiro;

import cli.CLI_InputHandler;


public class Jogo {

	
	static Tabuleiro tab;
	static InputHandler inpHandler;
	static gameWindow window;
	
	
	public static void main(String[] args) {
		
		inpHandler=new CLI_InputHandler(tab);//start with cli input;
				
	
	int playingMode=inpHandler.getPlayingMode();
	
	if(playingMode==0){//switch to GUI mode
		setupWindow();
		inpHandler=new GUI_InputHandler(tab);
	}
	
		
	//TODO how to get input from window!!!
	LabGenerator.prepareLab(inpHandler.getDimention());
	char[][] lab=LabGenerator.getLab();
	int mode=inpHandler.getMode();
	int nrDragons=inpHandler.getNumberOfDragons();
	
		
		
		tab=new Tabuleiro(
				1,2,
				6,3,
				lab,mode,nrDragons);
		tab.printLayout();
		inpHandler=new CLI_InputHandler(tab);
		
		while(1==1){
			inpHandler.getInGameInput();
			tab.printLayout();
			
		}

	}


	private static void setupWindow() {
		window=new gameWindow();
		window.makeVisible();
	}

	
	
	
 
	
	
	

}
