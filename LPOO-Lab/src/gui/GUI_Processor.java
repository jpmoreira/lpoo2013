package gui;

/**
 * A class that handles some user input and processes some game related data. It manages the operations related to persistence mechanisms.
 * It transfers and articulates operations between the game login and the viewControllers
 * 
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.prefs.Preferences;

import javax.swing.JOptionPane;

import logica_jogo.EditorProcessor;
import logica_jogo.EditorProcessor.ErrorCode;
import logica_jogo.LabGenerator;
import logica_jogo.Tabuleiro;
import main_package.InputHandler;

public class GUI_Processor implements InputHandler {

	private Tabuleiro theTab;
	private GameWindowViewController window;
	private int labSize = 10;
	private Preferences prefs;
	private int mode = 0;
	private int nrOfDragons = 1;
	private EditorWindowViewController Editor;
	private int move_up_key,move_down_key,move_left_key,move_right_key,send_eagle_key;

	public GUI_Processor() {
		prefs=Preferences.userNodeForPackage(this.getClass());
		LoadkeysFromPreferences();
		mode=Integer.parseInt(prefs.get("mode",""+0));
		labSize=Integer.parseInt(prefs.get("labsize",""+10));
		nrOfDragons=Integer.parseInt(prefs.get("nrOfDragons",""+1));
		theTab = null;
		setupGameWindow();
	}

	public GUI_Processor(Tabuleiro tab) {
		prefs=Preferences.userNodeForPackage(this.getClass());
		LoadkeysFromPreferences();
		mode=Integer.parseInt(prefs.get("mode",""+0));
		labSize=Integer.parseInt(prefs.get("labsize",""+10));
		nrOfDragons=Integer.parseInt(prefs.get("nrOfDragons",""+0));
		theTab = tab;
		setupGameWindow();
	}

	@Override
	public int getDimention() {
		return labSize;
	}

	@Override
	public int getMode() {
		return mode;
	}
	
	
	protected int getMove_up_key_code() {
		return move_up_key;
	}

	protected int getMove_down_key_code() {
		return move_down_key;
	}

	protected int getMove_left_key_code() {
		return move_left_key;
	}

	protected int getMove_right_key_code() {
		return move_right_key;
	}

	protected int getSend_eagle_key_code() {
		return send_eagle_key;
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

	private void setupGameWindow() {
		window = new GameWindowViewController(this);
		window.makeVisible();
	}

	@Override
	public void setTabuleiro(Tabuleiro newTab) {

		theTab = newTab;
	}
	
	/**
	 * A method that sets preferences and saves them in a persistent way.
	 * 
	 * @param mode The mode the game is supposed to operate on
	 * @param tabsize The size of the maze
	 * @param nrDragons The number of dragons
	 */
	public void setPrefs(int mode, int tabsize, int nrDragons){
		
		//set the values ready for next initialization
		this.mode=mode;
		this.nrOfDragons=nrDragons;
		this.labSize=tabsize;
		
		//save preferences
		prefs.put("mode",""+mode);
		prefs.put("labsize",""+tabsize);
		prefs.put("nrOfDragons",""+nrDragons);	
	}
	protected void setkeyPrefs(int up, int down, int right, int left, int eagle){
		prefs.put("key_up",""+up);
		prefs.put("key_down",""+down);
		prefs.put("key_right",""+right);
		prefs.put("key_left",""+left);
		prefs.put("key_eagle",""+eagle);
		LoadkeysFromPreferences();
	}
	private void LoadkeysFromPreferences(){
		move_up_key=(Integer.parseInt(prefs.get("key_up",""+KeyEvent.VK_UP)));
		move_down_key=(Integer.parseInt(prefs.get("key_down",""+KeyEvent.VK_DOWN)));
		move_left_key=(Integer.parseInt(prefs.get("key_left",""+KeyEvent.VK_LEFT)));
		move_right_key=(Integer.parseInt(prefs.get("key_right",""+KeyEvent.VK_RIGHT)));
		send_eagle_key=(Integer.parseInt(prefs.get("key_eagle",""+KeyEvent.VK_E)));
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
				if (arg0.getKeyCode() == move_up_key) {
					theTab.movePlayer(0, -1);
					window.updateDrawbleContent();
				} else if (arg0.getKeyCode() == move_down_key) {
					theTab.movePlayer(0, 1);
					window.updateDrawbleContent();

				} else if (arg0.getKeyCode() == move_left_key) {
					theTab.movePlayer(-1, 0);
					window.updateDrawbleContent();
				} else if (arg0.getKeyCode() == move_right_key) {
					theTab.movePlayer(1, 0);
					window.updateDrawbleContent();
				} else if (arg0.getKeyCode() == send_eagle_key) {
					theTab.getEagle().StartEagle(theTab.getHero(),
							theTab.getSword());
					window.updateDrawbleContent();
				}

			}
		});

	}

	public void makeGame() {
		LabGenerator.prepareLab(labSize);
		char[][] lab = LabGenerator.getLab();
		System.out.println("starting game with mode="+mode);
		theTab = new Tabuleiro(0, 0, 0, 0, lab, mode, nrOfDragons);
		theTab.printLayout();
		window.updateDrawbleContent();
	}

	protected void Prepare_Editor(int editor_size) {
		Editor = new EditorWindowViewController(this, editor_size);
	}

	public char[][] getLayout() {
		return theTab.getLayout();
	}

	public void saveGame(String name) {
		try {
			name = name.concat(".save");
			FileOutputStream fileOut = new FileOutputStream(name);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(theTab);
			out.close();
			fileOut.close();

		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public void switch_layout(char[][] newlayout) {

		Tabuleiro newTab = EditorProcessor.generateInitializedTab(newlayout,mode);
		if (newTab == null) {// if an error occurred
			ErrorCode error = EditorProcessor.getErrorCode();
			if (error == ErrorCode.NotEnoughSpace) {
				// TODO case it hasn't enough empty spaces
				System.out.println("Not enough empty spaces");
			} else if (error == ErrorCode.NoExit) {
				// TODO case it hasn't a proper exit
				System.out.println("No exit");
			}
		}
		else{//if lab is a good one
			theTab = newTab;
			window.updateDrawbleContent();			
		}
	}

	protected void init_JDialog() {
		 Settings_Dialog d1 = new  Settings_Dialog(window.getFrame(),this);
		 d1.setVisible(true);
	}

	
	public void loadGame(String name) {
		try {
			name = name.concat(".save");
			FileInputStream fileIn = new FileInputStream(name);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			theTab = null;
			theTab = (Tabuleiro) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			JOptionPane.showMessageDialog(window.getFrame(),
					"File not Found !", "IOException not found",
					JOptionPane.ERROR_MESSAGE);
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			JOptionPane.showMessageDialog(window.getFrame(),
					"File is not valid !", "ClassNotFoundException",
					JOptionPane.ERROR_MESSAGE);
			c.printStackTrace();
			return;
		}
		theTab.printLayout();
		window.updateDrawbleContent();
	}

	public char[][] getBaseLayout() {
		return theTab.getBaseLayout();
	}
}
