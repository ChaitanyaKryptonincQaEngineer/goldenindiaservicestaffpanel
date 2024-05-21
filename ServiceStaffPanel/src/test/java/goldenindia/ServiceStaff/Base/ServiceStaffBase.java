package goldenindia.ServiceStaff.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentTest;

import goldenindia.ServiceStaff.CommonUtilities.CommonUtilities;
import goldenindia.ServiceStaff.PageObjects.LoginPage;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceStaffBase extends CommonUtilities {

	public WebDriver driver;
	public LoginPage loginPage;

	
	public ExtentTest test;
	@SuppressWarnings("unused")
	public static ThreadLocal<ExtentTest> testThread = new <ExtentTest>ThreadLocal<ExtentTest>();

	// Method to initialize WebDriver based on properties
	public WebDriver initializeDriver() {
		Properties properties = loadPropertiesFile("servicestaffpanel.properties");
		String browserName = properties.getProperty("browserValue");
		System.out.println(browserName);

		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			WebDriverManager.chromedriver().clearDriverCache().setup();
			WebDriverManager.chromedriver().clearResolutionCache().setup();
			driver = new ChromeDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			WebDriverManager.edgedriver().clearDriverCache().setup();
			WebDriverManager.edgedriver().clearResolutionCache().setup();
			driver = new EdgeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			WebDriverManager.firefoxdriver().clearDriverCache().setup();
			WebDriverManager.firefoxdriver().clearResolutionCache().setup();
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("Invalid browser value: " + browserName);
		}

		if (driver != null) {
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		}

		return driver;
	}

	// Method to load properties file
	private Properties loadPropertiesFile(String fileName) {
		Properties properties = new Properties();
		try (FileInputStream fileInputStream = new FileInputStream(
				new File(System.getProperty("user.dir") + "\\Resources\\" + fileName))) {
			properties.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	// Method to take a screenshot of the page
	public String takePageScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir") + "//Reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, destination);
		return destination.getAbsolutePath();
	}

	// Method to launch the application and return LoginPage object
	public LoginPage launchApplication() throws IOException {
		driver = initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.gotoURL();
		return loginPage;
	}

	// Method to delete previous failure screenshots
	public void deletePreviousFailureScreenshots(String currentTestName) {
		File reportsFolder = new File(System.getProperty("user.dir") + "//Reports//");
		if (reportsFolder.exists() && reportsFolder.isDirectory()) {
			for (File file : reportsFolder.listFiles()) {
				if (file.getName().startsWith(currentTestName) && file.getName().endsWith(".png")) {
					if (!file.delete()) {
						System.out.println("Failed to delete file: " + file.getName());
					}
				}
			}
		}
	}

	// Method to delete extra images
	public void deleteExtraImages() {
		File reportsFolder = new File(System.getProperty("user.dir") + "//Reports//");
		if (reportsFolder.exists() && reportsFolder.isDirectory()) {
			for (File file : reportsFolder.listFiles()) {
				if (!file.getName().equals("index.html")
						&& (file.getName().endsWith(".png") || file.getName().endsWith(".jpg"))) {
					String testName = file.getName().replaceFirst("[.][^.]+$", "");
					deletePreviousFailureScreenshots(testName);
				}
			}
		}
	}

	public void captureScreenshot(Scenario scenario) {

		try {
			Thread.sleep(1000);
			TakesScreenshot ts = (TakesScreenshot) driver;
			File srcFile = ts.getScreenshotAs(OutputType.FILE);
			File destDir = new File(System.getProperty("user.dir") + "//Reports");

			// Create directory if it doesn't exist
			if (!destDir.exists()) {
				destDir.mkdir();
			}

			File destFile = new File(destDir, scenario.getName() + ".png");
			if (destFile.exists()) {
				destFile.delete();
			}

			FileUtils.copyFile(srcFile, destFile);

			ExtentTest test = testThread.get();
			if (test != null) {
				// Add screenshot to Extent Report
				test.addScreenCaptureFromPath(destFile.getAbsolutePath());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
