/**
 * 
 */
package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.TestBase;
import utility.BrowserFactory;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.ws.rs.core.UriBuilder;

/**
 * @author ragesh
 *
 */
public class pnr_status extends TestBase {
	
	 private static final String CHAT_ID = "60958867";
	 private static final String TOKEN = "5911845994:AAE9GjvejlkKbFnW769GPNlvEL2pWWGID7c";
	 private static String PNR_STATUS;
	 
	
	@Test
	public void launchConfirmTkt() throws InterruptedException, IOException {
		
		driver = BrowserFactory.launchBrowser(driver, "Chrome","https://www.confirmtkt.com/pnr-status");
		
		WebElement element = driver.findElement(By.xpath("//input[@id='pnr-text']"));
		element.sendKeys("4506738653");
		
		WebElement checkPNRButton = driver.findElement(By.xpath("//button[contains(text(),'Check PNR Status')]"));
		checkPNRButton.click();
		
		
		
		WebElement currentPNRStatus = driver.findElement(By.xpath("//tbody/tr[1]/td[2]"));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
		wait.until(ExpectedConditions.visibilityOf(currentPNRStatus));
		
		PNR_STATUS = currentPNRStatus.getText();
		System.out.println(PNR_STATUS);
		
		
		if(currentPNRStatus.isDisplayed() && !PNR_STATUS.isEmpty()) {
			apiCall();
		}
		
		Thread.sleep(1000);
		
		
	}
	
	public static void apiCall() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .version(HttpClient.Version.HTTP_2)
                .build();
 
        UriBuilder builder = UriBuilder
                .fromUri("https://api.telegram.org")
                .path("/{token}/sendMessage")
                .queryParam("chat_id", CHAT_ID)
                .queryParam("text", PNR_STATUS);
 
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(builder.build("bot" + TOKEN))
                .timeout(Duration.ofSeconds(5))
                .build();
 
        HttpResponse<String> response = client
          .send(request, HttpResponse.BodyHandlers.ofString());
 
        System.out.println(response.statusCode());
        System.out.println(response.body());
	}

}
