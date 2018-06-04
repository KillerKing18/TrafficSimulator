package tp.examples.sensors.ver0;


public class Heater {

	private boolean isOn;
	private String id;

	public Heater(String id) {
		isOn = false;
		this.id = id;
	}

	public void on() {
		if ( !isOn ) {
			isOn = true;
			System.out.println("Heater " + id + ": on");
		}
	}

	public void off() {
		if ( isOn ) {
			isOn = false;
			System.out.println("Heater " + id + ": off");
		}
	}

}
