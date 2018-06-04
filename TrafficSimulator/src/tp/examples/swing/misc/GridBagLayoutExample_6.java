package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.*;

@SuppressWarnings("serial")
public class GridBagLayoutExample_6 extends JFrame {

	public GridBagLayoutExample_6() {
		super("[=] GridBag Layout [=]");
		initGUI();
	}

	private void initGUI() {

		JPanel mainPanel = new JPanel(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		JButton[] buttons = new JButton[10];
		for (int i = 0; i < 5; i++)
			buttons[i] = new JButton("Button " + i);

		c.gridx = 0;
		c.gridy = 0;
		mainPanel.add(buttons[0], c);

		c.gridx = 1;
		c.gridy = 0;
		mainPanel.add(buttons[1], c);

		c.gridx = 2;
		c.gridy = 0;
		mainPanel.add(buttons[2], c);

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		c.gridheight = 1;
		mainPanel.add(buttons[3], c);

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.gridheight = 1;
		mainPanel.add(buttons[4], c);

		mainPanel.setOpaque(true);

		this.setContentPane(mainPanel);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GridBagLayoutExample_6();
			}
		});
	}
}