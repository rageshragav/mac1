/**
 * 
 */
package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author ragesh
 *
 */
public class TestBase {
	
	public static WebDriver driver;
public	static Properties properties;
	
	public TestBase() {
	try {
		
		properties = new Properties();
		FileInputStream ip = new FileInputStream("config.properties");
		try {
			properties.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}catch (FileNotFoundException e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	}
	
	public static void initiqlization() {
		
	String browserNameString=	properties.getProperty("browser");
	
	if (browserNameString.equals("chrome")){
		 driver = WebDriverManager.chromedriver().create();
	}
	
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get(properties.getProperty("url"));
	 
	}

}
