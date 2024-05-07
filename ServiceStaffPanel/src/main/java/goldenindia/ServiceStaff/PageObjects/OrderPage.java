package goldenindia.ServiceStaff.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import goldenindia.ServiceStaff.CommonUtilities.CommonUtilities;

public class OrderPage extends CommonUtilities {

	private WebDriver driver;

	// Web Elements
	@FindBy(css = "[class*=\"d-flex\"]")
	private List<WebElement> tableNumbers;

	@FindBy(css = "[class=\"my-3 row\"] [class=\"col-lg-6 col-md-6 col-sm-6 col-12\"] button")
	private List<WebElement> products;

	@FindBy(css = "[class=\"cart_boday card-body\"] [class=\"col-lg-6 col-md-6 col-sm-6 col-12\"]")
	private WebElement sentItemsButton;

	@FindBy(xpath = "//h2[@id='swal2-title']")
	private WebElement successMessageAfterSendingItems;

	@FindBy(css = "[class=\"cart_boday card-body\"] [class=\"Pay_CHF_Col col-lg-6 col-md-6 col-sm-6 col-12\"]")
	private WebElement payButton;

	@FindBy(css = "[class=\"text-start mx-1 row-hover row\"] [class=\"col\"]")
	private WebElement addCourseButton;

	@FindBy(css = "[class=\"list-group list-group-flush\"] button")
	private List<WebElement> menuCategories;

	@FindBy(css = "[class=\"my-3 row\"] [class=\"col-lg-6 col-md-6 col-sm-6 col-12\"]")
	private List<WebElement> categoryProducts;

	@FindBy(css = "[class*=\"course_name\"]")
	private List<WebElement> courses;

	@FindBy(xpath = "//div[@class=\"cart_boday card-body\"]//div[@class=\"col-lg-6 col-md-6 col-sm-6 col-12\"]/button")
	private WebElement fireCourseButton;

	@FindBy(css = "div[id='swal2-html-container'] div")
	private WebElement fireCourseMessage;

	// Constructor
	public OrderPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Method to retrieve table numbers
	public List<WebElement> getTableNumbers() {

		return tableNumbers;
	}

	// Method to add products to the cart
	public void addItemsToCart() throws InterruptedException {
		for (WebElement product : products) {
			System.out.println("Adding product: " + product.getText());
			product.click();
			Thread.sleep(1000);
		}
	}

	public void addingCourse() {
		addCourseButton.click();
	}

	// Method to click on the "Sent Items" button
	public void clickOnSentItemsButton() throws InterruptedException {
		sentItemsButton.click();
		Thread.sleep(2000);
	}

	// Method to get success message after sending items
	public WebElement getSuccessMessageAfterSendingItems() {
		return successMessageAfterSendingItems;
	}

	// Method to click on the "Pay" button and navigate to payment page
	public PaymentPage clickOnPayButton() {
		payButton.click();
		return new PaymentPage(driver);
	}

	public void addingTheProductsToCourse() throws InterruptedException {
		int coursesSize = courses.size();
		String courseProducts[][] = { { "Soft Drink 1", "Alcohol Drink 1" }, // Course 1
				{ "Lambsag Rice", "Poi-Aanam" }
				// Course 2
		};
		int count = 0;
		while (count < coursesSize) {
			courses.get(count).click(); // Click on the course

			for (int menuCategory = 0; menuCategory < menuCategories.size(); menuCategory++) {

				menuCategories.get(menuCategory).click(); // Click on the menu category

				int courseProductCount = courseProducts[count].length; // Get the number of products for the current
																		// course

				for (int i = 0; i < courseProductCount; i++) {
					String productToFind = courseProducts[count][i];

					for (int categoryProduct = 0; categoryProduct < categoryProducts.size(); categoryProduct++) {
						String currentProduct = categoryProducts.get(categoryProduct).getText();

						if (currentProduct.equals(productToFind)) {
							System.out.println("Matching product found: " + currentProduct); // Debug statement

							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							categoryProducts.get(categoryProduct).click();
							break;
						}
					}
				}
			}

			addCourseButton.click();
			count++;
		}

		System.out.println("I'm Outside");

	}

	public void firingCourse() throws InterruptedException {

		int count = 0;
		while (fireCourseButton.getText().contains("Fire")) {

			Thread.sleep(2000);
			fireCourseButton.click();
			count += 1;
			if (count > 0) {
				break;
			}
		}

	}

	public String gettingFireCourseMessage() {
		return fireCourseMessage.getText();
	}

	public String gettingFireCourseSequence() {
		String arr[] = fireCourseButton.getText().split("Course");
		return arr[1].trim();
	}
	
	
	
}
