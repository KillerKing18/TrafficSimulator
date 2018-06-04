package tp.examples.sensors.ver2;

import java.util.ArrayList;

public class TempSensor extends Thread implements TempObservable {

	private float t;
	private ArrayList<TempObserver> ls;
	private String id;

	public TempSensor(String id) {
		ls = new ArrayList<TempObserver>();
		this.id = id;
		refresh();
	}

	public void run() {
		while ( true ) {
			refresh();
			sleep(5000);
		}
	}
	
	public void refresh() {
		float x = HWLib.getTemperature(id);
		if (t != x) {
			t = x;
			for (TempObserver l : ls) {
				l.tempChanged(t);
			}
		}
	}

	public void registerTempObserver(TempObserver l) {
		ls.add(l);
		// enable to send an initial notification with temperature
		// l.tempChanged(t); 
	}
	
	private static void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
		}
	}

}
