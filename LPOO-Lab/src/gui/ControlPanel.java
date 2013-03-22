package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class ControlPanel extends JPanel {
	
	private JButton resetBtn;
	private JButton exitBtn;
	private SpringLayout theLO;
	public ControlPanel() {
		
		super();
		
		//set layout
		theLO=new SpringLayout();
		setLayout(theLO);
		
		//create Buttons
		setupResetButton();//order is relevant
		setupExitButton();
		
		
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

}
