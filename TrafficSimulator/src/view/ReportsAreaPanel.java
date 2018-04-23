package view;

import java.util.List;

import control.Controller;
import model.Event;
import model.RoadMap;
import model.SimulatorError;
import model.TrafficSimulatorObserver;

public class ReportsAreaPanel extends TextAreaPanel implements TrafficSimulatorObserver{

	public ReportsAreaPanel(String title, boolean editable, Controller control){
		super(title, editable);
		// TODO control.addObserver(this);
	}

	@Override
	public void registered(int time, RoadMap map, List<Event> events) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void simulatorError(int time, RoadMap map, List<Event> events, SimulatorError e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void advanced(int time, RoadMap map, List<Event> events) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eventAdded(int time, RoadMap map, List<Event> events) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset(int time, RoadMap map, List<Event> events) {
		// TODO Auto-generated method stub
		
	}

}
