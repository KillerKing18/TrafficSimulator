package tp.examples.sensors.ver0;


public class Main {

	public static void main(String[] args) {
		TempSensor s = new TempSensor("s");
		Heater h = new Heater("h");
		AirCondition a = new AirCondition("a");

		while ( true ) {
			float currTemp = s.getTemperature();
			if ( currTemp > 30 ) {
				h.off();
				a.on();
			} else if ( currTemp < 20 ) {
				h.on();
				a.off();
			}
			sleep(5000); // sleep 5 seconds
		}
	}

	private static void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
		}
	}

}
