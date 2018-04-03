package tp8.myLib;

import tp8.Logger;

public class LibStuff {
	private Logger logger = Logger.getInstance();
	
	public LibStuff() {
		logger.logInfo("Creating a Stuff.");
		// ...
	}

	public void doSomething(int action) {
		logger.logInfo("Doing something on : "+this+" "+action);
		// ...
		logger.logInfo("Something done!");
	}

	public void finalize() {
		logger.logInfo("This stuff is dying :"+this);
	}
}