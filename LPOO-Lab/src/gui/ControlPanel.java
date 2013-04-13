package gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlPanel extends JPanel {

	
	
	
	private JLabel modeLabel;
	private JLabel nrOfDragonsLabel;
	private JLabel labSizeLabel;
	private JLabel labSizeValueLabel;
	private ButtonGroup gameModeButtonGroup;
	private gameWindow parent;
	
	
	
	
	private JSlider labSizeSlider;
	
	public ControlPanel(gameWindow parentWindow,int x,int y,int width,int height) {
		super();
		parent=parentWindow;
		setBounds(x,y,width,height);

		
		//create Buttons
		
//		setupSlider();
//		setupLabels();
//		setupGameModeButtons();
		
		
	}

	
	
	private void setupSlider(){
		
		labSizeSlider = new JSlider(10,50,10);
		int width=190;
		int height=50;
		labSizeSlider.setBounds((getWidth()-width)/2, getHeight()-50, width, height);
		
		
		//add listener to be used to change the value label value!!!
		labSizeSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				JSlider source=(JSlider)arg0.getSource();
				
				int value = source.getValue();
				String theTXT=value+" x "+value;
				labSizeValueLabel.setText(theTXT);
				
				if(!((JSlider)arg0.getSource()).getValueIsAdjusting()){//if it has been released (not adjusting anymore)
					parent.redirectFocus();
				}
				
				
			}
		});
		
		
		add(labSizeSlider);
	}
		
	private void setupLabels(){
		
		int width=100;
		int height=10;
		
		modeLabel=new JLabel("Mode:");
		modeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//modeLabel.setBounds((getWidth()-width)/2, exitBtn.getY()+exitBtn.getHeight()+50, width, height);
	
		
		add(modeLabel);
		
		
		nrOfDragonsLabel=new JLabel("Nr. of Dragons:");
		nrOfDragonsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		add(nrOfDragonsLabel);
		
		labSizeLabel=new JLabel("Size:");
		labSizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		labSizeLabel.setBounds((getWidth()-width)/2, labSizeSlider.getY()-25-height, width, height);
		
		add(labSizeLabel);
		
		
		
		String theStr=""+labSizeSlider.getValue()+" x "+labSizeSlider.getValue();
		labSizeValueLabel=new JLabel(theStr);
		labSizeValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		labSizeValueLabel.setBounds((getWidth()-width)/2, labSizeSlider.getY()-5-height, width, height);
		
		add(labSizeValueLabel);
	}
	JSlider getLabSizeSlider(){
		return labSizeSlider;
	}

	private void setupGameModeButtons(){
		
		int spacing=getWidth()/10;
		int theY=modeLabel.getY()+modeLabel.getHeight();
		JRadioButton btn1=new JRadioButton("1");
		btn1.setBounds(spacing, theY, 2*spacing, 2*spacing);
		JRadioButton btn2=new JRadioButton("2");
		btn2.setBounds(4*spacing, theY, 2*spacing, 2*spacing);
		JRadioButton btn3=new JRadioButton("3");
		btn3.setBounds(7*spacing, theY, 2*spacing, 2*spacing);
		
		
		
		gameModeButtonGroup=new ButtonGroup();
		gameModeButtonGroup.add(btn1);
		gameModeButtonGroup.add(btn2);
		gameModeButtonGroup.add(btn3);
		
		add(btn1);
		add(btn2);
		add(btn3);
		
		
	}

}
