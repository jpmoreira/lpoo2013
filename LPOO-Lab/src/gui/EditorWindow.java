package gui;

import java.io.IOException;

import javax.swing.JFrame;

import logica_jogo.LabGenerator;

import pictures.PictureLoader;

public class EditorWindow extends JFrame{
	private LabPanel editPanel;
	private GUI_InputHandler editHandler;
	private char [][] labedit;
	
	public EditorWindow(GUI_InputHandler handler,int editor_size) {
		editHandler = handler;
		labedit = new char [editor_size][editor_size];
		LabGenerator.initLab(editor_size);
		labedit = LabGenerator.getLab();
		try {
			initialize();
		} catch (Exception e) {
			System.out.println("exeption");
		}
		
	}

	public void makeVisible() {
		this.setVisible(true);
	}

	public void setupListeners() {

	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		this.setBounds(300,300, 500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

		this.getContentPane().setLayout(null);

		editPanel = new LabPanel(wall_pic_path, dragon_pic_path, hero_pic_path,
				sword_pic_path, sleeping_dragon_pic_path, armed_pic_path,
				empty_pic_path, eagle_grass_pic_path, eagle_wall_pic_path);
		this.getContentPane().add(editPanel);
		editPanel.setLayout(null);
		editPanel.setFocusable(true);

		editPanel.requestFocusInWindow();
		editPanel.layoutModified(labedit, labedit);
		this.setBounds(300, 300, editPanel.getWidth(), editPanel.getHeight());
		makeVisible();
}
	
}