package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.*;

@SuppressWarnings("serial")
public class GridBagLayoutExample_8 extends JFrame {

	JLabel l1, l2, l3, l4;
	JTextField f1, f2, f3, f4;

	public GridBagLayoutExample_8() {
		super("[=] GridBag Layout [=]");
		initGUI();
	}

	private void initGUI() {

		JPanel mainPanel = new JPanel(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.LINE_START;
		l1 = new JLabel("Name");
		c.gridx = 0;
		c.gridy = 0;
		mainPanel.add(l1, c);

		l2 = new JLabel("Surname");
		c.gridx = 0;
		c.gridy = 1;
		mainPanel.add(l2, c);

		l3 = new JLabel("Address");
		c.gridx = 0;
		c.gridy = 2;
		mainPanel.add(l3, c);

		l4 = new JLabel("Country");
		c.gridx = 0;
		c.gridy = 3;
		mainPanel.add(l4, c);

		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		f1 = new JTextField(10);
		c.gridx = 1;
		c.gridy = 0;
		mainPanel.add(f1, c);

		f2 = new JTextField(10);
		c.gridx = 1;
		c.gridy = 1;
		mainPanel.add(f2, c);

		f3 = new JTextField(10);
		c.gridx = 1;
		c.gridy = 2;
		mainPanel.add(f3, c);

		f4 = new JTextField(10);
		c.gridx = 1;
		c.gridy = 3;
		mainPanel.add(f4, c);

		mainPanel.setOpaque(true);

		this.setContentPane(mainPanel);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GridBagLayoutExample_8();
			}
		});
	}
}