package goldenindia.ServiceStaff.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import io.github.bonigarcia.wdm.WebDriverManager;
import goldenindia.ServiceStaff.PageObjects.LoginPage;

public class servicestaffbase {

	public WebDriver driver;
	public LoginPage lpage;

	// Method to initialize WebDriver based on properties
	public WebDriver initializeDriver() {
		Properties properties = new Properties();
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(
					new File(System.getProperty("user.dir") + "\\Resources\\servicestaffpanel.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			properties.load(fileInputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String browserName = properties.getProperty("browserValue");
		System.out.println(browserName);
		if (browserName.contains("chrome")) {

			WebDriverManager.chromedriver().setup();
			 
				driver = new ChromeDriver();
				driver.manage().deleteAllCookies();
			
		} else if (browserName.contains("edge")) {

			WebDriverManager.edgedriver().setup();
			 
				driver = new EdgeDriver();
				driver.manage().deleteAllCookies();
			
		} else if (browserName.contains("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			 
				driver = new FirefoxDriver();
				driver.manage().deleteAllCookies();
			
		} else {
			System.out.println("Invalid browser value: " + browserName);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		return driver;
	}

	public void deleteExtraImages() {
		String reportsFolderPath = System.getProperty("user.dir") + "//Reports//";
		File reportsFolder = new File(reportsFolderPath);

		if (reportsFolder.exists() && reportsFolder.isDirectory()) {
			File[] files = reportsFolder.listFiles();

			// Delete all files except the latest failure screenshot for each test
			for (File file : files) {
				if (!file.getName().equals("index.html")
						&& (file.getName().endsWith(".png") || file.getName().endsWith(".jpg"))) {
					String testName = file.getName().replaceFirst("[.][^.]+$", ""); // Remove file extension
					deletePreviousFailureScreenshots(testName);
				}
			}
		}
	}

	public void deletePreviousFailureScreenshots(String currentTestName) {
		String reportsFolderPath = System.getProperty("user.dir") + "//Reports//";
		File reportsFolder = new File(reportsFolderPath);

		if (reportsFolder.exists() && reportsFolder.isDirectory()) {
			File[] files = reportsFolder.listFiles();

			// Delete previous failure screenshots for the current test
			for (File file : files) {
				if (file.getName().startsWith(currentTestName) && file.getName().endsWith(".png")) {
					if (!file.delete()) {
						System.out.println("Failed to delete file: " + file.getName());
					}
				}
			}
		}
	}

	public String takingPageScreenShot(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//Reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//Reports//" + testCaseName + ".png";
	}

	// Method to launch the application and return LoginPage object
	public LoginPage launchApplication() throws IOException {
		driver = initializeDriver();
		lpage = new LoginPage(driver);
		lpage.gotoURL();
		return lpage;
	}

	// Method to close the browser
	public void tearDown() throws Exception {
		driver.quit();
	}
}
