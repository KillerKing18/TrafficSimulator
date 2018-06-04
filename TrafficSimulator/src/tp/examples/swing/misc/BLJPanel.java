package tp.examples.swing.misc;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BLJPanel extends JPanel {

	final static int LEFT = 0;
	final static int CENTER = 1;
	final static int RIGHT = 2;
	final static int TOP = 3;
	final static int BOT = 4;

	GridBagLayout lm;

	public BLJPanel() {
		lm = new GridBagLayout();
		this.setLayout(lm);
	}

	public void add(JComponent x, int pos) {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		switch (pos) {
		case LEFT:
			c.gridx = 0;
			c.gridy = 1;
			break;
		case CENTER:
			c.gridx = 1;
			c.gridy = 1;
			c.weightx = 1;
			c.weighty = 1;
			break;
		case RIGHT:
			c.gridx = 2;
			c.gridy = 1;
			break;
		case TOP:
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 3;
			break;
		case BOT:
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 3;
			break;
		}

		this.add(x, c);

	}
}
