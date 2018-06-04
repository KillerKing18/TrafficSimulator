package tp.examples.swing.misc;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

// Class implements three different types of listener.
@SuppressWarnings("serial")
public class SeparatorExample extends JFrame implements ActionListener,
		ItemListener {

	JTextArea storyArea;
	JRadioButton boyButton, girlButton;
	JComboBox<String> nameBox;
	JCheckBox cb[] = new JCheckBox[7];

	String objects[] = { "Car", "Bike", "Dragon", "Bus", "Mouse", "Cheese",
			"Unicycle", "Sinclair C5", "Pony" };

	String places[] = { "Office", "Hospital", "University", "Pool", "Farm",
			"Castle", "Sewer" };

	public SeparatorExample() {
		super("[=] SeparatorExample [=]");
		initGUI();
	}

	private void initGUI() {

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

		// The main story for the JTextArea
		String story = "When I was a small boy, I always dreamed of having a Red Car.\n"
				+ "I'd travel in it everywhere and go and visit my mum.\n"
				+ "Sometimes I'd go and visit the Office.\n"
				+ "I loved my Red Car and it loved me.\n";

		// Plus we instantiate the TextArea.
		storyArea = new JTextArea(story, 5, 15);
		storyArea.setEditable(false);
		storyArea.setLineWrap(true);
		storyArea.setWrapStyleWord(true);

		// To determine the sex of the user.
		boyButton = new JRadioButton("Boy");
		boyButton.addActionListener(this);
		boyButton.setSelected(true);
		boyButton.setHorizontalAlignment(0);
		girlButton = new JRadioButton("Girl");
		girlButton.addActionListener(this);
		boyButton.setHorizontalAlignment(0);

		ButtonGroup sexGroup = new ButtonGroup();
		sexGroup.add(boyButton);
		sexGroup.add(girlButton);

		JPanel sexPanel = new JPanel();
		sexPanel.setLayout(new BoxLayout(sexPanel, BoxLayout.PAGE_AXIS));
		sexPanel.add(boyButton);
		sexPanel.add(girlButton);
		sexPanel.add(Box.createRigidArea(new Dimension(0, 30)));

		// To determine the vehicle.
		nameBox = new JComboBox<String>(objects);
		nameBox.setSelectedIndex(0);
		nameBox.addActionListener(this);
		sexPanel.add(nameBox);

		// To determine the places they have been to.
		JPanel cbPanel = new JPanel();
		cbPanel.setLayout(new BoxLayout(cbPanel, BoxLayout.PAGE_AXIS));

		cbPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		for (int i = 0; i < 7; i++) {
			cb[i] = new JCheckBox(places[i]);
			cb[i].addItemListener(this);
			cbPanel.add(cb[i]);
			cbPanel.add(Box.createHorizontalGlue());
		}
		cb[0].setSelected(true);
		cbPanel.add(Box.createRigidArea(new Dimension(10, 0)));

		// This final bit of code uses a BoxLayout to space out the widgets
		// in the GUI.

		mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		mainPanel.add(storyArea);
		mainPanel.add(new JSeparator(SwingConstants.VERTICAL));
		mainPanel.add(sexPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		mainPanel.add(new JSeparator(SwingConstants.VERTICAL));
		mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		mainPanel.add(cbPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));

		mainPanel.setOpaque(true);

		this.setContentPane(mainPanel);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	// For the List Events.
	public void valueChanged(ListSelectionEvent e) {
		sortTextPanel();
	}

	// For the Action Events.
	public void actionPerformed(ActionEvent e) {
		sortTextPanel();
	}

	// For the Item Events.
	public void itemStateChanged(ItemEvent e) {
		sortTextPanel();
	}

	// This rewrites the story depending on the actions so far in the various
	// widgets then re-sends the story to the TextArea.
	public void sortTextPanel() {
		String story = "When I was a small";

		if (boyButton.isSelected()) {
			story = story + " boy, I always dreamed of having a";
		} else {
			story = story + " girl, I always dreamed of having a";
		}

		story = story + " " + objects[nameBox.getSelectedIndex()] + " ";
		story = story
				+ ".\nI'd travel in it everywhere and go and visit my mum.\n";
		story = story + "Sometimes I'd go and visit the ";

		for (int i = 0; i < 7; i++) {
			if (cb[i].isSelected())
				story = story + places[i] + " ";
		}
		story = story + "\nI loved my " + objects[nameBox.getSelectedIndex()]
				+ " and it loved me.\n";

		storyArea.setText(story);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SeparatorExample();
			}
		});
	}
}