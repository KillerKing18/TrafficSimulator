package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class ToggleButtonExample_1 extends JFrame implements ItemListener, ActionListener {

	// Definition of global values and items that are part of the GUI.

	JToggleButton toggleButton;
	JPanel mainPanel;
	private JButton normalButton;

	boolean tmp;

	public ToggleButtonExample_1() {
		super("[=] JToggleButton [=]");
		initGUI();
	}

	private void initGUI() {

		// We create a bottom JPanel to place everything on.
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.red);
		mainPanel.setLayout(null);
		mainPanel.setOpaque(true);

		toggleButton = new JToggleButton("Off");
		toggleButton.setLocation(75, 10);
		toggleButton.setSize(100, 100);
		toggleButton.addItemListener(this);
		toggleButton.addActionListener(this);
		mainPanel.add(toggleButton);

		normalButton = new JButton("Push me!");
		normalButton.setLocation(75, 120);
		normalButton.setSize(100, 30);
		normalButton.addActionListener(this);
		mainPanel.add(normalButton);

		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(250, 200);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("actionPerformed is called from " + e.getSource());

		if (e.getSource() == normalButton) {
			toggleButton.setSelected(!toggleButton.isSelected());
		}
	}

	// This is the new itemStateChanged Method.
	// It catches any events with an ItemListener attached.
	// Using an if statement, we can determine if the button is now selected or
	// deselected
	// after the action and perform changes to the GUI accordingly.

	public void itemStateChanged(ItemEvent e) {
		System.out.println("itemStateChanged is called from " + e.getSource());

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
				new ToggleButtonExample_1();
			}
		});
	}

}
