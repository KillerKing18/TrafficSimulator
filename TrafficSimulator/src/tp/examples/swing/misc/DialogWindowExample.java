package tp.examples.swing.misc;

import javax.swing.*;

import tp.examples.swing.icons.IconsDir;

import java.awt.event.*;

@SuppressWarnings("serial")
public class DialogWindowExample extends JFrame  {

	String names[];
	JComboBox<String> list;
	private DialogWindow dialog_;

	public DialogWindowExample() {
		super("Dialog Example");
		initGUI();
	}

	private void initGUI() {

		JPanel mainPanel = new JPanel();
		mainPanel.add(new JLabel("If you click "));
		dialog_ = new DialogWindow(this);
		JButton here = new JButton("here");
		here.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int status = dialog_.open();
				if ( status == 0) {
					System.out.println("Canceled");
				} else {
					System.out.println("The following items where selected:");
					for(String s : dialog_.getSelectedItems()) {
						System.out.println(s);
					}
				}
			}
		});
		mainPanel.add(here);
		mainPanel.add(new JLabel("a dialog window is opened and the main window blocks."));

		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new DialogWindowExample();
			}
		});
	}
}