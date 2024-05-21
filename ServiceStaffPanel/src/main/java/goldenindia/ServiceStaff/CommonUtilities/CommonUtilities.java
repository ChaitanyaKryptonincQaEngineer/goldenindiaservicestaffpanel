package goldenindia.ServiceStaff.CommonUtilities;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtilities {

	public WebDriver driver;

	public Actions movingToWebElementUsingActionClass(WebElement ele) {
		Actions action = new Actions(driver);
		return action.moveToElement(ele);
	}

	public void waitTillVisibilityOfWebElement(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void scrollToElementUsingJavascriptExecutor(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
	}

	

}
