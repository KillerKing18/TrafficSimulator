package racingcontrol;

import java.io.InputStream;

import control.Controller;
import model.Event;
import model.RacingSimulator;
import model.RoadMap;
import model.SimulatorError;
import model.TrafficSimulator;

public class RacingController extends Controller {

	public RacingController(TrafficSimulator simulator, int ticks, InputStream inStream) {
		super(simulator, ticks, inStream);
	}
	
	public RoadMap getRoadMap() {
		return ((RacingSimulator) _sim).getRoadMap();
	}

	public void addEvent(Event e) {
		try {
			_sim.addEvent(e);
		} catch (SimulatorError e1) {
		}
	}
	
	public int getArrivedVehicles() {
		return ((RacingSimulator) _sim).getArrivedVehicles();
	}
	
	public int getTotalVehicles() {
		return ((RacingSimulator) _sim).getTotalVehicles();
	}
}
