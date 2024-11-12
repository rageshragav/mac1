/**
 * 
 */
package testcasesextent;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author ragesh
 *
 */
public class TestClassUsingListeners {
	
	
	public WebDriver driver;

	@BeforeClass
	public void beforeClass() {

		
		driver = WebDriverManager.chromedriver().create();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.google.co.in/");
	}

	@Test
	public void testPassed() {
	
		Assert.assertTrue(false);
	}

	@Test
	public void testFailed() {
		driver.get("https://www.google.co.in/");
		
		Assert.fail();
	}

	@Test
	public void testSkipped() {
		driver.get("https://www.google.co.in/");
		
		throw new SkipException("Executin skip test method");
	}

	
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}

}
