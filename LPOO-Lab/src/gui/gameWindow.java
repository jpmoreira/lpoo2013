package gui;
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
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;


public class gameWindow {

	private JFrame frame;
	private ControlPanel theControlPanel;
	private JPanel mainPanel;


	/**
	 * Create the application.
	 */
	public gameWindow() {
		initialize();
	}

	public void makeVisible(){
		frame.setVisible(true);
	}
	public void setupListeners(){
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		mainPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, mainPanel, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, mainPanel, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, mainPanel, -200, SpringLayout.EAST, frame.getContentPane());
		mainPanel.setBackground(Color.YELLOW);
		springLayout.putConstraint(SpringLayout.WEST, mainPanel, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(mainPanel);
		
		theControlPanel = new ControlPanel();
		SpringLayout springLayout_1 = (SpringLayout) theControlPanel.getLayout();
		springLayout.putConstraint(SpringLayout.NORTH, theControlPanel, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, theControlPanel, 10, SpringLayout.EAST, mainPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, theControlPanel, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, theControlPanel, -10, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(theControlPanel);
		
		
		
	}
	ControlPanel getControlPanel(){
		return theControlPanel;
	}
	JPanel getMainPanel(){
		return mainPanel;
	}
}
