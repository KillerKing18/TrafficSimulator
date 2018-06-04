package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class BoxLayoutExample_2 extends JFrame {

	public BoxLayoutExample_2() {
		super("[=] BoxLayout [=]");
		initGUI();
	}

	public void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

		mainPanel.add(createPanel(Color.red, 50, 50, true));
		mainPanel.add(createPanel(Color.yellow, 30, 50, false));
		mainPanel.add(createPanel(Color.blue, 50, 10, true));
		mainPanel.add(createPanel(Color.green, 50, 20, false));
		mainPanel.add(createPanel(Color.magenta, 50, 50, true));

		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(280, 190);
		this.pack();
		this.setVisible(true);

	}

	private JPanel createPanel(Color color, int x, int y, boolean fixed) {
		JPanel panel;

		panel = new JPanel();
		panel.setBackground(color);
		panel.setAlignmentY(Component.TOP_ALIGNMENT);

		// check the default maximum and minimum size:
		//
		System.out.println(panel.getMaximumSize() + " "
				+ panel.getMinimumSize());

		panel.setPreferredSize(new Dimension(x, y));
		if (fixed) {
			panel.setMaximumSize(new Dimension(x, y));
			panel.setMinimumSize(new Dimension(x, y));
		}
		return panel;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new BoxLayoutExample_2();
			}
		});
	}
}
