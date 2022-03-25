package stepDefinitions;

import org.apache.log4j.Level;
import utilities.Configuration;
import org.apache.log4j.Logger;

public class BaseTest {


	boolean displayLoggerInfo = Boolean.parseBoolean(Configuration.getProperty("displayLoggerInfo"));

	public void set_logging() {
		if (displayLoggerInfo) {
			//System.out.println("Setting logger = INFO");
			Logger.getRootLogger().setLevel(Level.INFO);
		} else {
			//System.out.println("Setting logger = OFF");
			Logger.getRootLogger().setLevel(Level.OFF);
		}
	}

}
