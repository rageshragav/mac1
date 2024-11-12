/**
 * 
 */
package practice;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author ragesh
 *
 */
public class PropLog4jExample {

	/**
	 * @param args
	 */
	
	static Logger logger = Logger.getLogger(PropLog4jExample.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PropertyConfigurator.configure("log4j.properties");
		logger.info("this is info");
		logger.debug("this is debug");
		logger.warn("this is warn");
		logger.error("this is error");
		logger.fatal("this is fatal");
	}

}
