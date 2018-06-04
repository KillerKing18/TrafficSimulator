package tp.examples.sensors.ver0;

public class TempSensor {

	private float t;
	private String id;

	public TempSensor(String id) {
		this.id = id;
	}

	public float getTemperature() {
		t = HWLib.getTemperature(id);
		return t;
	}

}
