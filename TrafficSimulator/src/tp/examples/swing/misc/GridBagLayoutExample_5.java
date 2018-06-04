package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class GridBagLayoutExample_5 extends JFrame implements ActionListener {

	JButton[] numButtons;
	JButton plus, minus, mul, div, eq, dot, clear;
	JTextField numText;

	boolean hasDot;

	public GridBagLayoutExample_5() {
		super("[=] GridBag Layout [=]");
		initGUI();
	}

	private void initGUI() {

		JPanel mainPanel = new JPanel(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		numButtons = new JButton[10];

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;

		int num = 1;
		for (int i = 0; i < 3; i++)
			for (int j = 1; j < 4; j++) {
				numButtons[num] = new JButton("" + num);
				c.gridx = i;
				c.gridy = j;
				mainPanel.add(numButtons[num], c);
				numButtons[num].addActionListener(this);
				num++;
			}

		numButtons[0] = new JButton("0");
		c.gridx = 1;
		c.gridy = 4;
		numButtons[0].addActionListener(this);
		mainPanel.add(numButtons[0], c);

		plus = new JButton("+");
		c.gridx = 3;
		c.gridy = 1;
		mainPanel.add(plus, c);

		plus = new JButton("-");
		c.gridx = 3;
		c.gridy = 2;
		mainPanel.add(plus, c);

		plus = new JButton("*");
		c.gridx = 3;
		c.gridy = 3;
		mainPanel.add(plus, c);

		plus = new JButton("/");
		c.gridx = 3;
		c.gridy = 4;
		mainPanel.add(plus, c);

		eq = new JButton("=");
		c.gridx = 2;
		c.gridy = 4;
		mainPanel.add(eq, c);

		dot = new JButton(".");
		c.gridx = 0;
		c.gridy = 4;
		dot.addActionListener(this);
		mainPanel.add(dot, c);

		clear = new JButton("C");
		c.gridx = 3;
		c.gridy = 0;
		clear.addActionListener(this);
		mainPanel.add(clear, c);

		numText = new JTextField();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.gridheight = 1;
		mainPanel.add(numText, c);
		numText.setEditable(false);

		mainPanel.setOpaque(true);

		this.setContentPane(mainPanel);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == clear) {
			numText.setText("");
			hasDot = false;
		} else if (e.getSource() == dot) {
			if (!hasDot) {
				numText.setText(numText.getText() + ".");
				hasDot = true;
			}
		} else {
			for (int i = 0; i < 10; i++) {
				if (e.getSource() == numButtons[i]) {
					numText.setText(numText.getText() + i);
				}
			}
		}

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GridBagLayoutExample_5();
			}
		});
	}

}