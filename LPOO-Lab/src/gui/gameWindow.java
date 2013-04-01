package gui;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JSplitPane;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;


public class gameWindow {

	private JFrame frame;
	private ControlPanel theControlPanel;
	private LabPanel mainPanel;
	private GUI_InputHandler inputHandler;


	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public gameWindow(GUI_InputHandler handler) {
		try {
			initialize();			
		} catch (Exception e) {
			System.out.println("exeption");
		}
		inputHandler=handler;
	}

	public void makeVisible(){
		frame.setVisible(true);
	}
	public void setupListeners(){
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		String wall_pic_path="/home/mppl/Desktop/pics/raw/brick2.png";
		String hero_pic_path="/home/mppl/Desktop/pics/final/hero.png";
		String dragon_pic_path="/home/mppl/Desktop/pics/final/dragon.png";
		String sleeping_dragon_pic_path="/home/mppl/Desktop/pics/final/sleeping_dragon.png";
		String sword_pic_path="/home/mppl/Desktop/pics/final/sword.png";
		String armed_pic_path="/home/mppl/Desktop/pics/final/hero.png";
		String empty_pic_path="/home/mppl/Desktop/pics/raw/grass.jpg";
		frame.getContentPane().setLayout(null);
		
		
		theControlPanel = new ControlPanel(this,10,10,400,50);
		theControlPanel.setLayout(null);
		//theControlPanel.setBackground(Color.yellow);
		frame.getContentPane().add(theControlPanel);
		
		mainPanel = new LabPanel(wall_pic_path,dragon_pic_path,hero_pic_path,sword_pic_path,sleeping_dragon_pic_path,armed_pic_path,empty_pic_path);
		mainPanel.setBounds(10, 10+theControlPanel.getHeight()+theControlPanel.getY(), 240, 280);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		mainPanel.setFocusable(true);
		
		
		mainPanel.requestFocusInWindow();
		frame.setResizable(false);
	
	}
	ControlPanel getControlPanel(){
		return theControlPanel;
	}
	LabPanel getMainPanel(){
		return mainPanel;
	}
	GUI_InputHandler getInputHandler(){
		return inputHandler;
	}
	
	public void setSize(int x,int y){
		this.frame.setSize(x,y);
		this.frame.setResizable(false);
	}
	public void updateDrawbleContent(){
		mainPanel.layoutModified(inputHandler.getLayout());
		readjustSizes();
	}
	public JFrame getFrame(){
		return frame;
	}
	public void redirectFocus(){
		mainPanel.requestFocusInWindow();
	}
	
	public void readjustSizes(){

		//TODO change thiss!!!!
		Dimension d=mainPanel.getRequiredDimension();
		theControlPanel.setBounds(10, theControlPanel.getY(), mainPanel.getWidth(), theControlPanel.getHeight());
		int width=(int) d.getWidth()+theControlPanel.getWidth()+5;
		int height=(int) d.getHeight()+theControlPanel.getHeight();
		frame.setSize(mainPanel.getWidth()+20,mainPanel.getHeight()+theControlPanel.getHeight()+20);
	}
	
}
