package racingview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	private ArrayList<String> _idsList;

	public CharacterChooserPanel(String[] items, RacingPanel racingPanel, ImagesPanel imagesPanel) {
		super(items, racingPanel, imagesPanel);
		_speedMap = new HashMap<String, Integer>();
		_luckMap = new HashMap<String, Integer>();
		_idsList = new ArrayList<String>();
		_idsList.add("mario");
		
		for(int i = 0; i < id.length; i++) {
			_speedMap.put(id[i], speeds[i]);
			_luckMap.put(id[i], luck[i]);
		}
	}
	
	public void reset() {
		_idsList = new ArrayList<String>();
		_idsList.add("mario");
	}
	
	private void addKart(String id) {
		if(!_idsList.contains(id))
			_idsList.add(id);
		else
			_idsList.remove(id);
	}
	
	public Map<String, Integer> getSpeedMap() {
		return _speedMap;
	}
	
	public Map<String, Integer> getLuckMap() {
		return _luckMap;
	}
	
	public List<String> getSelectedCharacters() {
		return _idsList;
	}

	@Override
	protected void setMouseListener(JButton button, String path) {
		button.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
				addKart(button.getActionCommand());
				_racingPanel.notifyCharacterAdded();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				_imagesPanel.removeAll();
				JPanel temp1 = new JPanel();
				temp1.setLayout(new BorderLayout());
				temp1.setPreferredSize(new Dimension(200, 150));
				temp1.setMaximumSize(new Dimension(200, 150));
				temp1.setMinimumSize(new Dimension(200, 150));
				ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/" + path + ".gif"));
				icon.setImage(icon.getImage().getScaledInstance(75, 75, 1));
				temp1.add(new JLabel(icon));
				_imagesPanel.add(temp1);
				JPanel temp2 = new JPanel();
				temp2.setLayout(new GridLayout(2, 1));
				JLabel speed = new JLabel("Speed : " + _speedMap.get(path));
				speed.setFont(new Font("Sheriff", Font.BOLD, 25));
				temp2.add(speed);
				JLabel luck = new JLabel("Luck : " + _luckMap.get(path));
				luck.setFont(new Font("Sheriff", Font.BOLD, 25));
				temp2.add(luck);
				_imagesPanel.add(temp2);
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
