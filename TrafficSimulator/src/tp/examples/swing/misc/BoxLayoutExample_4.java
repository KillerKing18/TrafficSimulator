package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class BoxLayoutExample_4 extends JFrame {

	public BoxLayoutExample_4() {
		super("[=] BoxLayout [=]");
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

		mainPanel.add(createPanel(Color.red, 50, 50));
		mainPanel.add(Box.createHorizontalGlue());
		mainPanel.add(createPanel(Color.yellow, 30, 50));
		mainPanel.add(createPanel(Color.blue, 50, 10));
		mainPanel.add(Box.createHorizontalGlue());
		mainPanel.add(createPanel(Color.green, 50, 20));
		mainPanel.add(createPanel(Color.magenta, 50, 50));

		mainPanel.setOpaque(true);

		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(280, 190);
		this.pack();
		this.setVisible(true);
	}

	private JPanel createPanel(Color color, int x, int y) {
		JPanel panel;
		panel = new JPanel();
		panel.setBackground(color);
		panel.setAlignmentY(Component.TOP_ALIGNMENT);
		panel.setPreferredSize(new Dimension(x, y));
		panel.setMaximumSize(new Dimension(x, y));
		panel.setMinimumSize(new Dimension(x, y));
		return panel;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new BoxLayoutExample_4();
			}
		});
	}
}
