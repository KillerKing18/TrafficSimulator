package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.*;

@SuppressWarnings("serial")
public class GridBagLayoutExample_4 extends JFrame {

	public GridBagLayoutExample_4() {
		super("[=] GridBag Layout [=]");
		initGUI();
	}

	private void initGUI() {

		JPanel mainPanel = new JPanel(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		JPanel red = createSquareJPanel(Color.red, 100);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.2;
		c.weighty = 0.1;
		c.gridwidth = 2;
		c.gridheight = 1;
		mainPanel.add(red, c);

		JPanel blue = createSquareJPanel(Color.blue, 70);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.4;
		c.weighty = 0.3;
		mainPanel.add(blue, c);

		c = new GridBagConstraints(); // reset c

		JPanel yellow = createSquareJPanel(Color.yellow, 40);
		c.gridx = 2;
		c.gridy = 2;
		mainPanel.add(yellow, c);

		JPanel green = createSquareJPanel(Color.green, 30);
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		mainPanel.add(green, c);

		JPanel magenta = createSquareJPanel(Color.magenta, 50);
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.anchor = GridBagConstraints.CENTER;
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
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GridBagLayoutExample_4();
			}
		});
	}
}