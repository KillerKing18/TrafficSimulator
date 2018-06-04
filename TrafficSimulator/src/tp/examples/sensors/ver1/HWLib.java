package tp.examples.sensors.ver1;

import java.io.File;
import java.util.Scanner;

public class HWLib {

	private final static String devpath = "/tmp/";

	public static float getTemperature(String id) {

		float t = 0;
		try {
			File file = new File(devpath + id);
			Scanner in = new Scanner(file);
			t = in.nextFloat();
			in.close();
		} catch (Exception e) {
			System.out.println("Error while reading temprature from a file: "
					+ e);
		}

		return t;
	}
}
