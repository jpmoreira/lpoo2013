package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SpringLayout;

public class ControlPanel extends JPanel {

	private SpringLayout theLO;
	
	private JButton resetBtn;
	private JButton exitBtn;
	
	
	private JLabel modeLabel;
	private JLabel nrOfDragonsLabel;
	
	private JSlider labSizeSlider;
	
	public ControlPanel() {
		
		super();
		
		//set layout
		theLO=new SpringLayout();
		setLayout(theLO);
		
		//create Buttons
		setupResetButton();//order is relevant
		setupExitButton();
		
		setupLabels();
		setupSlider();
		
		
	}
	
	private void setupResetButton(){
		resetBtn = new JButton("Reset");
		theLO.putConstraint(SpringLayout.NORTH, resetBtn, 32, SpringLayout.NORTH, this);
		theLO.putConstraint(SpringLayout.WEST, resetBtn, 40, SpringLayout.WEST, this);
		theLO.putConstraint(SpringLayout.SOUTH, resetBtn, 67, SpringLayout.NORTH, this);
		theLO.putConstraint(SpringLayout.EAST, resetBtn, -40, SpringLayout.EAST, this);
		
		
		//setup listener
		resetBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//TODO implement this listener
			}
		});
		
		
		add(resetBtn);
	}
	
	private void setupExitButton(){
		exitBtn = new JButton("Exit");
		theLO.putConstraint(SpringLayout.NORTH, exitBtn, 30, SpringLayout.SOUTH, resetBtn);
		theLO.putConstraint(SpringLayout.WEST, exitBtn, 40, SpringLayout.WEST, this);
		theLO.putConstraint(SpringLayout.SOUTH, exitBtn, 65, SpringLayout.SOUTH, resetBtn);
		theLO.putConstraint(SpringLayout.EAST, exitBtn, -40, SpringLayout.EAST, this);
		
		
		//setup listener
		exitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}
		});
		
		
		add(exitBtn);
	}

	
	
	private void setupSlider(){
		
		labSizeSlider = new JSlider(10,50,10);
		theLO.putConstraint(SpringLayout.WEST, labSizeSlider, 0, SpringLayout.WEST, this);
		theLO.putConstraint(SpringLayout.SOUTH, labSizeSlider, -89, SpringLayout.SOUTH, this);
		theLO.putConstraint(SpringLayout.EAST, labSizeSlider, 180, SpringLayout.WEST, this);
		add(labSizeSlider);
	}
		
	private void setupLabels(){
		
		modeLabel=new JLabel("Mode:");
		theLO.putConstraint(SpringLayout.NORTH, modeLabel, 50, SpringLayout.SOUTH, exitBtn);
		theLO.putConstraint(SpringLayout.EAST, modeLabel, -60, SpringLayout.EAST, this);
		theLO.putConstraint(SpringLayout.WEST, modeLabel, 60, SpringLayout.WEST, this);
		
		add(modeLabel);
		
		
		nrOfDragonsLabel=new JLabel("Nr. of Dragons:");
		theLO.putConstraint(SpringLayout.NORTH, nrOfDragonsLabel, 50, SpringLayout.SOUTH, modeLabel);
		theLO.putConstraint(SpringLayout.EAST, nrOfDragonsLabel, -30, SpringLayout.EAST, this);
		theLO.putConstraint(SpringLayout.WEST, nrOfDragonsLabel, 30, SpringLayout.WEST, this);
		
		add(nrOfDragonsLabel);
	}
	JSlider getLabSizeSlider(){
		return labSizeSlider;
	}
}
