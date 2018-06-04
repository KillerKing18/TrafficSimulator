package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class BorderLayoutExample_1 extends JFrame {

	JPanel centerPanel, leftPanel, rightPanel, topPanel, botPanel;

	public BorderLayoutExample_1(String title, int opt) {
		super(title);
		initGUI(opt);
	}

	private void initGUI(int opt) {
		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));

		switch (opt) {
		case 1:
			centerPanel = createPanel(Color.red, 50, 50);
			mainPanel.add(centerPanel, BorderLayout.CENTER);
			centerPanel.add(createButton("Hola!", 50, 20));
			break;
		case 2:
			centerPanel = createPanel(Color.red, 50, 50);
			mainPanel.add(centerPanel, BorderLayout.CENTER);
			centerPanel.setLayout(new BorderLayout());
			centerPanel.add(createButton("Hola!", 50, 20));
			break;
		default:
			mainPanel.add(createButton("Hola!", 50, 20));
			break;
		}

		leftPanel = createPanel(Color.green, 50, 50);
		mainPanel.add(leftPanel, BorderLayout.LINE_START);

		rightPanel = createPanel(Color.yellow, 50, 50);
		mainPanel.add(rightPanel, BorderLayout.LINE_END);

		topPanel = createPanel(Color.blue, 20, 20);
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

	private JButton createButton(String t, int x, int y) {
		JButton b = new JButton(t);
		b.setPreferredSize(new Dimension(x, y));
		return b;
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				for (int i = 0; i < 3; i++)
					new BorderLayoutExample_1("[=] BorderLayout " + i + "[=]",
							i);
			}
		});
	}
}