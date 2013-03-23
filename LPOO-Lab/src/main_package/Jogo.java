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
	
	
	public static void main(String[] args) {
		
		inpHandler=new CLI_InputHandler();//start with cli input, set no tab
				
	
	int playingMode=inpHandler.getPlayingMode();
	
	if(playingMode==0){//switch to GUI mode
		inpHandler=new GUI_InputHandler();
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
		inpHandler.setTabuleiro(tab);
		
		inpHandler.HandleGameInput();//deliever command to the inputHandler
		//if in cli mode this will result in a loop
		//if in a gui mode this will result in setting up the apropriate listeners

	}



	
	
	
 
	
	
	

}
