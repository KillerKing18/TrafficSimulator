package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class ToggleButtonExample extends JFrame implements ItemListener {

	// Definition of global values and items that are part of the GUI.

	JToggleButton toggleButton;
	JPanel mainPanel;

	public ToggleButtonExample() {
		super("[=] JToggleButton [=]");
		initGUI();
	}

	private void initGUI() {

		// We create a bottom JPanel to place everything on.
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.red);
		mainPanel.setLayout(null);

		toggleButton = new JToggleButton("Off");
		toggleButton.setLocation(75, 10);
		toggleButton.setSize(100, 100);
		toggleButton.addItemListener(this);
		mainPanel.add(toggleButton);

		this.setContentPane(mainPanel);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(250, 150);
		this.setVisible(true);

	}

	// This is the new itemStateChanged Method.
	// It catches any events with an ItemListener attached.
	// Using an if statement, we can determine if the button is now selected or
	// deselected
	// after the action and perform changes to the GUI accordingly.

	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			toggleButton.setText("On!");
			mainPanel.setBackground(Color.green);
		} else {
			toggleButton.setText("Off");
			mainPanel.setBackground(Color.red);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ToggleButtonExample();
			}
		});
	}
}
