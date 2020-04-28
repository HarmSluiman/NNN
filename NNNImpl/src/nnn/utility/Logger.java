package nnn.utility;

public class Logger {
	public static int none = 0;
	public static int info = 1;
	public static int error = 2;
	public static int exception = 3;
	public static int verbose = 4;
	private static int activeLevel = Logger.verbose;

	public static int getActiveLevel() {
		return activeLevel;
	}

	public static void setActiveLevel(int activeLevel) {
		Logger.activeLevel = activeLevel;
	}

	public static void log(String input) {
		if (getActiveLevel() == Logger.verbose) {
			System.out.println(input);
		}

	}

}
