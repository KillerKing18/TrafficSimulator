package view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CharacterChooserPanel extends ChooserPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Integer[] speeds = {35, 35, 10, 40, 15, 25, 40, 30};
	private static final Integer[] luck = {75, 70, 90, 50, 40, 90, 30, 60};
	private static final String[] id = {"mario", "luigi", "bowser", "peach", "donkey", "toad", "yoshi", "koopa"};
	
	private Map<String, Integer> _speedMap;
	private Map<String, Integer> _luckMap;

	public CharacterChooserPanel(String[] items) {
		super(items);
		_speedMap = new HashMap<String, Integer>();
		_luckMap = new HashMap<String, Integer>();
		
		for(int i = 0; i < id.length; i++) {
			_speedMap.put(id[i], speeds[i]);
			_luckMap.put(id[i], luck[i]);
		}
	}
	
	public Map<String, Integer> getSpeedMap() {
		return _speedMap;
	}
	
	public Map<String, Integer> getLuckMap() {
		return _luckMap;
	}

	@Override
	protected void setMouseListener(JButton button, String path) {
		button.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				_imagesPanel.addKart(button.getActionCommand());
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				_imagesPanel.removeAll();
				ImageIcon icon2 = new ImageIcon(this.getClass().getResource("/images/" + path + ".gif"));
				icon2.setImage(icon2.getImage().getScaledInstance(75, 75, 1));
				_imagesPanel.add(new JLabel(icon2));
				JPanel temp = new JPanel();
				temp.setLayout(new GridLayout(2, 1));
				JLabel speed = new JLabel("Speed : " + _speedMap.get(path));
				speed.setFont(new Font("Sheriff", Font.BOLD, 25));
				temp.add(speed);
				JLabel luck = new JLabel("Luck : " + _luckMap.get(path));
				luck.setFont(new Font("Sheriff", Font.BOLD, 25));
				temp.add(luck);
				_imagesPanel.add(temp);
				_imagesPanel.repaint();
				updateUI();
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				_imagesPanel.removeAll();
				JLabel _image = new JLabel();
				ImageIcon icon = new ImageIcon(this.getClass().getResource(_imagesPanel.getPath()));
				icon.setImage(icon.getImage().getScaledInstance(400, 150, 1));
				_image.setIcon(icon);
				_imagesPanel.add(_image);
				_imagesPanel.repaint();
				updateUI();
				
			}
		});
	}
}
