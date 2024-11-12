/**
 * 
 */
package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;

/**
 * @author ragesh
 *
 */
public class LoginPage extends TestBase{

	@FindBy(name ="uid")
	WebElement userName;
	
	@FindBy(name ="password")
	WebElement passWord;
	
	@FindBy(name ="btnLogin")
	WebElement btnLogin;
	
	
	public LoginPage() {
		
		PageFactory.initElements(driver, this);
	}
	
	public HomePage login(String usn,String pass) {
		
		userName.sendKeys(usn);
		passWord.sendKeys(pass);
		btnLogin.click();
		return new HomePage();
	}
}
