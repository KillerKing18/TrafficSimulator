package racingview;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.NewJunctionEvent;
import model.NewKartEvent;
import model.NewRoadEvent;
import model.SimulatorError;
import racingcontrol.RacingController;

public class ImagesPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RacingController _control;
	
	private String path;
	
	
	public ImagesPanel(String path, RacingController control) {
		this.path = path;
		_control = control;
			
		this.setLayout(new GridLayout(1, 2));
		
		JLabel _image = new JLabel();
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/icons/clear.png"));
		icon.setImage(icon.getImage().getScaledInstance(400, 150, 1));
		_image.setIcon(icon);
		this.add(_image);
	}
	
	public String getPath() {
		return path;
	}
	
	public boolean checkIn(String[] selectedCupJunctions, String[] selectedCupItinerary, Map<String, Integer> speedMap, Map<String, Integer> luckMap, List<String> IDs) throws SimulatorError {
		if(IDs.size() < 2) {
			ImageIcon icon = new ImageIcon(getClass().getResource("/icons/clear.png"));
			//Music music = new Music("src/music/mammamia.wav");
			//music.play();
			JOptionPane.showMessageDialog(this, "You must choose at least 2 pilots in order to start the race!", "Mamma Mia", JOptionPane.WARNING_MESSAGE, icon);
			//music.stop();
			//music = null;
			return false;
		}
		else {
			int nextJunction = 0;
			for(int i = 0; i < selectedCupJunctions.length; i++)
				_control.addEvent(new NewJunctionEvent(0, selectedCupJunctions[i]));
			for(int i = 0; i < selectedCupJunctions.length; i++) {
				nextJunction = i == selectedCupJunctions.length - 1 ? 0 : nextJunction + 1;
				_control.addEvent(new NewRoadEvent(0, "r" + (i + 1), selectedCupJunctions[i], selectedCupJunctions[nextJunction], 200, 50));
			}
			for(String id : IDs)
				_control.addEvent(new NewKartEvent(0, id, speedMap.get(id), selectedCupItinerary, luckMap.get(id)));
			return true;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
