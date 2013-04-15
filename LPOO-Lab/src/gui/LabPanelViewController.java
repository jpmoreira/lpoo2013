package gui;

/**
 * A viewController made to handle some game related functionalities more directly related to the panel the maze is in. 
 * 
 * 
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LabPanelViewController{

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
	private ImageIcon closed_door_pic;//TODO add to documentation
	private ImageIcon open_door_pic;

	private char[][] layout = null;
	private char[][] baseLayout = null;
	private JLabel[][] visualLayout = null;
	private JPanel thePanel;

	/**
	 * A simple constructor that recieves and array with the location of the pictures to be drawn to show the maze.
	 * 
	 * 
	 * @param picsArray An array with the path of pictures to be loaded
	 * @throws IOException
	 */ 
	public LabPanelViewController(String[] picsArray) throws IOException {

		thePanel=new JPanel();
		loadPictures(picsArray);
	}

	private void loadPictures(String[] picsArray) throws IOException {
		Image temp = ImageIO.read(new File(picsArray[0]));
		Image tempResized = temp.getScaledInstance(Cell_Width, Cell_Height,
				java.awt.Image.SCALE_SMOOTH);
		brick_pic = new ImageIcon(tempResized);


		temp = ImageIO.read(new File(picsArray[1]));
		tempResized = temp.getScaledInstance(Cell_Width, Cell_Height,
				java.awt.Image.SCALE_SMOOTH);
		hero_pic = new ImageIcon(tempResized);
		
		temp = ImageIO.read(new File(picsArray[2]));
		tempResized = temp.getScaledInstance(Cell_Width, Cell_Height,
				java.awt.Image.SCALE_SMOOTH);
		dragon_pic = new ImageIcon(tempResized);


		temp = ImageIO.read(new File(picsArray[3]));
		tempResized = temp.getScaledInstance(Cell_Width, Cell_Height,
				java.awt.Image.SCALE_SMOOTH);
		sleeping_dragon_pic = new ImageIcon(tempResized);
		
		temp = ImageIO.read(new File(picsArray[4]));
		tempResized = temp.getScaledInstance(Cell_Width, Cell_Height,
				java.awt.Image.SCALE_SMOOTH);
		sword_pic = new ImageIcon(tempResized);

		temp = ImageIO.read(new File(picsArray[5]));
		tempResized = temp.getScaledInstance(Cell_Width, Cell_Height,
				java.awt.Image.SCALE_SMOOTH);
		armed_hero_pic = new ImageIcon(tempResized);

		temp = ImageIO.read(new File(picsArray[6]));
		tempResized = temp.getScaledInstance(Cell_Width, Cell_Height,
				java.awt.Image.SCALE_SMOOTH);
		empty_place_pic = new ImageIcon(tempResized);
		open_door_pic= new ImageIcon(tempResized);

		temp = ImageIO.read(new File(picsArray[7]));
		tempResized = temp.getScaledInstance(Cell_Width, Cell_Height,
				java.awt.Image.SCALE_SMOOTH);
		eagle_grass_pic = new ImageIcon(tempResized);

		temp = ImageIO.read(new File(picsArray[8]));
		tempResized = temp.getScaledInstance(Cell_Width, Cell_Height,
				java.awt.Image.SCALE_SMOOTH);
		eagle_wall_pic = new ImageIcon(tempResized);
		
		temp = ImageIO.read(new File(picsArray[9]));
		tempResized = temp.getScaledInstance(Cell_Width, Cell_Height,
				java.awt.Image.SCALE_SMOOTH);
		closed_door_pic= new ImageIcon(tempResized);
	}

	public int getCell_Width() {
		return Cell_Width;
	}

	public int getCell_Height() {
		return Cell_Height;
	}

	/**
	 * A method that should be called when the layout changes either in dimension or content.
	 * Both a layout and and empty layout are required since some pictures of some elements are dependent of whats behind it.
	 * 
	 * 
	 * @param newLayout The new layout
	 * @param newBaseLayout A copy of the new layout without no elements in it
	 */
	public void layoutModified(char[][] newLayout, char[][] newBaseLayout) {

		if (visualLayout != null) {// if we had a layout before
			for (int i = 0; i < visualLayout.length; i++) {
				for (int f = 0; f < visualLayout.length; f++) {
					if (visualLayout[i][f] != null) {
						thePanel.remove(visualLayout[i][f]);
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
				thePanel.add(visualLayout[i][f]);
			}
		}

		thePanel.setBounds(thePanel.getX(), thePanel.getY(), layout.length * Cell_Width, layout.length
				* Cell_Height);// resize

		updateGraphicLayout();
	}

	
	/**
	 * 
	 * A method that updates the Graphic layout based on the layout currently set.
	 * 
	 */
	private void updateGraphicLayout() {

		for (int i = 0; i < layout.length; i++) {
			for (int f = 0; f < layout.length; f++) {
				updateGraphicLayoutCell(i, f);
			}
		}
		thePanel.repaint();// draw again
	}

	private void updateGraphicLayoutCell(int i, int f) {
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
		} else if (theCell == 'S'){
			visualLayout[i][f].setIcon(closed_door_pic);
		}else if(theCell == 's'){
			visualLayout[i][f].setIcon(open_door_pic);
		}
		

		if (visualLayout[i][f] != null) {
			visualLayout[i][f].repaint();
		}

	}


	public Dimension getRequiredDimension() {
		if (layout == null) {
			return new Dimension(0, 0);
		}
		return new Dimension(layout.length * Cell_Width, layout.length
				* Cell_Height);

	}
	public JPanel getPanel(){
		return thePanel;
	}
}
