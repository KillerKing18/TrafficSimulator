package tp.examples.sensors.ver0;

public class AirCondition {

	private boolean isOn;
	String id;

	public AirCondition(String id) {
		isOn = false;
		this.id = id;
	}

	public void on() {
		if ( !isOn ) {
			isOn = true;
			System.out.println("AC " + id + ": on");
		}
	}

	public void off() {
		if ( isOn ) {
			isOn = false;
			System.out.println("AC " + id + ": off");
		}
	}

}
