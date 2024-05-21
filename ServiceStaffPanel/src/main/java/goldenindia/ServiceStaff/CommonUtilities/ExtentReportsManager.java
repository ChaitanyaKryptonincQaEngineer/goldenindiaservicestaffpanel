package goldenindia.ServiceStaff.CommonUtilities;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.*;

public class ExtentReportsManager {

	public static ExtentReports extent;
	public static ExtentTest test;

	public static ExtentReports configReports() {
		if (extent == null) {
			File file = new File(System.getProperty("user.dir") + "//Reports");
			ExtentSparkReporter reporter = new ExtentSparkReporter(file);
			reporter.config().setDocumentTitle("Test Report");
			reporter.config().setReportName("Bug Report Version 1.0");

			extent = new ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo("QA Engineer", "Chaitanya Gopalasetty");
		}
		return extent;
	}
}
