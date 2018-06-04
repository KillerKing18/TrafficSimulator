package tp.examples.sensors.ver2;


public class Main {

	public static void main(String[] args) {
		TempSensor s = new TempSensor("s");
		TempSensor t = new TempSensor("t");
		Heater h = new Heater("h",19,30);
		AirCondition a = new AirCondition("a",30,19);

		s.registerTempObserver(h);
		s.registerTempObserver(a);
		t.registerTempObserver(h);
		t.registerTempObserver(a);

		s.start();
		t.start();
	}


}
