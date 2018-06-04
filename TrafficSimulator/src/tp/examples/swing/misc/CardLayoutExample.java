package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class CardLayoutExample extends JFrame implements ActionListener {

	JPanel buttonsPanel, cardsPanel;
	JPanel contentPanel1, contentPanel2, contentPanel3;
	JButton nextButton, prevButton, lastButton, firstButton;

	public CardLayoutExample() {
		super("[=] CardLayout [=]");
		initGUI();
	}

	private void initGUI() {

		// We create a bottom JPanel to place everything on.
		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));

		buttonsPanel = createButtons();
		mainPanel.add(buttonsPanel, BorderLayout.PAGE_START);

		cardsPanel = new JPanel(new CardLayout());
		mainPanel.add(cardsPanel);

		cardsPanel.add(createPanel(Color.red, 100, 100), "Card 1");
		cardsPanel.add(createPanel(Color.green, 100, 100), "Card 3");
		cardsPanel.add(someContent2(), "Card 3");
		cardsPanel.add(someContent1(), "Card 4");

		mainPanel.setOpaque(true);

		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(280, 190);
		this.pack();
		this.setVisible(true);
	}

	public JPanel someContent1() {

		// We create a bottom JPanel to place everything on.
		JPanel panel = new JPanel(new BorderLayout(5, 5));

		panel.add(new JButton("Hola!"), BorderLayout.CENTER);
		panel.add(createPanel(Color.green, 50, 50), BorderLayout.LINE_START);
		panel.add(createPanel(Color.yellow, 50, 50), BorderLayout.LINE_END);
		panel.add(createPanel(Color.blue, 20, 20), BorderLayout.PAGE_START);
		panel.add(createPanel(Color.gray, 20, 20), BorderLayout.PAGE_END);

		panel.setOpaque(true);
		return panel;
	}

	public JPanel someContent2() {

		// We create a bottom JPanel to place everything on.
		JPanel panel = new JPanel();
		panel.add(new JLabel("Username"));
		panel.add(new JTextField(8));

		return panel;
	}

	private JPanel createButtons() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		firstButton = new JButton("First");
		firstButton.addActionListener(this);
		panel.add(firstButton);

		prevButton = new JButton("Prev");
		prevButton.addActionListener(this);
		panel.add(prevButton);

		nextButton = new JButton("Next");
		nextButton.addActionListener(this);
		panel.add(nextButton);

		lastButton = new JButton("Last");
		lastButton.addActionListener(this);
		panel.add(lastButton);

		return panel;
	}

	private JPanel createPanel(Color color, int x, int y) {
		JPanel panel;
		panel = new JPanel();
		panel.setBackground(color);
		panel.setPreferredSize(new Dimension(x, y));
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout cl = (CardLayout) cardsPanel.getLayout();

		if (e.getSource() == nextButton) {
			cl.next(cardsPanel);
		} else if (e.getSource() == prevButton) {
			cl.previous(cardsPanel);
		} else if (e.getSource() == firstButton) {
			cl.first(cardsPanel);
		} else if (e.getSource() == lastButton) {
			cl.last(cardsPanel);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new CardLayoutExample();
			}
		});
	}

}
