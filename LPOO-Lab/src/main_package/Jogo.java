package main_package;
import java.util.Currency;
import java.util.Random;
import java.util.Stack;

import logica_jogo.LabGenerator;
import logica_jogo.Tabuleiro;

import cli.CLI_InputHandler;


public class Jogo {

	
	static Tabuleiro tab;
	static CLI_InputHandler inpHandler;
	
	
	public static void main(String[] args) {
		inpHandler=new CLI_InputHandler(tab);
				
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

	
	
	
 
	
	
	

}
