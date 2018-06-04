package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class BLJPanelExample extends JFrame {

	JPanel centerPanel, leftPanel, rightPanel, topPanel, botPanel;

	public BLJPanelExample() {
		super("[=] BorderLayout using GridBagLayout [=]");
		initGUI();
	}

	private void initGUI() {

		BLJPanel mainPanel = new BLJPanel();

		centerPanel = createPanel(Color.red, 50, 50);
		mainPanel.add(centerPanel, BLJPanel.CENTER);

		leftPanel = createPanel(Color.green, 50, 50);
		mainPanel.add(leftPanel, BLJPanel.LEFT);

		rightPanel = createPanel(Color.yellow, 50, 50);
		mainPanel.add(rightPanel, BLJPanel.RIGHT);

		topPanel = createPanel(Color.blue, 20, 20);
		mainPanel.add(topPanel, BLJPanel.TOP);

		botPanel = createPanel(Color.gray, 20, 20);
		mainPanel.add(botPanel, BLJPanel.BOT);

		mainPanel.setOpaque(true);

		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 200);
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
				new BLJPanelExample();
			}
		});
	}
}
