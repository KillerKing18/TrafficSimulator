package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.NewJunctionEvent;
import model.NewKartEvent;
import model.NewRoadEvent;
import model.TrafficSimulator;

public class ImagesPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TrafficSimulator _model;
	private RacingPanel _racingPanel;
	private CharacterChooserPanel _characterChooserPanel;
	private String[] _itinerary;
	private String[] _junctions;
	private ArrayList<String> _idsList;
	
	private String path;
	
	
	public ImagesPanel(RacingPanel racingPanel, String path, TrafficSimulator model, CharacterChooserPanel characterChooserPanel) {
		_racingPanel = racingPanel;
		this.path = path;
		_junctions = new String[0];
		_itinerary = new String[0];
		_idsList = new ArrayList<String>();
		_model = model;
		_characterChooserPanel = characterChooserPanel;
			
		this.setLayout(new GridLayout(1, 2));
		
		JLabel _image = new JLabel();
		ImageIcon icon = new ImageIcon(this.getClass().getResource(path));
		icon.setImage(icon.getImage().getScaledInstance(400, 150, 1));
		_image.setIcon(icon);
		this.add(_image);
	}
	
	public List<String> getSelectedCharacters() {
		return _idsList;
	}
	
	
	public void reset() {
		_idsList = new ArrayList<String>();
		_junctions = new String[0];
		_itinerary = new String[0];
	}
	
	public String getPath() {
		return path;
	}
	
	public boolean checkIn() {
		if(_itinerary.length != 0)
		{
			try {
				int nextJunction = 0;
				for(int i = 0; i < _junctions.length; i++)
					_model.addEvent(new NewJunctionEvent(0, _junctions[i]));
				for(int i = 0; i < _junctions.length; i++) {
					nextJunction = i == _junctions.length - 1 ? 0 : nextJunction + 1;
					_model.addEvent(new NewRoadEvent(0, "r" + (i + 1), _junctions[i], _junctions[nextJunction], 200, 50));
				}
				for(String id : _idsList)
					_model.addEvent(new NewKartEvent(0, id, _characterChooserPanel.getSpeedMap().get(id), _itinerary, _characterChooserPanel.getLuckMap().get(id)));
			}
			catch (Exception exc) {
			}
			return true;
		}
		else {
			JOptionPane.showMessageDialog(this, "You must choose a Cup to race in", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}
	
	public void addKart(String id) {
		if(!_idsList.contains(id))
			_idsList.add(id);
		else
			_idsList.remove(id);
		_racingPanel.notifyCharacterAdded();
	}
	
	public void setItinerary(String[] itinerary) {
		_itinerary = itinerary;
		_racingPanel.notifyCupSelected();
	}
	
	public void setJunctions(String[] junctions) {
		_junctions = junctions;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
