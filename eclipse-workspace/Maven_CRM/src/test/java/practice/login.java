/**
 * 
 */
package practice;



import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import pages.LoginPage;
import utility.BrowserFactory;

/**
 * @author ragesh
 *
 */
public class login extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	
	public login() {
		super();
	}

	//static WebDriver driver ;
	
	@BeforeMethod
	public void setup() {
		initiqlization();
		loginPage = new LoginPage();
	}
	
	
	
	@Test
	public void loginTest() {
		
	homePage =	loginPage.login(properties.getProperty("username"), properties.getProperty("password"));
	}
	@Test
	public void launch() {
		
		driver = BrowserFactory.launchBrowser(driver, "Chrome","https://freecrm.com/");
		
		WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Login')]"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleIs("Free CRM software for customer relationship management, sales, marketing campaigns and support."));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
		System.out.println(driver.getTitle());
	}
}
