package view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class CupChooserPanel extends ChooserPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String[][] _junctions = {{"Mario Kart Stadium", "Water Park", "Sweet Sweet Canyon", "Thwomp Ruins"}, // Mushroom cup
			{"Mario Circuit", "Toad Harbor", "Twisted Mansion", "Shy Guy Falls"}, // Flower cup
			{"Sunshine Airport", "Dolphin Shoals", "Electrodrome", "Mount Wario"}, // Star cup
			{"Cloudtop Cruise", "Bone-Dry Dunes", "Bowser's Castle", "Rainbow Road"}, // Special cup
			{"Moo Moo Meadows", "Mario Circuit", "Cheep Cheep Beach", "Toad's Turnpike"}, // Shell cup
			{"Dry Dry Desert", "Donut Plains 3", "Royal Raceway", "DK Jungle"}, // Banana cup
			{"Wario Stadium", "Sherbet Land", "Music Park", "Yoshi Valley"}, // Leaf cup
			{"Tick-Tock Clock", "Piranha Plant Slide", "Grumble Volcano", "Rainbow Road"}}; // Lightning cup
	
	private Map<String, String[]> _cupsMap;
	
	private String[] circuits;
	
	private String _selectedCup;

	public CupChooserPanel(String[] items) {
		super(items);
		circuits = items;
		_cupsMap = new HashMap<String, String[]>();
		_cupsMap.put(circuits[0], _junctions[0]);
		_cupsMap.put(circuits[1], _junctions[1]);
		_cupsMap.put(circuits[2], _junctions[2]);
		_cupsMap.put(circuits[3], _junctions[3]);
		_cupsMap.put(circuits[4], _junctions[4]);
		_cupsMap.put(circuits[5], _junctions[5]);
		_cupsMap.put(circuits[6], _junctions[6]);
		_cupsMap.put(circuits[7], _junctions[7]);
		
	}
	
	public Map<String, String[]> getCupsMap(){
		return _cupsMap;
	}
	
	public String getSelectedCup() {
		return _selectedCup;
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
				_selectedCup = path;
				String[] junctions = _cupsMap.get(path);
				String[] itinerary = new String[(junctions.length * 3) + 1];
				for(int i = 0; i < (junctions.length * 3) + 1; i++)
					itinerary[i] = junctions[i % junctions.length];
				_imagesPanel.setItinerary(itinerary);
				_imagesPanel.setJunctions(junctions);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				/* TODO
				_imagesPanel.removeAll();
				ImageIcon icon2 = new ImageIcon(this.getClass().getResource("/images/" + path + ".gif"));
				icon2.setImage(icon2.getImage().getScaledInstance(75, 75, 1));
				_imagesPanel.add(new JLabel(icon2));
				_imagesPanel.add(new JTextArea("Speed : 80\nLuck: 80"));
				_imagesPanel.repaint();
				updateUI();
				*/
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
	
	public void reset() {
		_selectedCup = null;
	}
}
