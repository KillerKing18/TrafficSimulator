package tp.examples.sensors.ver2;

public class Heater implements TempObserver {

	private boolean isOn;
	private int onTemp;
	private int offTemp;
	private String id;

	public Heater(String id, int onTemp, int offTemp) {
		isOn = false;
		this.id = id;
		this.onTemp = onTemp;
		this.offTemp = offTemp;
	}

	public void on() {
		isOn = true;
		System.out.println("Heater " + id + ": on");
	}

	public void off() {
		isOn = false;
		System.out.println("Heater " + id + ": off");
	}

	@Override
	public void tempChanged(float t) {
		if (t > this.offTemp && isOn)
			off();
		else if (t < onTemp && !isOn)
			on();
	}

}
