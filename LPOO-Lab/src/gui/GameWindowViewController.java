package gui;
/**
 * A view Controller for the game window
 * 
 */

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import pictures.PictureLoader;

public class GameWindowViewController {

	private JFrame frame;
	private LabPanelViewController mainPanelVC;
	private GUI_Processor inputHandler;
	private char[][] editor_maze;
	private JMenuBar menuBar;

	/**
	 * Simple Constructor.
	 * 
	 * @param handler An object of type GUI_Processor that will handle user input and do some processing as well as some interaction between different windows.
	 */
	public GameWindowViewController(GUI_Processor handler) {
		try {
			initialize();
		} catch (Exception e) {
			System.out.println("exeption");
		}
		inputHandler = handler;
	}

	/**
	 * A method to make the window visible
	 * 
	 */
	public void makeVisible() {
		frame.setVisible(true);
	}


	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		

		frame.getContentPane().setLayout(null);

		String[] picNames=getPicturesNames();
		
		mainPanelVC = new LabPanelViewController(picNames);
		frame.getContentPane().add(mainPanelVC.getPanel());
		mainPanelVC.getPanel().setLayout(null);
		mainPanelVC.getPanel().setFocusable(true);

		mainPanelVC.getPanel().requestFocusInWindow();
		frame.setResizable(false);
		menuBar = new JMenuBar();

		setupMenuBar();
	}

	
	/**
	 * Sets up the menu bar present on the game window.
	 * 
	 * 
	 */
	private void setupMenuBar() {
		frame.setJMenuBar(menuBar);

		JMenu GameMenu = new JMenu("Game");
		menuBar.add(GameMenu);

		JMenuItem NewGame_menu = new JMenuItem("New Game");
		NewGame_menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				int result = JOptionPane.showConfirmDialog(frame,
						"Make a new Game ?", "New Game",
						JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					inputHandler.makeGame();
				}

			}

		});
		GameMenu.add(NewGame_menu);

		JMenuItem SaveGame_menu = new JMenuItem("Save Game");
		SaveGame_menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				String save_name = JOptionPane.showInputDialog(frame,
						"Save Name");
				if (save_name != null)
					inputHandler.saveGame(save_name);
				redirectFocus();
			}
		});
		GameMenu.add(SaveGame_menu);

		JMenuItem LoadGame_menu = new JMenuItem("Load Game");
		LoadGame_menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				String load_name = JOptionPane.showInputDialog(frame,
						"Load Name");
				if (load_name != null)
					inputHandler.loadGame(load_name);
				redirectFocus();
			}
		});
		GameMenu.add(LoadGame_menu);

		JMenuItem ExitGame_menu = new JMenuItem("Exit");
		ExitGame_menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				int result = JOptionPane.showConfirmDialog(frame,
						"Do you want to exit the game ?", "Exit Game",
						JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					System.exit(0);
				}

			}

		});
		GameMenu.add(ExitGame_menu);

		JMenu mnEditor = new JMenu("Editor");
		menuBar.add(mnEditor);

		JMenuItem mntmNewEditor = new JMenuItem("New Editor");
		mntmNewEditor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				int valid = 0;
				int editordim = 0;
				do {
					String editor_dim = JOptionPane
							.showInputDialog(frame,
									"What's the desired dimension for the maze? (Min: 10, Max: 200)");
					if (editor_dim != null) {
						try {
							editordim = Integer.parseInt(editor_dim);
							if (editordim < 10 || editordim > 200) {
								JOptionPane
										.showMessageDialog(
												frame,
												"Error, please write a number bigger than 9 and smaller than 201",
												"Warning",
												JOptionPane.WARNING_MESSAGE);
								valid = 0;
							} else
								valid = 2;
						}

						catch (NumberFormatException nfe) {
							JOptionPane.showMessageDialog(frame,
									"Erro, valor introduzido n�o � um inteiro",
									"Warning", JOptionPane.WARNING_MESSAGE);
							valid = 0;
						}
					} else
						valid = 1;

					if (valid == 2)
						inputHandler.Prepare_Editor(editordim);
				} while (valid == 0);

			}
		});
		mnEditor.add(mntmNewEditor);

		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);
		JMenuItem mntmSettings_options = new JMenuItem("Options");
		mntmSettings_options.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				inputHandler.init_JDialog();
			}

		});

		mnSettings.add(mntmSettings_options);
	}

	public JPanel getMainPanel() {
		return mainPanelVC.getPanel();
	}

	GUI_Processor getInputHandler() {
		return inputHandler;
	}

	public void setSize(int x, int y) {
		this.frame.setSize(x, y);
		this.frame.setResizable(false);
	}

	public void updateDrawbleContent() {
		mainPanelVC.layoutModified(inputHandler.getLayout(),
				inputHandler.getBaseLayout());

		readjustSizes();
	}

	public JFrame getFrame() {
		return frame;
	}

	public char[][] getEditor() {
		return editor_maze;
	}

	/**
	 * A function to redirect focus to the game panel.
	 * 
	 * 
	 */
	public void redirectFocus() {
		mainPanelVC.getPanel().requestFocusInWindow();
	}

	/**
	 * A method used to resize the frame to fit the game content
	 * 
	 */
	public void readjustSizes() {

		Dimension d = new Dimension(mainPanelVC.getPanel().getWidth(), mainPanelVC.getPanel().getHeight());
		frame.getContentPane().setPreferredSize(d);
		frame.pack();
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
