package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class FlowLayoutExample_1 extends JFrame implements ActionListener {

	JPanel centerPanel, leftPanel, rightPanel, topPanel, botPanel;
	JButton alignButton;

	public FlowLayoutExample_1() {
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

		for (int i = 0; i < 10; i++) {
			centerPanel.add(createPanel(Color.cyan, 20, 20));
		}

		alignButton = new JButton("Change Alignment");
		alignButton.addActionListener(this);
		botPanel.add(alignButton);

		mainPanel.setOpaque(true);

		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(280, 190);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		FlowLayout fl = ((FlowLayout) centerPanel.getLayout());
		int a = fl.getAlignment();
		switch (a) {
		case FlowLayout.LEFT:
			a = FlowLayout.CENTER;
			break;
		case FlowLayout.CENTER:
			a = FlowLayout.RIGHT;
			break;
		case FlowLayout.RIGHT:
			a = FlowLayout.LEFT;
		}
		fl.setAlignment(a);
		centerPanel.revalidate();
	}

	private JPanel createPanel(Color color, int x, int y) {

		JPanel panel;
		panel = new JPanel();
		panel.setBackground(color);
		panel.setPreferredSize(new Dimension(x, y));
		return panel;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new FlowLayoutExample_1();
			}
		});
	}

}
