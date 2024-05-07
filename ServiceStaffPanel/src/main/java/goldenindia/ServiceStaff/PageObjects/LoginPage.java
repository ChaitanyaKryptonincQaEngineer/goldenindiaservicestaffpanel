	package goldenindia.ServiceStaff.PageObjects;
	
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;
	
	public class LoginPage {
	
		public WebDriver driver;
		
		// Locators
		@FindBy(css = "[type=\"number\"]")
		WebElement serviceLoginEmail;
	
		@FindBy(css = "[type=\"password\"]")
		WebElement servicePassword;
	
		@FindBy(css = "[type=\"submit\"]")
		WebElement loginButton;
	
		@FindBy(xpath = "(//button[@type=\"button\"])[2]")
		WebElement clockinButton;
	
		// Constructor
		public LoginPage(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
	
		// Method to click on the login button
		public void loggingtoTheServicestaffPanel(String staffID, String pinCode) {
		    serviceLoginEmail.sendKeys(staffID);
		    servicePassword.sendKeys(pinCode);
		    loginButton.click();
		}

	
		// Method to click on the clock in button and navigate to the order page
		public OrderPage clickOnClockInButton() {
			OrderPage orderPage = new OrderPage(driver);
			clockinButton.click();
			return orderPage;
		}
	
		// Method to navigate to the login page URL
		public void gotoURL() {
			driver.get("http://servicestaff.mypreview.xyz/");
		}
	}
