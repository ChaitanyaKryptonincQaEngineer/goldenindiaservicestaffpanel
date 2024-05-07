package goldenindia.ServiceStaff.PageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import goldenindia.ServiceStaff.CommonUtilities.CommonUtilities;

public class PaymentPage extends CommonUtilities {

	private WebDriver driver;

	@FindBy(css = "[class=\"text-center mb-3 col-lg-4 col-md-4 col-sm-4 col-6\"] button")
	private List<WebElement> paymentOptions;

	@FindBy(css = "[class=\"col-lg-4 col-md-4 col-sm-4 col-4\"]")
	private List<WebElement> numericalEntries;

	@FindBy(css = "[class=\"text-end price col-lg-6 col-md-6 col-sm-6 col-6\"]")
	private WebElement orderAmount;

	@FindBy(css = "div[id='swal2-html-container'] div")
	private WebElement paymentCompletionMessage;

	@FindBy(xpath = "(//div[@class=\"mt-4 col-lg-6 col-md-6 col-sm-6 col-12\"]/button)[2]")
	private WebElement paymentCompletingButton;

	public PaymentPage(WebDriver driver) {
		this.setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	// Method to retrieve payment completion message
	public WebElement paymentCompletion() {
		paymentCompletingButton.click();
		waitTillVisibilityOfWebElement(paymentCompletionMessage);
		return paymentCompletionMessage;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	// Other methods can be added as needed for interacting with the payment page
}
