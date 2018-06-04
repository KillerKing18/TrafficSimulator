package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class ComboBoxExample extends JFrame implements ActionListener {

	String names[] = { "LEFT", "CENTER", "RIGHT" };
	JComboBox<String> list;
	private JPanel boxesPanel;

	public ComboBoxExample() {
		super("[=] ComboBox Example [=]");
		initGUI();
	}

	private void initGUI() {

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));

		list = new JComboBox<String>(names);
		list.setSelectedIndex(0);
		list.addActionListener(this);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(list);

		boxesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		boxesPanel.setPreferredSize(new Dimension(200, 120));
		boxesPanel.setBackground(Color.gray);

		JPanel red = createSquareJPanel(Color.red, 50);
		JPanel blue = createSquareJPanel(Color.blue, 50);
		JPanel green = createSquareJPanel(Color.green, 50);
		JPanel orange = createSquareJPanel(Color.orange, 50);
		JPanel yellow = createSquareJPanel(Color.yellow, 50);

		boxesPanel.add(red);
		boxesPanel.add(blue);
		boxesPanel.add(green);
		boxesPanel.add(orange);
		boxesPanel.add(yellow);

		mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		mainPanel.add(buttonPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		mainPanel.add(boxesPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));

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

	public void actionPerformed(ActionEvent e) {

		FlowLayout fl = (FlowLayout) boxesPanel.getLayout();

		@SuppressWarnings("unchecked")
		JComboBox<String> cb = (JComboBox<String>) e.getSource();
		String name = (String) cb.getSelectedItem();
		System.out.println(name);
		if (name.equals(names[0])) {
			fl.setAlignment(FlowLayout.LEFT);
		} else if (name.equals(names[1])) {
			fl.setAlignment(FlowLayout.CENTER);
		} else if (name.equals(names[2])) {
			fl.setAlignment(FlowLayout.RIGHT);
		}

		boxesPanel.revalidate();

		// try to uncomment and see the effect
		// cb.removeItem(names[0]);
		// cb.addItem("bla bla");
		// cb.addItem(new Object()); // must change ComboBox<String> to
		// ComboBox<Object>
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ComboBoxExample();
			}
		});
	}
}
