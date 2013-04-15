package gui;
/**
 * A classed used as a controller for the editor View
 * 
 */

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import logica_jogo.EditorProcessor;
import logica_jogo.EditorProcessor.ErrorCode;
import logica_jogo.LabGenerator;
import pictures.PictureLoader;

public class EditorWindowViewController {
	private LabPanelViewController editPanelVC;
	private GUI_Processor editHandler;
	private char[][] labedit;
	private JFrame theFrame;
	private EditorProcessor theProcessor;

	public EditorWindowViewController(GUI_Processor handler, int editor_size) {
		editHandler = handler;
		labedit = new char[editor_size][editor_size];
		LabGenerator.initLab(editor_size);
		labedit = LabGenerator.getLab();
		try {
			initialize();
		} catch (Exception e) {
			System.out.println("exeption");
		}

	}

	public void makeVisible() {
		theFrame.setVisible(true);
	}

	public void setupListeners() {

	}

	/**
	 * Initialize the contents of the frame and set's up necessary listeners
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		theFrame= new JFrame();
		theFrame.setBounds(300, 300, 140, 173);
		theFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		
		setupReturnListener();
		

		String[] thePics=getPicturesNames();

		theFrame.getContentPane().setLayout(null);

		editPanelVC = new LabPanelViewController(thePics);
		editPanelVC.getPanel().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					int x = e.getX() / editPanelVC.getCell_Width();
					int y = e.getY() / editPanelVC.getCell_Height();
					update_Cell(x, y);
					/* TODO: Place update function on other class */

					editPanelVC.layoutModified(labedit, labedit);

				}

			}
		});
		editPanelVC.getPanel().setSize(500, 500);
		theFrame.getContentPane().add(editPanelVC.getPanel());
		editPanelVC.getPanel().setLayout(null);
		editPanelVC.getPanel().setFocusable(true);

		editPanelVC.getPanel().requestFocusInWindow();
		editPanelVC.layoutModified(labedit, labedit);
		theFrame.setBounds(300, 300, editPanelVC.getPanel().getWidth(), editPanelVC.getPanel().getHeight());
		// Solves the problem with border dimensions on different looks and
		// feels (Operative systems)
		Dimension d = new Dimension(editPanelVC.getPanel().getWidth(), editPanelVC.getPanel().getHeight());
		theFrame.getContentPane().setPreferredSize(d);
		theFrame.pack();
		makeVisible();

	}

	/**
	 * Function called when a particular cell is supposed to be updated because it was edited by the user
	 * 
	 * 
	 * @param x the x position on the grid
	 * @param y the y position on the grid
	 */
	private void update_Cell(int x, int y) {
		switch (labedit[y][x]) {
		case 'x':
			labedit[y][x] = ' ';
			break;
		case ' ':
			labedit[y][x] = 'H';
			break;
		case 'H':
			labedit[y][x] = 'D';
			break;
		case 'D':
			labedit[y][x] = 'E';
			break;
		case 'E':
			labedit[y][x] = 'x';
			break;
		default:
			break;
		}

	}


	/**
	 * Sets up the listener called when the editor returns.
	 * 
	 * 
	 */
	private void setupReturnListener(){
		theFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				theProcessor.generateInitializedTab(labedit, editHandler.getMode());
				
				if(theProcessor.getErrorCode()==ErrorCode.NoErr){//In case no error
					promptUserToSaveEditedMap(e);
					
				}
				else{
					promptUserWithErrorMessage(e,theProcessor.getErrorCode());
					
				}
				
			}

		});
	}

	private void promptUserToSaveEditedMap(WindowEvent e) {
		JFrame frame = (JFrame) e.getSource();
		
		int result = JOptionPane.showConfirmDialog(frame,
				"Do you want to use Editor Map ?", "Exit Editor",
				JOptionPane.YES_NO_OPTION);
		
		if (result == JOptionPane.YES_OPTION) {
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			editHandler.switch_layout(labedit);
		} else {
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
	}
	
	private void promptUserWithErrorMessage(WindowEvent e,ErrorCode error) {
		JFrame frame = (JFrame) e.getSource();
		String txt="";
		if(error==ErrorCode.NoExit){
			txt="A maze with no exit was created.\nIn order to be saved, a maze must be a valid one.\nLeave Editor and discard changes?";
		}
		else if(error==ErrorCode.NotEnoughSpace){
			txt="A maze with not enough space was created.\nIn order to be saved, a maze must be a valid one.\nLeave Editor and discard changes?";
		}
		int result=JOptionPane.showConfirmDialog(frame,
				txt, "Exit Editor",
				JOptionPane.ERROR_MESSAGE);
		if (result == JOptionPane.YES_OPTION) {
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		
	}
	

	private String[] getPicturesNames(){
		String[] picturesArray=new String[9];
		
		picturesArray[0] = PictureLoader.getImagePath("brick2.png");
		picturesArray[1] = PictureLoader.getImagePath("hero.png");
		picturesArray[2] = PictureLoader.getImagePath("dragon.png");
		picturesArray[3] = PictureLoader
				.getImagePath("sleeping_dragon.png");
		picturesArray[4] = PictureLoader.getImagePath("sword.png");
		picturesArray[5] = PictureLoader.getImagePath("hero.png");
		picturesArray[6] = PictureLoader.getImagePath("grass.jpg");
		picturesArray[7] = PictureLoader
				.getImagePath("eagle_grass.png");
		picturesArray[8] = PictureLoader
				.getImagePath("eagle_wall.png");
		return picturesArray;
		
	}
}