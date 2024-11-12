package testcasesextent;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;

public class NewTest {

	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest extentTest;
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {

		htmlReporter = new ExtentSparkReporter("./reports/extent.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Automation test results");
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.setSystemInfo("organization", "expleo");
		extent.setSystemInfo("Browser", "Chrome");
		extent.attachReporter(htmlReporter);

		driver = WebDriverManager.chromedriver().create();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.google.co.in/");
	}

	@Test
	public void testPassed() {
	
		extentTest = extent.createTest("Successfull test");
		extentTest.log(Status.PASS, "Test method succcess");
	}

	@Test
	public void testFailed() {
		driver.get("https://www.google.co.in/");
		extentTest = extent.createTest("Successfull test");
		extentTest.log(Status.FAIL, "Test method fail");
		Assert.fail();
	}

	@Test
	public void testSkipped() {
		driver.get("https://www.google.co.in/");
		extentTest = extent.createTest("Skipped test");
		extentTest.log(Status.SKIP, "Test method skipped");
		throw new SkipException("Executin skip test method");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		String methodName = result.getMethod().getMethodName();

		if (result.getStatus() == ITestResult.FAILURE) {
			String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
			extentTest.fail("<details><summary><b><font color=red>Exception Occured, click to see details:"
					+ "</font></b></summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details> \n");
			String path = takeScreenshot(result.getMethod().getMethodName());
			extentTest.fail("<b><font color=red>" + "Screenshot of failure" + "</font></b>",
					MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			String longText = "<b>Test Method " + methodName + " Failed</b>";
			Markup m = MarkupHelper.createLabel(longText, ExtentColor.RED);
			extentTest.log(Status.FAIL, m);
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			String longText = "<b>Test Method " + methodName + " Successful</b>";
			Markup m = MarkupHelper.createLabel(longText, ExtentColor.GREEN);
			extentTest.log(Status.PASS, m);

		} else if (result.getStatus() == ITestResult.SKIP) {
			String longText = "<b>Test Method " + methodName + " Skipped</b>";
			Markup m = MarkupHelper.createLabel(longText, ExtentColor.YELLOW);
			extentTest.log(Status.SKIP, m);
		}

	}
	public String takeScreenshot(String methodName) {
		String fileName = getScreenshotName(methodName);
		String directory = System.getProperty("user.dir")+"/screenshots/";
		String path = directory+fileName;
		
		try {
			 File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			 FileUtils.copyFile(screenshot, new File(path));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return path;
	}
	
	public static String getScreenshotName(String methodName) {
		Date d = new Date();
		String fileName = methodName + "_" +d.toString().replace(":", "_").replace(" ", "_")+ ".png";
		return fileName;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
		extent.flush();
	}

}
