package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class FlowLayoutExample_2 extends JFrame implements ActionListener {

	JPanel centerPanel, leftPanel, rightPanel, topPanel, botPanel;
	JButton layoutButton;
	int lastLayout;

	public FlowLayoutExample_2() {
		super("[=] Change Layout [=]");
		initGUI();
	}

	private void initGUI() {

		// We create a bottom JPanel to place everything on.
		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));

		centerPanel = createPanel(Color.red, 50, 50);
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		leftPanel = createPanel(Color.green, 50, 50);
		mainPanel.add(leftPanel, BorderLayout.LINE_START);

		rightPanel = createPanel(Color.yellow, 50, 50);
		mainPanel.add(rightPanel, BorderLayout.LINE_END);

		topPanel = createPanel(Color.blue, 50, 30);
		mainPanel.add(topPanel, BorderLayout.PAGE_START);

		botPanel = createPanel(Color.gray, 50, 40);
		mainPanel.add(botPanel, BorderLayout.PAGE_END);

		for (int i = 0; i < 5; i++) {
			centerPanel.add(createPanel(Color.white, 20, 20));
			centerPanel.add(createPanel(Color.black, 20, 20));
		}

		lastLayout = 0;

		layoutButton = new JButton("Change Layout");
		layoutButton.addActionListener(this);
		botPanel.add(layoutButton);

		mainPanel.setOpaque(true);

		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(280, 190);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (lastLayout == 0) {
			centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
			lastLayout = 1;
		} else {
			centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			lastLayout = 0;
		}

		centerPanel.revalidate();
	}

	private JPanel createPanel(Color color, int x, int y) {

		JPanel panel;
		panel = new JPanel();
		panel.setBackground(color);
		panel.setPreferredSize(new Dimension(x, y));
		panel.setMaximumSize(new Dimension(x, y));
		panel.setMinimumSize(new Dimension(x, y));
		return panel;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new FlowLayoutExample_2();
			}
		});
	}

}
