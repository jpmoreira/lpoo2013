package gui;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Settings_Dialog extends JDialog {
	private JTextField up_key_textField;
	private JTextField down_key_textField;
	private JTextField left_key_textField;
	private JTextField right_key_textField;
	private JTextField eagle_key_textField;
	private JLabel Slider_value;
	private ButtonGroup game_mode_btg;
	private int key_up, key_left, key_right, key_down,temp;

	public Settings_Dialog(JFrame frame) {
		super(frame, "Settings", true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.setBounds(89, 256, 89, 23);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBounds(432, 256, 89, 23);
		getContentPane().add(btnNewButton_1);

		JSlider Lab_slider = new JSlider();
		Lab_slider.setBounds(200, 11, 200, 26);

		Lab_slider.setValue(10);
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
		lblL.setBounds(38, 11, 102, 23);
		getContentPane().add(lblL);

		Slider_value = new JLabel("" + Lab_slider.getValue());
		Slider_value.setBounds(286, 34, 46, 14);
		getContentPane().add(Slider_value);

		JLabel lblDragonType = new JLabel("Dragon Behaviour Type");
		lblDragonType.setBounds(10, 56, 168, 23);
		getContentPane().add(lblDragonType);

		JRadioButton rdbtnDragon1 = new JRadioButton("1 - Static Dragon");
		rdbtnDragon1.setBounds(10, 86, 185, 23);
		rdbtnDragon1.setSelected(true);
		getContentPane().add(rdbtnDragon1);

		JRadioButton rdbtnDragon3 = new JRadioButton("3 - Sleep / Wake Dragon");
		rdbtnDragon3.setBounds(377, 86, 210, 23);
		getContentPane().add(rdbtnDragon3);

		JRadioButton rdbtnDragon2 = new JRadioButton("2 - Moving Dragon");
		rdbtnDragon2.setBounds(200, 86, 155, 23);
		getContentPane().add(rdbtnDragon2);

		game_mode_btg = new ButtonGroup();
		game_mode_btg.add(rdbtnDragon1);
		game_mode_btg.add(rdbtnDragon2);
		game_mode_btg.add(rdbtnDragon3);

		JLabel lblKeyboardKeys = new JLabel("Keyboard keys");
		lblKeyboardKeys.setBounds(10, 120, 130, 14);
		getContentPane().add(lblKeyboardKeys);

		up_key_textField = new JTextField();
		addlistener(up_key_textField);
		up_key_textField.setBounds(196, 145, 40, 20);
		getContentPane().add(up_key_textField);
		up_key_textField.setColumns(10);

		down_key_textField = new JTextField();
		addlistener(down_key_textField);
		down_key_textField.setBounds(196, 176, 40, 20);
		getContentPane().add(down_key_textField);
		down_key_textField.setColumns(10);

		left_key_textField = new JTextField();
		addlistener(left_key_textField);
		left_key_textField.setBounds(146, 176, 40, 20);
		getContentPane().add(left_key_textField);
		left_key_textField.setColumns(10);

		right_key_textField = new JTextField();
		addlistener(right_key_textField);
		right_key_textField.setBounds(246, 176, 40, 20);
		getContentPane().add(right_key_textField);
		right_key_textField.setColumns(10);

		eagle_key_textField = new JTextField();
		addlistener(eagle_key_textField);
		eagle_key_textField.setBounds(484, 145, 40, 20);
		getContentPane().add(eagle_key_textField);
		eagle_key_textField.setColumns(10);

		JLabel lblEagle = new JLabel("Eagle");
		lblEagle.setBounds(421, 146, 71, 17);
		getContentPane().add(lblEagle);

		JLabel lblUp = new JLabel("UP");
		lblUp.setBounds(209, 131, 46, 14);
		getContentPane().add(lblUp);

		JLabel lblLeft = new JLabel("LEFT");
		lblLeft.setBounds(110, 176, 40, 20);
		getContentPane().add(lblLeft);

		JLabel lblDown = new JLabel("DOWN");
		lblDown.setBounds(196, 197, 59, 20);
		getContentPane().add(lblDown);

		JLabel lblRight = new JLabel("RIGHT");
		lblRight.setBounds(295, 176, 71, 20);
		getContentPane().add(lblRight);
		setBounds(frame.getX(), frame.getY(), 614, 335);

	}

	private void save_settings() {
		for (Enumeration<AbstractButton> buttons = game_mode_btg.getElements(); buttons
				.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();
			if (button.isSelected()) {
				char selected = button.getText().charAt(0);
				int mode = (Character.getNumericValue(selected)) - 1;
			}
		}
	}
	
	private void addlistener(JTextField textfield){
	
		textfield.addKeyListener(new KeyAdapter() {
		
			@Override
			public void keyTyped(KeyEvent e) {
				temp = e.getKeyCode();
				((JTextField)e.getSource()).setText(String.valueOf((char) key_up));

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					((JTextField)e.getSource()).setText("UP");
					temp = e.getKeyCode();
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					((JTextField)e.getSource()).setText("DOWN");
					temp = e.getKeyCode();
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					((JTextField)e.getSource()).setText("LEFT");
					temp = e.getKeyCode();
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					((JTextField)e.getSource()).setText("RIGHT");
					temp = e.getKeyCode();
				}
			}

		});
		if (textfield == up_key_textField) key_up = temp;
		else if (textfield == down_key_textField) key_down = temp;
		else if (textfield == left_key_textField) key_left = temp;
		else if (textfield == right_key_textField) key_right = temp;
	}
	
	
	void setupKeyLabels(){
		
	}
	
}
