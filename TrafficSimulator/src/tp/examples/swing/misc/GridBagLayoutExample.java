package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.*;

@SuppressWarnings("serial")
public class GridBagLayoutExample extends JFrame {

	public GridBagLayoutExample() {
		super("[=] GridBag Layout [=]");
		initGUI();
	}

	private void initGUI() {

		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JPanel red = createSquareJPanel(Color.red, 100);
		c.gridx = 0;
		c.gridy = 0;
		mainPanel.add(red, c);

		JPanel blue = createSquareJPanel(Color.blue, 70);
		c.gridx = 1;
		c.gridy = 1;
		mainPanel.add(blue, c);

		JPanel yellow = createSquareJPanel(Color.yellow, 40);
		c.gridx = 2;
		c.gridy = 2;
		mainPanel.add(yellow, c);

		JPanel green = createSquareJPanel(Color.green, 30);
		c.gridx = 1;
		c.gridy = 2;
		mainPanel.add(green, c);

		JPanel magenta = createSquareJPanel(Color.magenta, 50);
		c.gridx = 0;
		c.gridy = 1;
		mainPanel.add(magenta, c);

		mainPanel.setOpaque(true);

		this.setContentPane(mainPanel);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);

	}

	// In this method, we create a square JPanel of a colour and set size
	// specified by the arguments.

	private JPanel createSquareJPanel(Color color, int size) {
		JPanel tempPanel = new JPanel();
		tempPanel.setBackground(color);
		tempPanel.setMinimumSize(new Dimension(size, size));
		tempPanel.setMaximumSize(new Dimension(size, size));
		tempPanel.setPreferredSize(new Dimension(size, size));
		return tempPanel;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GridBagLayoutExample();
			}
		});
	}
}