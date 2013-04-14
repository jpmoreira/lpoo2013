package gui;

import java.awt.Dialog;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logica_jogo.Coordinate;
import logica_jogo.Dragon;
import logica_jogo.EditorProcessor;
import logica_jogo.EditorProcessor.ErrorCode;
import logica_jogo.LabGenerator;
import logica_jogo.Tabuleiro;
import main_package.InputHandler;



public class GUI_InputHandler implements InputHandler {

	private Tabuleiro theTab;

	private gameWindow window;
	
	private int mode=0;
	private int labSize=10;
	private int nrOfDragons=1;
	private GUI_InputHandler editHandler;
	private EditorWindow Editor;
	
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
				else if(arg0.getKeyCode()==KeyEvent.VK_E){
					theTab.getEagle().StartEagle(theTab.getHero(),theTab.getSword());
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
	
	protected void Prepare_Editor(int editor_size){
		Editor = new EditorWindow(this,editor_size);
	}

	public char[][] getLayout(){
		return theTab.getLayout();
	}

	public void saveGame(String name){
		try
	      {
			 name=name.concat(".save");
	         FileOutputStream fileOut =
	         new FileOutputStream(name);
	         ObjectOutputStream out =new ObjectOutputStream(fileOut);
	         out.writeObject(theTab);
	         out.close();
	          fileOut.close();
	          
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	public void switch_layout(char[][] newlayout){
	
	Tabuleiro newTab=EditorProcessor.generateInitializedTab(newlayout);	
	if(newTab==null){//if an error occurred
		ErrorCode error=EditorProcessor.getErrorCode();
		if(error==ErrorCode.NotEnoughSpace){
			//TODO case it hasn't enough empty spaces
			System.out.println("Not enough empty spaces");
		}
		else if(error==ErrorCode.NoExit){
			//TODO case it hasn't a proper exit
			System.out.println("No exit");
		}
	}
	
	theTab=newTab;
	window.updateDrawbleContent();
	}

	protected void init_JDialog(String name){
		JDialog d1 = new JDialog(window.getFrame(),name,Dialog.ModalityType.DOCUMENT_MODAL);
		setup_Dialog(d1);
	}
	private void setup_Dialog(JDialog dialog){
		JPanel dial = new JPanel();
		JButton okBtn = new JButton("OK");
	}

	public void loadGame(String name){
		try
	      {
			 name=name.concat(".save");
	         FileInputStream fileIn =
	                          new FileInputStream(name);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         theTab = null ;
	         theTab = (Tabuleiro) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {		
	    	 JOptionPane.showMessageDialog(window.getFrame(),"File not Found !","IOException not found",JOptionPane.ERROR_MESSAGE );
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         JOptionPane.showMessageDialog(window.getFrame(),"File is not valid !","ClassNotFoundException",JOptionPane.ERROR_MESSAGE );
	         c.printStackTrace();
	         return;
	      }
		theTab.printLayout();
		window.updateDrawbleContent();
	}

	public char[][] getBaseLayout(){
		return theTab.getBaseLayout();
	}
}
