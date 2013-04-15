package gui;

//TODO document it

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LabPanel extends JPanel {

	private final int Cell_Width = 32;
	private final int Cell_Height = 32;

	private ImageIcon brick_pic;
	private ImageIcon dragon_pic;
	private ImageIcon hero_pic;
	private ImageIcon sword_pic;
	private ImageIcon sleeping_dragon_pic;
	private ImageIcon armed_hero_pic;
	private ImageIcon empty_place_pic;
	private ImageIcon eagle_wall_pic;
	private ImageIcon eagle_grass_pic;

	private char[][] layout = null;
	private char[][] baseLayout = null;
	private JLabel[][] visualLayout = null;

	public LabPanel(String wall_pic_path, String dragon_pic_path,
			String hero_pic_path, String sword_pic_path,
			String sleeping_dragon_pic_path, String armed_hero_pic_path,
			String empty_pic_path, String eagle_grass_pic_path,
			String eagle_wall_pic_path) throws IOException {

		Image temp = ImageIO.read(new File(wall_pic_path));
		Image tempResized = temp.getScaledInstance(Cell_Width, Cell_Height,
				java.awt.Image.SCALE_SMOOTH);

		brick_pic = new ImageIcon(tempResized);
		System.out.println("brick");

		temp = ImageIO.read(new File(dragon_pic_path));
		tempResized = temp.getScaledInstance(Cell_Width, Cell_Height,
				java.awt.Image.SCALE_SMOOTH);

		dragon_pic = new ImageIcon(tempResized);

		System.out.println("dragon");

		temp = ImageIO.read(new File(hero_pic_path));
		tempResized = temp.getScaledInstance(Cell_Width, Cell_Height,
				java.awt.Image.SCALE_SMOOTH);

		hero_pic = new ImageIcon(tempResized);

		temp = ImageIO.read(new File(sword_pic_path));
		tempResized = temp.getScaledInstance(Cell_Width, Cell_Height,
				java.awt.Image.SCALE_SMOOTH);

		sword_pic = new ImageIcon(tempResized);

		temp = ImageIO.read(new File(sleeping_dragon_pic_path));
		tempResized = temp.getScaledInstance(Cell_Width, Cell_Height,
				java.awt.Image.SCALE_SMOOTH);

		sleeping_dragon_pic = new ImageIcon(tempResized);

		temp = ImageIO.read(new File(armed_hero_pic_path));
		tempResized = temp.getScaledInstance(Cell_Width, Cell_Height,
				java.awt.Image.SCALE_SMOOTH);

		armed_hero_pic = new ImageIcon(tempResized);

		System.out.println("just before");

		temp = ImageIO.read(new File(empty_pic_path));
		tempResized = temp.getScaledInstance(Cell_Width, Cell_Height,
				java.awt.Image.SCALE_SMOOTH);

		empty_place_pic = new ImageIcon(tempResized);
		System.out.println("just before");

		temp = ImageIO.read(new File(eagle_grass_pic_path));
		tempResized = temp.getScaledInstance(Cell_Width, Cell_Height,
				java.awt.Image.SCALE_SMOOTH);

		eagle_grass_pic = new ImageIcon(tempResized);

		temp = ImageIO.read(new File(eagle_wall_pic_path));
		tempResized = temp.getScaledInstance(Cell_Width, Cell_Height,
				java.awt.Image.SCALE_SMOOTH);

		eagle_wall_pic = new ImageIcon(tempResized);
	}

	public int getCell_Width() {
		return Cell_Width;
	}

	public int getCell_Height() {
		return Cell_Height;
	}

	public void layoutModified(char[][] newLayout, char[][] newBaseLayout) {

		if (visualLayout != null) {// if we had a layout before
			for (int i = 0; i < visualLayout.length; i++) {
				for (int f = 0; f < visualLayout.length; f++) {
					if (visualLayout[i][f] != null) {
						remove(visualLayout[i][f]);
					}
				}
			}
		}

		layout = newLayout;
		baseLayout = newBaseLayout;
		visualLayout = new JLabel[layout.length][layout.length];

		// visualLayout=new JLabel[layout.length][layout.length];

		for (int i = 0; i < layout.length; i++) {
			for (int f = 0; f < layout.length; f++) {
				visualLayout[i][f] = new JLabel();
				visualLayout[i][f].setBounds(f * Cell_Width, i * Cell_Height,
						Cell_Width, Cell_Height);
				add(visualLayout[i][f]);
			}
		}

		setBounds(getX(), getY(), layout.length * Cell_Width, layout.length
				* Cell_Height);// resize

		updateVisualLayout();
	}

	private void updateVisualLayout() {

		for (int i = 0; i < layout.length; i++) {
			for (int f = 0; f < layout.length; f++) {
				updateVisualLayoutCell(i, f);
			}
		}
		repaint();// draw again
	}

	private void updateVisualLayoutCell(int i, int f) {
		char theCell = layout[i][f];

		if (visualLayout[i][f] == null) {
			visualLayout[i][f] = new JLabel();
		}

		if (theCell == 'x') {
			visualLayout[i][f].setIcon(brick_pic);
		} else if (theCell == 'H') {
			visualLayout[i][f].setIcon(hero_pic);
		} else if (theCell == 'A') {
			visualLayout[i][f].setIcon(armed_hero_pic);
		} else if (theCell == 'D' || theCell == 'F') {
			visualLayout[i][f].setIcon(dragon_pic);
		} else if (theCell == 'd') {
			visualLayout[i][f].setIcon(sleeping_dragon_pic);
		} else if (theCell == 'E') {
			visualLayout[i][f].setIcon(sword_pic);
		} else if (theCell == 'e' && baseLayout[i][f] == 'x') {
			visualLayout[i][f].setIcon(eagle_wall_pic);
		} else if (theCell == 'e' && baseLayout[i][f] == ' ') {
			visualLayout[i][f].setIcon(eagle_grass_pic);
		} else if (theCell == ' ') {
			visualLayout[i][f].setIcon(empty_place_pic);
		}

		if (visualLayout[i][f] != null) {
			visualLayout[i][f].repaint();
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (layout != null) {
			updateVisualLayout();
		}

	}

	public Dimension getRequiredDimension() {
		if (layout == null) {
			return new Dimension(0, 0);
		}
		return new Dimension(layout.length * Cell_Width, layout.length
				* Cell_Height);

	}
}
