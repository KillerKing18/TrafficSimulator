package tp.examples.sensors.ver1;

public class AirCondition implements TempObserver {

	private boolean isOn;
	private float onTemp;
	private float offTemp;
	private String id;

	public AirCondition(String id, float onTemp, float offTemp) {
		isOn = false;
		this.id = id;
		this.onTemp = onTemp;
		this.offTemp = offTemp;
	}

	public void on() {
		isOn = true;
		System.out.println("AC " + id + ": on");
	}

	public void off() {
		isOn = false;
		System.out.println("AC " + id + ": off");
	}

	@Override
	public void tempChanged(float t) {
		if (t < this.offTemp && isOn)
			off();
		else if (t > onTemp && !isOn)
			on();
	}

}
