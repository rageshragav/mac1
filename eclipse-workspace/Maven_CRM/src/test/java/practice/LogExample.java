/**
 * 
 */
package practice;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * @author ragesh
 *
 */
public class LogExample{

	/**
	 * @param args
	 */
public static	Logger logger = Logger.getLogger(LogExample.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BasicConfigurator.configure();
		logger.info("this is info");
		logger.debug("this is debug");
		logger.warn("this is warn");
		logger.error("this is error");
		logger.fatal("this is fatal");

	}

}
