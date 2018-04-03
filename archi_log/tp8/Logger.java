package tp8;

public class Logger {
	// The unique instance of Logger
	private static Logger instance;
	
	// The singleton pattern lock
	private static Object lock = Logger.class;
	
	// Message number
	private int count;
	
	private Logger() {
		count = 1;
	}
	
	public static Logger uncalledYet() {
		synchronized(lock) {
			return instance;
		}
	}
	
	public static Logger getInstance() {
		synchronized(lock) {
			if(instance == null) {
				instance = new Logger();
			}
			return instance;
		}
	}
	
	public void logError(String message) {
		synchronized(lock) {
			log("ERROR", message);
		}
	}
	
	public void logInfo(String message) {
		synchronized(lock) {
			log("INFO", message);
		}
	}
	
	public void logWarning(String message) {
		synchronized(lock) {
			log("WARNING", message);
		}
	}
	
	// Format and write the message and increment count
	private void log(String type, String message) {
		System.out.println(count + ". " + type + ": " + message);
		++count;
	}
}
