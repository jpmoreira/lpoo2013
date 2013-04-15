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
	private JLabel Slider_value;
	private JLabel Dragon_slider;
	private ButtonGroup game_mode_btg;
	private GUI_InputHandler setHandler;
	private int key_up, key_left, key_right, key_down, key_eagle, temp;

	public Settings_Dialog(JFrame frame, GUI_InputHandler handler) {

		super(frame, "Settings", true);
		setHandler = handler;
		init_keys();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		setupOKandCancelButtons();

		setupMazeSizeSliderAndLabel();

		setupGameModeButtons();

		setupKeyLabels();

		setBounds(frame.getX(), frame.getY(), 614, 387);

	}

	private void init_keys() {
		key_up = setHandler.getMove_up();
		key_down = setHandler.getMove_down();
		key_left = setHandler.getMove_left();
		key_right = setHandler.getMove_right();
		key_eagle = setHandler.getSend_eagle();
	}

	private void save_settings() {
		int mode = 0;
		for (Enumeration<AbstractButton> buttons = game_mode_btg.getElements(); buttons
				.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();
			if (button.isSelected()) {
				char selected = button.getText().charAt(0);
				mode = (Character.getNumericValue(selected)) - 1;
			}
		}

		setHandler.setPrefs(mode, Integer.parseInt(Slider_value.getText()),
				Integer.parseInt(Dragon_slider.getText()));
		setHandler
				.setkeyPrefs(key_up, key_down, key_right, key_left, key_eagle);
		this.dispose();
	}

	private JTextField setCodeText(JTextField textfield, int theKcode) {
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
						temp = e.getKeyCode();
					}

					else if (theKcode == KeyEvent.VK_DOWN) {
						((JTextField) e.getSource()).setText("DOWN");
						temp = e.getKeyCode();
					}

					else if (theKcode == KeyEvent.VK_LEFT) {
						((JTextField) e.getSource()).setText("LEFT");
						temp = e.getKeyCode();
					}

					else if (theKcode == KeyEvent.VK_RIGHT) {
						((JTextField) e.getSource()).setText("RIGHT");
						temp = theKcode;
					}
					if (e.getSource() == up_key_textField)
						key_up = temp;
					else if (e.getSource() == down_key_textField)
						key_down = temp;
					else if (e.getSource() == left_key_textField)
						key_left = temp;
					else if (e.getSource() == right_key_textField)
						key_right = temp;
					else if (e.getSource() == eagle_key_textField)
						key_eagle = temp;
				}

				else {// if not a arrow key
					if (isValidKey(theKcode)) {

						String newText = String.valueOf((char) theKcode);
						((JTextField) e.getSource()).setText(newText);
						temp = theKcode;
					} else {
						return;
					}

					if (e.getSource() == up_key_textField)
						key_up = temp;
					else if (e.getSource() == down_key_textField)
						key_down = temp;
					else if (e.getSource() == left_key_textField)
						key_left = temp;
					else if (e.getSource() == right_key_textField)
						key_right = temp;
					else if (e.getSource() == eagle_key_textField)
						key_eagle = temp;

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
		up_key_textField = setCodeText(up_key_textField,
				setHandler.getMove_up());

		down_key_textField = new JTextField();
		addlistener(down_key_textField);
		down_key_textField.setBounds(200, 224, 40, 20);
		getContentPane().add(down_key_textField);
		down_key_textField.setHorizontalAlignment(JTextField.CENTER);
		down_key_textField.setColumns(10);
		down_key_textField = setCodeText(down_key_textField,
				setHandler.getMove_down());

		left_key_textField = new JTextField();
		addlistener(left_key_textField);
		left_key_textField.setBounds(155, 224, 40, 20);
		getContentPane().add(left_key_textField);
		left_key_textField.setHorizontalAlignment(JTextField.CENTER);
		left_key_textField.setColumns(10);
		left_key_textField = setCodeText(left_key_textField,
				setHandler.getMove_left());

		right_key_textField = new JTextField();
		addlistener(right_key_textField);
		right_key_textField.setBounds(245, 224, 40, 20);
		getContentPane().add(right_key_textField);
		right_key_textField.setHorizontalAlignment(JTextField.CENTER);
		right_key_textField.setColumns(10);
		right_key_textField = setCodeText(right_key_textField,
				setHandler.getMove_right());

		eagle_key_textField = new JTextField();
		addlistener(eagle_key_textField);
		eagle_key_textField.setBounds(468, 210, 40, 20);
		getContentPane().add(eagle_key_textField);
		eagle_key_textField.setHorizontalAlignment(JTextField.CENTER);
		eagle_key_textField.setColumns(10);
		eagle_key_textField = setCodeText(eagle_key_textField,
				setHandler.getSend_eagle());

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
		JRadioButton rdbtnDragon1 = new JRadioButton("1 - Static Dragon");
		rdbtnDragon1.setBounds(10, 134, 185, 23);
		rdbtnDragon1.setSelected(true);
		getContentPane().add(rdbtnDragon1);

		JRadioButton rdbtnDragon3 = new JRadioButton("3 - Sleep / Wake Dragon");
		rdbtnDragon3.setBounds(388, 134, 204, 23);
		getContentPane().add(rdbtnDragon3);

		JRadioButton rdbtnDragon2 = new JRadioButton("2 - Moving Dragon");
		rdbtnDragon2.setBounds(213, 134, 155, 23);
		getContentPane().add(rdbtnDragon2);

		game_mode_btg = new ButtonGroup();
		game_mode_btg.add(rdbtnDragon1);
		game_mode_btg.add(rdbtnDragon2);
		game_mode_btg.add(rdbtnDragon3);

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

		Lab_slider.setValue(setHandler.getDimention());
		Lab_slider.setMinimum(10);
		getContentPane().add(Lab_slider);
		Lab_slider.addChangeListener(new ChangeListener() {
			/** TODO: complete slider change event */
			public void stateChanged(ChangeEvent arg0) {
				int slider_val = ((JSlider) arg0.getSource()).getValue();
				Slider_value.setText("" + slider_val);
			}
		});
		JLabel lblL = new JLabel("Labyrinth Size");
		lblL.setBounds(10, 11, 130, 23);
		getContentPane().add(lblL);

		Slider_value = new JLabel("" + Lab_slider.getValue());
		Slider_value.setBounds(286, 34, 46, 14);
		getContentPane().add(Slider_value);

		JLabel lblDragonType = new JLabel("Dragon Behaviour Type");
		lblDragonType.setBounds(10, 104, 168, 23);
		getContentPane().add(lblDragonType);

		JSlider dragons_slider = new JSlider();
		dragons_slider.setValue(setHandler.getNumberOfDragons());
		dragons_slider.setMinimum(1);
		dragons_slider.setMaximum(10);
		dragons_slider.setBounds(200, 50, 200, 26);
		getContentPane().add(dragons_slider);

		dragons_slider.addChangeListener(new ChangeListener() {
			/** TODO: complete slider change event */
			public void stateChanged(ChangeEvent arg0) {
				int dragons_val = ((JSlider) arg0.getSource()).getValue();
				Dragon_slider.setText("" + dragons_val);
			}
		});
		Dragon_slider = new JLabel("" + dragons_slider.getValue());
		Dragon_slider.setBounds(286, 82, 46, 14);
		getContentPane().add(Dragon_slider);

	}

	private boolean isValidKey(int keyCode) {
		if (keyCode == key_up || keyCode == key_down || keyCode == key_left
				|| keyCode == key_right || keyCode == key_eagle) {
			return false;
		}
		return true;
	}
}
