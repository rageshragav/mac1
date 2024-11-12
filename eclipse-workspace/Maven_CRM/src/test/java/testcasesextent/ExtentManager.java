/**
 * 
 */
package testcasesextent;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * @author ragesh
 *
 */
public class ExtentManager {

	private static ExtentReports extent;
	public ExtentSparkReporter htmlReporter;

	public static ExtentReports createInstance() {

		String filename = getReportName();
		String directory = System.getProperty("user.dir") + "/reports/";
		new File(directory).mkdir();
		String path = directory+filename;
		 ExtentSparkReporter htmlReporter = new ExtentSparkReporter(path);
	//	htmlReporter = new ExtentSparkReporter("./reports/extent.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Automation test results");
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.setSystemInfo("organization", "expleo");
		extent.setSystemInfo("Browser", "Chrome");
		extent.attachReporter(htmlReporter);

		return extent;
	}

	public static String getReportName() {
		Date d = new Date();
		String fileName = "AutomationReport_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
		return fileName;
	}

}
