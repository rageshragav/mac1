package utility;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
public class BrowserFactory {
	
	//WebDriver driver = null;
	
	public static WebDriver launchBrowser(WebDriver driver, String browserName, String URL) {
		
		
		if (browserName.equalsIgnoreCase("Chrome"))
		{
			driver = WebDriverManager.chromedriver().create();
			
			//System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
			//driver = new ChromeDriver();
		}
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	//	WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Login')]"));
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return driver;

	}

	
	public static void quitBrowser(WebDriver driver) {
		//driver.close();

	}
}
