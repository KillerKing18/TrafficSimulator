package model;

import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import exceptions.UnexistingObjectException;
import ini.IniSection;
import music.Music;

public class Kart extends Vehicle {
	
	protected int _luck;
	protected Random _random;
	protected boolean _newLap;

	public Kart(String id, int maxSpeed, List<Junction> itinerary, int luck) {
		super(id, maxSpeed, itinerary);
		_luck = luck;
		_random = new Random();
		_newLap = false;
	}

	void advance() throws UnexistingObjectException {
		if(_itinerary.get(_itineraryIndex) == _itinerary.get(0) && !_atJunction && _itineraryIndex != 0 && _newLap) {
			_newLap = false;
			if (_random.nextInt(101) < _luck) {
				_currentSpeed = _currentSpeed * 2;
				ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/boost.gif"));
				Music music = new Music("src/music/itemboxboost.wav");
				music.play();
				music = null;
				JOptionPane.showMessageDialog(null, _id + " found an Item Box...\nEXTRA BOOST!", "Item Box", JOptionPane.INFORMATION_MESSAGE, icon);
			}
			else {
				ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/boo.gif"));
				Music music = new Music("src/music/itemboxboo.wav");
				music.play();
				music = null;
				JOptionPane.showMessageDialog(null, _id + " found an Item Box...\nBOO!", "Item Box", JOptionPane.WARNING_MESSAGE, icon);
				makeFaulty(1);
			}
		}
		int previous = _itineraryIndex;
		super.advance();
		if(_itineraryIndex != previous)
			_newLap = true;
	}
	
	protected void fillReportDetails(IniSection is) {
		is.setValue("type", "kart");
		super.fillReportDetails(is);
	}
}
