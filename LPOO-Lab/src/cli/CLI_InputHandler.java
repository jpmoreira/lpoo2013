package cli;

import java.io.IOException;
import java.util.Scanner;

import logica_jogo.Tabuleiro;
import main_package.InputHandler;


public class CLI_InputHandler implements InputHandler{
	private Tabuleiro theTab;
	private Scanner inputScanner;
	
	public CLI_InputHandler(Tabuleiro tab) {
		theTab=tab;
		inputScanner=new Scanner(System.in);
	}
	
	public CLI_InputHandler(){
		theTab=null;
		inputScanner=new Scanner(System.in);
	}
	
	public void getInGameInput(){
		char input='\0';			
		input=inputScanner.next().toUpperCase().charAt(0);
		
		
		switch (input) {
		case 'A':
			theTab.movePlayer(-1, 0);
			break;
		case 'D':
			theTab.movePlayer(1,0);
			break;
		case 'W':
			theTab.movePlayer(0, -1);
			break;
		case 'S':
			theTab.movePlayer(0, 1);
			break;
		default:
			break;
		}

		
}
	
	public int getDimention(){
		System.out.print("Please Insert the labyrinth Dimention: ");
		int dimention=0;
		try {
			dimention=inputScanner.nextInt();
			
		} catch (Exception e) {
			System.out.println("Invalid number format. Please enter another number.");
		}
		if(dimention<=3){
			return 10;
		}
		return dimention;
	}

	public int getMode(){
		System.out.print("Please Insert Game Mode: ");
		int mode=0;
		try {
			mode=inputScanner.nextInt();
			
		} catch (Exception e) {
			System.out.println("Invalid number format. Please enter another number.");
		}
		if(mode!=1 && mode!=2 && mode!=3){
			return 1;
		}
		return mode;
	}
		
	public int getNumberOfDragons(){
		System.out.print("Please Insert Nr of Dragons: ");
		int nrDragons=0;
		try {
			nrDragons=inputScanner.nextInt();
			
		} catch (Exception e) {
			System.out.println("Invalid number format. Please enter another number.");
		}
		if(nrDragons<1){
			return 1;
		}
		return nrDragons;
		
	}

	public int getPlayingMode(){
		System.out.print("Play in GUI mode?  ");
		try{
			String answer=inputScanner.next();
			if(answer.charAt(0)=='y' || answer.charAt(0)=='Y'){
				return 0;
				
			}
		}
		catch(Exception e){
			System.out.println("Invalid input.");
		}
		return 1;//default behaviour (cli)!
	}

	@Override
	public void HandleGameInput() {
		while(1==1){//forever loop
			getInGameInput();
			theTab.printLayout();
			
		}
		
	}
	@Override
	public void setTabuleiro(Tabuleiro newTab) {
		theTab=newTab;
		
	}
	
}
