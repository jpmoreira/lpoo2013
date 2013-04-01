package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.management.openmbean.TabularData;

import logica_jogo.LabGenerator;
import logica_jogo.Tabuleiro;
import main_package.InputHandler;



public class GUI_InputHandler implements InputHandler {

	private Tabuleiro theTab;
	private gameWindow window;
	
	private int mode=0;
	private int labSize=10;
	private int nrOfDragons=1;

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
		return labSize;
	}

	@Override
	public int getMode() {
		return mode;
	}

	@Override
	public int getNumberOfDragons() {
		return nrOfDragons;
	}

	@Override
	public int getPlayingMode() {
		// TODO Auto-generated method stub
		return 0;
	}

	private void setupWindow(){
		window=new gameWindow(this);
		window.makeVisible();
	}
	@Override
	public void setTabuleiro(Tabuleiro newTab) {
	
		theTab=newTab;
	}
	@Override
	public void HandleGameInput() {
		window.redirectFocus();
		window.getMainPanel().addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
			
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_UP){
					theTab.movePlayer(0, -1);
					window.updateDrawbleContent();
				}
				else if(arg0.getKeyCode()==KeyEvent.VK_DOWN){
					theTab.movePlayer(0, 1);
					window.updateDrawbleContent();
				}
				else if(arg0.getKeyCode()==KeyEvent.VK_LEFT){
					theTab.movePlayer(-1, 0);
					window.updateDrawbleContent();
				}
				else if(arg0.getKeyCode()==KeyEvent.VK_RIGHT){
					theTab.movePlayer(1, 0);
					window.updateDrawbleContent();
				}
				
			}
		});
		
		
		
		
	}
	
	public void makeGame(){
		LabGenerator.prepareLab(getDimention());
		char [][] lab=LabGenerator.getLab();
		theTab=new Tabuleiro(0, 0, 0, 0, lab,getMode(), getNumberOfDragons());
		theTab.printLayout();
		window.updateDrawbleContent();
	}

	public char[][] getLayout(){
		return theTab.getLayout();
	}
}
