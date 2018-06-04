package tp.examples.swing.misc;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class BorderExample extends JFrame {

	public BorderExample() {
		super("[=] BorderExample [=]");
		initGUI();
	}

	private void initGUI() {

		// We create a bottom JPanel to place everything on.
		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));

		Border b = BorderFactory.createLineBorder(Color.black, 2);

		JPanel centerPanel = createPanel(Color.red, 50, 50);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setBorder(BorderFactory.createTitledBorder(b, "title",
				TitledBorder.RIGHT, TitledBorder.TOP));

		JPanel leftPanel = createPanel(Color.green, 50, 50);
		mainPanel.add(leftPanel, BorderLayout.LINE_START);
		leftPanel.setBorder(BorderFactory.createTitledBorder(b, "title"));

		JPanel rightPanel = createPanel(Color.yellow, 50, 50);
		mainPanel.add(rightPanel, BorderLayout.LINE_END);
		rightPanel.setBorder(BorderFactory.createTitledBorder(b, "title",
				TitledBorder.LEFT, TitledBorder.BOTTOM));

		JPanel topPanel = createPanel(Color.blue, 20, 20);
		mainPanel.add(topPanel, BorderLayout.PAGE_START);
		topPanel.setBorder(b);

		JPanel botPanel = createPanel(Color.gray, 20, 20);
		mainPanel.add(botPanel, BorderLayout.PAGE_END);

		mainPanel.setOpaque(true);

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
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new BorderExample();
			}
		});
	}
}
