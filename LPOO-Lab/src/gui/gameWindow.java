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


public class gameWindow {

	private JFrame frame;


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
		
		JPanel mainPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, mainPanel, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, mainPanel, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, mainPanel, -200, SpringLayout.EAST, frame.getContentPane());
		mainPanel.setBackground(Color.YELLOW);
		springLayout.putConstraint(SpringLayout.WEST, mainPanel, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(mainPanel);
		
		ControlPanel controlsPanel = new ControlPanel();
		springLayout.putConstraint(SpringLayout.NORTH, controlsPanel, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, controlsPanel, 10, SpringLayout.EAST, mainPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, controlsPanel, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, controlsPanel, -10, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(controlsPanel);
		
		
	}
}
