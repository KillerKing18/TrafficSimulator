package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.*;

@SuppressWarnings("serial")
public class GridBagLayoutExample_3 extends JFrame {

	public GridBagLayoutExample_3() {
		super("[=] GridBag Layout [=]");
		initGUI();
	}

	private void initGUI() {

		JPanel mainPanel = new JPanel(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		JPanel red = createSquareJPanel(Color.red, 100);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.weightx = 0.1;
		c.weighty = 0.4;
		c.gridy = 0;
		mainPanel.add(red, c);

		JPanel blue = createSquareJPanel(Color.blue, 70);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.3;
		c.weighty = 0.2;
		mainPanel.add(blue, c);

		c = new GridBagConstraints(); // reset c

		JPanel yellow = createSquareJPanel(Color.yellow, 40);
		c.gridx = 2;
		c.gridy = 2;
		mainPanel.add(yellow, c);

		JPanel green = createSquareJPanel(Color.green, 30);
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.NONE;
		mainPanel.add(green, c);

		JPanel magenta = createSquareJPanel(Color.magenta, 50);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LAST_LINE_END;
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
				new GridBagLayoutExample_3();
			}
		});
	}
}