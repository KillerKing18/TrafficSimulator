package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import model.Event;
import model.RoadMap;
import model.SimulatorError;
import model.TrafficSimulatorObserver;

public class ReportsAreaPanel extends TextAreaPanel implements TrafficSimulatorObserver, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RoadMap _map;
	private int _time;
	private DialogWindow _dialog;
	private MainPanel _mainPanel;
	
	public ReportsAreaPanel(MainPanel mainPanel, String title, boolean editable){
		super(title, editable);
		_mainPanel = mainPanel;
		initGUI();
	}
	
	private void initGUI(){
		_dialog = new DialogWindow(_mainPanel);		
	}

	@Override
	public void registered(int time, RoadMap map, List<Event> events) {
		_map = map;
	}

	@Override
	public void simulatorError(int time, RoadMap map, List<Event> events, SimulatorError e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void advanced(int time, RoadMap map, List<Event> events) {
		_time = time;
		_map = map;
	}

	@Override
	public void eventAdded(int time, RoadMap map, List<Event> events) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset(int time, RoadMap map, List<Event> events) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		switch(str){
		case "GENERATE":
			_dialog.setData(_map.getVehicles(), _map.getRoads(), _map.getJunctions());
			int status = _dialog.open();
			if ( status == 0) {
				// TODO
				System.out.println("Canceled");
			} 
			else {
				insert(_map.generateSelectedReports(_time, _dialog.getSelectedVehicles(), _dialog.getSelectedRoads(), _dialog.getSelectedJunctions()));
			}
			//insert(_map.generateReport(_time));
			break;
		case "CLEAR":
			clear();
			break;
		default:
			break;
		}
		
	}

}
