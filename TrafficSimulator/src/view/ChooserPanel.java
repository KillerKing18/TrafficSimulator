package view;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class ChooserPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected ImagesPanel _imagesPanel;
	protected String[] _items;

	public ChooserPanel(String[] items) {
		_items = items;
	}
	
	public void initGUI() {
		this.setLayout(new GridLayout(2, 4));

		for(String str : _items) {
			JButton button = new JButton();
			createGenericButton(button, str);
			this.add(button);
		}
	}
	
	protected void createGenericButton(JButton button, String path) {
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/" + path + ".png"));
		icon.setImage(icon.getImage().getScaledInstance(75, 75, 1));
		button.setIcon(icon);
		button.setToolTipText(path);
		setMouseListener(button, path);
		button.setActionCommand(path);
	}
	
	abstract protected void setMouseListener(JButton button, String path);
	
	public void setImagesPanel(ImagesPanel imagesPanel) {
		_imagesPanel = imagesPanel;
	}
}
