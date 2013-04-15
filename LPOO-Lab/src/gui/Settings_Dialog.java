package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Settings_Dialog extends JDialog {
	
	private JTextField up_key_textField;
	private JTextField down_key_textField;
	private JTextField left_key_textField;
	private JTextField right_key_textField;
	private JTextField eagle_key_textField;
	
	private int key_up_code, key_left_code, key_right_code, key_down_code, key_eagle_code, temp_code;
	
	private JLabel slider_value_label;
	private JLabel dragonNr_slider_label;
	private ButtonGroup game_mode_btnGroup;
	private GUI_Processor input_Handler;

	public Settings_Dialog(JFrame frame, GUI_Processor handler) {

		super(frame, "Settings", true);
		
		input_Handler=handler;
		
		getSavedKeys();
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		getContentPane().setLayout(null);

		setupOKandCancelButtons();

		setupMazeSizeSliderAndLabel();

		setupGameModeButtons();

		setupKeyLabels();

		setBounds(frame.getX(), frame.getY(), 614, 387);

	}

	private void getSavedKeys() {
		key_up_code = input_Handler.getMove_up_key_code();
		key_down_code = input_Handler.getMove_down_key_code();
		key_left_code = input_Handler.getMove_left_key_code();
		key_right_code = input_Handler.getMove_right_key_code();
		key_eagle_code = input_Handler.getSend_eagle_key_code();
	}

	private void save_settings() {
		int mode = 0;
		for (Enumeration<AbstractButton> buttons = game_mode_btnGroup.getElements(); buttons
				.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();
			if (button.isSelected()) {
				char selected = button.getText().charAt(0);
				mode = (Character.getNumericValue(selected)) ;
			}
		}

		input_Handler.setPrefs(mode, Integer.parseInt(slider_value_label.getText()),
				Integer.parseInt(dragonNr_slider_label.getText()));
		input_Handler
				.savekeyPrefs(key_up_code, key_down_code, key_right_code, key_left_code, key_eagle_code);
		this.dispose();
	}

	private JTextField setKeyLabelText(JTextField textfield, int theKcode) {
		if (theKcode == KeyEvent.VK_UP) {
			textfield.setText("UP");
		} else if (theKcode == KeyEvent.VK_DOWN) {
			textfield.setText("DOWN");
		}

		else if (theKcode == KeyEvent.VK_LEFT) {
			textfield.setText("LEFT");
		}

		else if (theKcode == KeyEvent.VK_RIGHT) {
			textfield.setText("RIGHT");
		} else
			textfield.setText(String.valueOf((char) theKcode));

		return textfield;
	}

	private void addlistener(JTextField textfield) {

		textfield.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent arg0) {

				arg0.consume();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int theKcode = e.getKeyCode();

				if (theKcode == KeyEvent.VK_UP || theKcode == KeyEvent.VK_DOWN
						|| theKcode == KeyEvent.VK_LEFT
						|| theKcode == KeyEvent.VK_RIGHT) {

					if (!isValidKey(theKcode)) {
						JTextField theTextField = (JTextField) e.getSource();
						String newTxt = theTextField.getText();
						theTextField.setText(newTxt);
						return;
					}

					if (theKcode == KeyEvent.VK_UP) {
						((JTextField) e.getSource()).setText("UP");
						temp_code = e.getKeyCode();
					}

					else if (theKcode == KeyEvent.VK_DOWN) {
						((JTextField) e.getSource()).setText("DOWN");
						temp_code = e.getKeyCode();
					}

					else if (theKcode == KeyEvent.VK_LEFT) {
						((JTextField) e.getSource()).setText("LEFT");
						temp_code = e.getKeyCode();
					}

					else if (theKcode == KeyEvent.VK_RIGHT) {
						((JTextField) e.getSource()).setText("RIGHT");
						temp_code = theKcode;
					}
					if (e.getSource() == up_key_textField)
						key_up_code = temp_code;
					else if (e.getSource() == down_key_textField)
						key_down_code = temp_code;
					else if (e.getSource() == left_key_textField)
						key_left_code = temp_code;
					else if (e.getSource() == right_key_textField)
						key_right_code = temp_code;
					else if (e.getSource() == eagle_key_textField)
						key_eagle_code = temp_code;
				}

				else {// if not a arrow key
					if (isValidKey(theKcode)) {

						String newText = String.valueOf((char) theKcode);
						((JTextField) e.getSource()).setText(newText);
						temp_code = theKcode;
					} else {
						return;
					}

					if (e.getSource() == up_key_textField)
						key_up_code = temp_code;
					else if (e.getSource() == down_key_textField)
						key_down_code = temp_code;
					else if (e.getSource() == left_key_textField)
						key_left_code = temp_code;
					else if (e.getSource() == right_key_textField)
						key_right_code = temp_code;
					else if (e.getSource() == eagle_key_textField)
						key_eagle_code = temp_code;

				}

			}

		});

	}

	void setupKeyLabels() {
		JLabel lblKeyboardKeys = new JLabel("Keyboard keys");
		lblKeyboardKeys.setBounds(10, 164, 130, 14);
		getContentPane().add(lblKeyboardKeys);

		up_key_textField = new JTextField();
		addlistener(up_key_textField);
		up_key_textField.setBounds(200, 193, 40, 20);
		getContentPane().add(up_key_textField);
		up_key_textField.setHorizontalAlignment(JTextField.CENTER);
		up_key_textField.setColumns(10);
		up_key_textField = setKeyLabelText(up_key_textField,
				input_Handler.getMove_up_key_code());

		down_key_textField = new JTextField();
		addlistener(down_key_textField);
		down_key_textField.setBounds(200, 224, 40, 20);
		getContentPane().add(down_key_textField);
		down_key_textField.setHorizontalAlignment(JTextField.CENTER);
		down_key_textField.setColumns(10);
		down_key_textField = setKeyLabelText(down_key_textField,
				input_Handler.getMove_down_key_code());

		left_key_textField = new JTextField();
		addlistener(left_key_textField);
		left_key_textField.setBounds(155, 224, 40, 20);
		getContentPane().add(left_key_textField);
		left_key_textField.setHorizontalAlignment(JTextField.CENTER);
		left_key_textField.setColumns(10);
		left_key_textField = setKeyLabelText(left_key_textField,
				input_Handler.getMove_left_key_code());

		right_key_textField = new JTextField();
		addlistener(right_key_textField);
		right_key_textField.setBounds(245, 224, 40, 20);
		getContentPane().add(right_key_textField);
		right_key_textField.setHorizontalAlignment(JTextField.CENTER);
		right_key_textField.setColumns(10);
		right_key_textField = setKeyLabelText(right_key_textField,
				input_Handler.getMove_right_key_code());

		eagle_key_textField = new JTextField();
		addlistener(eagle_key_textField);
		eagle_key_textField.setBounds(468, 210, 40, 20);
		getContentPane().add(eagle_key_textField);
		eagle_key_textField.setHorizontalAlignment(JTextField.CENTER);
		eagle_key_textField.setColumns(10);
		eagle_key_textField = setKeyLabelText(eagle_key_textField,
				input_Handler.getSend_eagle_key_code());

		JLabel lblEagle = new JLabel("Eagle");
		lblEagle.setBounds(416, 212, 48, 17);
		getContentPane().add(lblEagle);

		JLabel lblUp = new JLabel("UP");
		lblUp.setBounds(213, 178, 46, 14);
		getContentPane().add(lblUp);

		JLabel lblLeft = new JLabel("LEFT");
		lblLeft.setBounds(110, 224, 46, 20);
		getContentPane().add(lblLeft);

		JLabel lblDown = new JLabel("DOWN");
		lblDown.setBounds(203, 243, 59, 20);
		getContentPane().add(lblDown);

		JLabel lblRight = new JLabel("RIGHT");
		lblRight.setBounds(300, 224, 71, 20);
		getContentPane().add(lblRight);

		JLabel lblNumberOfDragons = new JLabel("Number of Dragons");
		lblNumberOfDragons.setBounds(10, 50, 146, 26);
		getContentPane().add(lblNumberOfDragons);

	}

	void setupGameModeButtons() {
		JRadioButton modeButton_1 = new JRadioButton("1 - Static Dragon");
		modeButton_1.setBounds(10, 134, 185, 23);
		getContentPane().add(modeButton_1);

		JRadioButton modeButton_3 = new JRadioButton("3 - Sleep / Wake Dragon");
		modeButton_3.setBounds(388, 134, 204, 23);
		getContentPane().add(modeButton_3);

		JRadioButton modeButton_2 = new JRadioButton("2 - Moving Dragon");
		modeButton_2.setBounds(213, 134, 155, 23);
		getContentPane().add(modeButton_2);

		game_mode_btnGroup = new ButtonGroup();
		game_mode_btnGroup.add(modeButton_1);
		game_mode_btnGroup.add(modeButton_2);
		game_mode_btnGroup.add(modeButton_3);
		
		if(input_Handler.getMode()==1){
			modeButton_1.setSelected(true);
			
		}
		else if(input_Handler.getMode()==2){
			modeButton_2.setSelected(true);
		}
		else if(input_Handler.getMode()==3){
			modeButton_3.setSelected(true);
		}

	}

	void setupOKandCancelButtons() {
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				save_settings();
			}
		});
		btnNewButton.setBounds(89, 305, 89, 23);
		getContentPane().add(btnNewButton);
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Settings_Dialog.this.dispose();
			}
		});

		btnNewButton_1.setBounds(435, 305, 89, 23);
		getContentPane().add(btnNewButton_1);

	}

	void setupMazeSizeSliderAndLabel() {
		JSlider Lab_slider = new JSlider();
		Lab_slider.setBounds(200, 11, 200, 26);

		Lab_slider.setValue(input_Handler.getDimention());
		Lab_slider.setMinimum(10);
		getContentPane().add(Lab_slider);
		Lab_slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int slider_val = ((JSlider) arg0.getSource()).getValue();
				slider_value_label.setText("" + slider_val);
			}
		});
		JLabel lblL = new JLabel("Labyrinth Size");
		lblL.setBounds(10, 11, 130, 23);
		getContentPane().add(lblL);

		slider_value_label = new JLabel("" + Lab_slider.getValue());
		slider_value_label.setBounds(286, 34, 46, 14);
		getContentPane().add(slider_value_label);

		JLabel lblDragonType = new JLabel("Dragon Behaviour Type");
		lblDragonType.setBounds(10, 104, 168, 23);
		getContentPane().add(lblDragonType);

		JSlider dragons_slider = new JSlider();
		dragons_slider.setValue(input_Handler.getNumberOfDragons());
		dragons_slider.setMinimum(1);
		dragons_slider.setMaximum(10);
		dragons_slider.setBounds(200, 50, 200, 26);
		getContentPane().add(dragons_slider);

		dragons_slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int dragons_val = ((JSlider) arg0.getSource()).getValue();
				dragonNr_slider_label.setText("" + dragons_val);
			}
		});
		dragonNr_slider_label = new JLabel("" + dragons_slider.getValue());
		dragonNr_slider_label.setBounds(286, 82, 46, 14);
		getContentPane().add(dragonNr_slider_label);

	}

	
	/**
	 * A function to verify that no key code is assigned twice
	 * 
	 * @param keyCode the key code to be tested
	 * @return True if the keyCode is unique. False otherwise.
	 */
	private boolean isValidKey(int keyCode) {
		if (keyCode == key_up_code || keyCode == key_down_code || keyCode == key_left_code
				|| keyCode == key_right_code || keyCode == key_eagle_code) {
			return false;
		}
		return true;
	}
}
