package tp.examples.sensors.ver1;

import java.util.ArrayList;

public class TempSensor implements TempObservable {

	private float t;
	private ArrayList<TempObserver> obs;
	private String id;

	public TempSensor(String id) {
		obs = new ArrayList<TempObserver>();
		this.id = id;
		refresh();
	}

	public void refresh() {
		float x = HWLib.getTemperature(id);
		if (t != x) {
			t = x;
			for (TempObserver o : obs) {
				o.tempChanged(t);
			}
		}
	}

	public void registerTempObserver(TempObserver l) {
		obs.add(l);
	}
}
