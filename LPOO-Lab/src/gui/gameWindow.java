package gui;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import pictures.PictureLoader;

public class gameWindow {

	private JFrame frame;
	private LabPanel mainPanel;
	private GUI_InputHandler inputHandler;
	private char[][] editor_maze;
	private JMenuBar menuBar;

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public gameWindow(GUI_InputHandler handler) {
		try {
			initialize();
		} catch (Exception e) {
			System.out.println("exeption");
		}
		inputHandler = handler;
	}

	public void makeVisible() {
		frame.setVisible(true);
	}

	public void setupListeners() {

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

		String wall_pic_path = PictureLoader.getImagePath("brick2.png");
		String hero_pic_path = PictureLoader.getImagePath("hero.png");
		String dragon_pic_path = PictureLoader.getImagePath("dragon.png");
		String sleeping_dragon_pic_path = PictureLoader
				.getImagePath("sleeping_dragon.png");
		String sword_pic_path = PictureLoader.getImagePath("sword.png");
		String armed_pic_path = PictureLoader.getImagePath("hero.png");
		String empty_pic_path = PictureLoader.getImagePath("grass.jpg");
		String eagle_grass_pic_path = PictureLoader
				.getImagePath("eagle_grass.png");
		String eagle_wall_pic_path = PictureLoader
				.getImagePath("eagle_wall.png");

		frame.getContentPane().setLayout(null);

		mainPanel = new LabPanel(wall_pic_path, dragon_pic_path, hero_pic_path,
				sword_pic_path, sleeping_dragon_pic_path, armed_pic_path,
				empty_pic_path, eagle_grass_pic_path, eagle_wall_pic_path);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		mainPanel.setFocusable(true);

		mainPanel.requestFocusInWindow();
		frame.setResizable(false);
		menuBar = new JMenuBar();

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
									"Qual é a dimensão que deseja? (Min: 10, Max: 200)");
					if (editor_dim != null) {
						try {
							editordim = Integer.parseInt(editor_dim);
							if (editordim < 10 || editordim > 200) {
								JOptionPane
										.showMessageDialog(
												frame,
												"Erro, introduza um valor superior a 10 e menor que 200",
												"Warning",
												JOptionPane.WARNING_MESSAGE);
								valid = 0;
							} else
								valid = 2;
						}

						catch (NumberFormatException nfe) {
							JOptionPane.showMessageDialog(frame,
									"Erro, valor introduzido não é um inteiro",
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

	LabPanel getMainPanel() {
		return mainPanel;
	}

	GUI_InputHandler getInputHandler() {
		return inputHandler;
	}

	public void setSize(int x, int y) {
		this.frame.setSize(x, y);
		this.frame.setResizable(false);
	}

	public void updateDrawbleContent() {
		mainPanel.layoutModified(inputHandler.getLayout(),
				inputHandler.getBaseLayout());

		readjustSizes();
	}

	public JFrame getFrame() {
		return frame;
	}

	public char[][] getEditor() {
		return editor_maze;
	}

	public void redirectFocus() {
		mainPanel.requestFocusInWindow();
	}

	public void readjustSizes() {

		Dimension d = new Dimension(mainPanel.getWidth(), mainPanel.getHeight());
		frame.getContentPane().setPreferredSize(d);
		frame.pack();
	}
	
}
