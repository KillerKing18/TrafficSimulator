package view;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Event;
import model.RoadMap;
import model.SimulatorError;
import model.TrafficSimulator;
import model.TrafficSimulatorObserver;

public class StateBarPanel extends JPanel implements TrafficSimulatorObserver{
	
	private JLabel infoExecution;
	
	public StateBarPanel(String message, TrafficSimulator model){
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.infoExecution = new JLabel(message);
		this.add(this.infoExecution);
		this.setBorder(BorderFactory.createBevelBorder(1));
		model.addObserver(this);
	}

	
	public void setMessage(String message){ 
		this.infoExecution.setText(message);
	}
	
	@Override
	public void registered(int time, RoadMap map, List<Event> events) {
	}

	@Override
	public void simulatorError(int time, RoadMap map, List<Event> events, SimulatorError e) {
		this.infoExecution.setText("Error with the simulator!");
	}

	@Override
	public void advanced(int time, RoadMap map, List<Event> events) {
		this.infoExecution.setText("Advanced!");
	}

	@Override
	public void eventAdded(int time, RoadMap map, List<Event> events) {
		this.infoExecution.setText("New Event added!");
		
	}

	@Override
	public void reset(int time, RoadMap map, List<Event> events) {
		this.infoExecution.setText("Simulator reseted...");	
	}

}
