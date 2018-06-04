package tp.examples.sensors.ver1;


public class Main {

	public static void main(String[] args) {
		TempSensor s = new TempSensor("s");
		Heater h = new Heater("h",19,25);
		AirCondition a = new AirCondition("a",30,25);

		Heater h1 = new Heater("h1",19,25);
		AirCondition a1 = new AirCondition("a1",30,25);

		s.registerTempObserver(h);
		s.registerTempObserver(a);
		s.registerTempObserver(h1);
		s.registerTempObserver(a1);

		while ( true ) {
			s.refresh();
			sleep(5000);
		}

	}

	private static void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
		}
	}

}
