package gui;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import logica_jogo.LabGenerator;
import pictures.PictureLoader;

public class EditorWindow extends JFrame {
	private LabPanel editPanel;
	private GUI_InputHandler editHandler;
	private char[][] labedit;

	public EditorWindow(GUI_InputHandler handler, int editor_size) {
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
		this.setBounds(300, 300, 140, 173);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
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
		});

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
		editPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					int x = e.getX() / editPanel.getCell_Width();
					int y = e.getY() / editPanel.getCell_Height();
					update_Cell(x, y);
					/* TODO: Place update function on other class */

					editPanel.layoutModified(labedit, labedit);

				}

			}
		});
		editPanel.setSize(500, 500);
		this.getContentPane().add(editPanel);
		editPanel.setLayout(null);
		editPanel.setFocusable(true);

		editPanel.requestFocusInWindow();
		editPanel.layoutModified(labedit, labedit);
		this.setBounds(300, 300, editPanel.getWidth(), editPanel.getHeight());
		// Solves the problem with border dimensions on different looks and
		// feels (Operative systems)
		Dimension d = new Dimension(editPanel.getWidth(), editPanel.getHeight());
		this.getContentPane().setPreferredSize(d);
		this.pack();
		makeVisible();

	}

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

}