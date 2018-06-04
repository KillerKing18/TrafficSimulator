package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class BorderLayoutExample extends JFrame {

	JPanel centerPanel, leftPanel, rightPanel, topPanel, botPanel;

	public BorderLayoutExample() {
		super("[=] BorderLayout [=]");
		initGUI();
	}

	private void initGUI() {

		// We create a bottom JPanel to place everything on.
		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));

		centerPanel = createPanel(Color.red, 50, 50);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		
		leftPanel = createPanel(Color.green, 50, 50);
		mainPanel.add(leftPanel, BorderLayout.LINE_START);

		rightPanel = createPanel(Color.yellow, 50, 50);
		mainPanel.add(rightPanel, BorderLayout.LINE_END);

		topPanel = createPanel(Color.blue, 40, 40);
		mainPanel.add(topPanel, BorderLayout.PAGE_START);

		botPanel = createPanel(Color.gray, 20, 20);
		mainPanel.add(botPanel, BorderLayout.PAGE_END);

		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(280, 190);
		this.setVisible(true);
		
		

	}

	private JPanel createPanel(Color color, int x, int y) {

		JPanel panel;
		panel = new JPanel();
		panel.setBackground(color);
		panel.setPreferredSize(new Dimension(x, y));
		return panel;
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new BorderLayoutExample();
			}
		});
	}
}
