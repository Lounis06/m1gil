package tp8;

import tp8.myLib.*;

public class Appli {
	private LibStuff stuff;
	private Logger logger;

	public void initApp(Logger logger) {
		this.logger = logger;
		logger.logInfo("Initializing App...");
		// ... some code here
		stuff= new LibStuff();
		logger.logInfo("Initialization done!");
	}

	public void closeApp() {
		logger.logInfo("Closing app...");
		// ... some code here
		logger.logInfo("Ressources freeed!");
	}

	public void run() {
		boolean errors=false;
		for(int i=0; i<10; ++i) {
			int action = (int)(100.0*Math.random());
			if (action<50) {
				stuff.doSomething(action);
			} else {
				logger.logError("Invalide action : "+action);
				errors=true;
			}
		}
		if (errors) {
			logger.logWarning("some errors occured during processing.");
		}
	}

	public static void main(String[] args) {
		Appli app=new Appli();
		Logger logger;
		if(args.length == 3 && args[2] == "filelog") {
			logger = Logger.getInstance();
		} else {
			logger = Logger.getInstance();
		}
		app.initApp(logger);
		app.run();
		app.closeApp();
	}
}