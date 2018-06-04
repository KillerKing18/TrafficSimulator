package model;

public class RacingRoadMap extends RoadMap{

	private int totalVehicles;
	
	public RacingRoadMap() {
		super();
		totalVehicles = 0;
	}
	
	@Override
	void addVehicle(Vehicle vehicle) throws SimulatorError {
		try {
			super.addVehicle(vehicle);
			totalVehicles++;
		}
		catch (SimulatorError e) {
			throw e;
		}
	}
	
	@Override
	void clear() {
		super.clear();
		totalVehicles = 0;
	}
	
	public int getTotalVehicles() {
		return totalVehicles;
	}
}
