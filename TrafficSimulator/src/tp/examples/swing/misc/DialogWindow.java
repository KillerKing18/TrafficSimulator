package tp.examples.swing.misc;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DialogWindow extends JDialog {

	private static final long serialVersionUID = 1L;

	private JList<String> _itemsList;
	protected int _status;

	public DialogWindow(Frame parent) {
		super(parent, true); // change true to false for non-modal
		initGUI();
	}

	private void initGUI() {

		setTitle("Generate Reports");
		JPanel mainPanel = new JPanel(new BorderLayout());

		String[] items = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
		_itemsList = new JList<String>(items);

		mainPanel.add(_itemsList, BorderLayout.CENTER);

		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(buttonsPanel, BorderLayout.PAGE_END);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_status = 0;
				DialogWindow.this.setVisible(false);
			}
		});
		buttonsPanel.add(cancelButton);

		JButton OKButton = new JButton("OK");
		OKButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_status = 1;
				DialogWindow.this.setVisible(false);
			}
		});
		buttonsPanel.add(OKButton);

		mainPanel.add(buttonsPanel, BorderLayout.PAGE_END);

		setContentPane(mainPanel);
		setMinimumSize(new Dimension(100, 100));
		setVisible(false);
	}

	public String[] getSelectedItems() {
		int[] selectedIndices = _itemsList.getSelectedIndices();
		String[] items = new String[selectedIndices.length];

		for(int i=0; i<items.length; i++) {
			items[i] = _itemsList.getModel().getElementAt(selectedIndices[i]);
		}
		return items;
	}

	public int open() {
		setLocation(getParent().getLocation().x + 50, getParent().getLocation().y + 50);
		pack();
		setVisible(true);
		return _status;
	}

}
