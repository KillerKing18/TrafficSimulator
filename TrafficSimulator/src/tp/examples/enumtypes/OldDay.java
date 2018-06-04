package tp.examples.enumtypes;

public class OldDay {
	final public static int MONDAY = 0;
	final public static int TUESDAY = 1;
	final public static int WEDNESDAY = 2;
	final public static int THURSDAY = 3;
	final public static int FRIDAY = 4;
	final public static int SATURDAY = 5;
	final public static int SUNDAY = 6;

	public static void main(String[] args) {
		int day = OldDay.MONDAY;

		if (day == OldDay.THURSDAY) {
			System.out.println("Today is Tuesday");
		}
	}
}
