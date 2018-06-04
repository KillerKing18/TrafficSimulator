package tp.examples.swing.misc;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Component;

@SuppressWarnings("serial")
public class ScrollPaneExample_1 extends JFrame {

	public ScrollPaneExample_1() {
		super("[=] Scroll Pane with JPanel [=]");
		initGUI();
	}

	private void initGUI() {

		JPanel mainPanel = new JPanel(new BorderLayout());

		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

		addButton("Holaa", p);
		addButton("Holaaa", p);
		addButton("Holaaaa", p);
		addButton("Holaaaaa", p);
		addButton("Holaaaaaaaaaaaaaaaaaaaaaa", p);

		JScrollPane area = new JScrollPane(p,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainPanel.add(area);
		mainPanel.setOpaque(true);

		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	private void addButton(String text, JPanel container) {
		JButton button = new JButton(text);
		// try LEFT or RIGHT instead of CENTER
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		container.add(button);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ScrollPaneExample_1();
			}
		});
	}
}
