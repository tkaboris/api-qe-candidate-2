package utilities;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Configuration {
	
	static Logger logger = Logger.getLogger(Configuration.class);
	private static Properties properties;

	static {
		try {
			// Load config.properties
			String path = "src/test/resources/config.properties";
			FileInputStream input = new FileInputStream(path);

			properties = new Properties();
			properties.load(input);

			logger.info("Loaded config.properties: "+properties.toString());
			
			input.close();
		} catch (Exception e) {
			logger.error("config.properties couldn't be loaded: ");
			e.printStackTrace();
		}
	}

	public static String getProperty(String keyName) {
		String property = properties.getProperty(keyName);
		logger.info("Get "+keyName+" from config.properties: "+properties.getProperty(keyName));
		return property;
	}
	
	public static void main(String[] args) {
		
	}
}
