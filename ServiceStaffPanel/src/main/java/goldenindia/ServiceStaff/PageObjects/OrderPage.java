package goldenindia.ServiceStaff.PageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

	@FindBy(css = "[class*=\"course_name\"] span")
	private List<WebElement> courseEditButtons;

	@FindBy(css = "[class=\"modal-content\"] [class*=\"MuiButtonBase-root\"]")
	private List<WebElement> coursePopUpActions;

	@FindBy(id = "radio-1")
	private WebElement firstCourse;

	@FindBy(xpath = "//button[@type=\"submit\"]")
	private WebElement transferConfirmationButton;

	@FindBy(css = "alt=\"editCourse\"")
	private List <WebElement> belowCourseEditButton;

	@FindBy(css = "[class=\"modal-content\"] [type=\"button\"]")
	private List<WebElement> buttonsAfterClickingEditbutton;

	@FindBy(css="[class=\"mx-2 row\"]")
	private List<WebElement> courseNamesAfterSending;
	
	
	// Constructor
	public OrderPage(WebDriver driver) {
		// super(driver);
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
				{ "Lambsag Rice", "Poi-Aanam" } // Course 2
		};
		int count = 0;
		while (count < coursesSize) {
			courses.get(count).click(); // Click on the course
			for (int menuCategory = 0; menuCategory < menuCategories.size(); menuCategory++) {
				menuCategories.get(menuCategory).click(); // Click on the menu category
				int courseProductCount = courseProducts[count].length;
				for (int i = 0; i < courseProductCount; i++) {
					String productToFind = courseProducts[count][i];
					for (int categoryProduct = 0; categoryProduct < categoryProducts.size(); categoryProduct++) {
						String currentProduct = categoryProducts.get(categoryProduct).getText();
						if (currentProduct.equals(productToFind)) {
							System.out.println("Matching product found: " + currentProduct);
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

	public List<WebElement> gettingCoursesNames() {
		for (WebElement courseName : courses) {
			System.out.println(courseName.getText());
		}
		return courses;
	}

	public List<WebElement> clickingOntheEditButton() {
		return courseEditButtons;
	}

	public void clearingTheItemsInCourse() {
		for (WebElement courseActions : coursePopUpActions) {
			System.out.println(courseActions.getText());
			if (courseActions.getText().trim().contains("Clear All Items")) {
				courseActions.click();
			}
		}

	}

	public void transferTheCourseToAnotherCourse() throws InterruptedException {

		List<Integer> firstCount = new ArrayList<>();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> courses = wait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".course_name")));

		System.out.println("Printing the products according to the courses");
		int count = 0;
		for (WebElement course : courses) {
			// Ensure the course element is visible and interactable
			wait.until(ExpectedConditions.visibilityOf(course));
			wait.until(ExpectedConditions.elementToBeClickable(course));

			// Print the course name
			System.out.println("Course: " + course.getText().trim());

			// Find the products associated with the current course
			List<WebElement> products = course
					.findElements(By.xpath("following-sibling::div[contains(@class, 'product_item_name')]"));

			// Debugging print to check if products are found
			System.out.println("Number of products found: " + products.size());
			for (int i = 0; i < courses.size(); i++) {
				if (count == i) {
					firstCount.add(products.size());
				}
			}

			for (WebElement product : products) {
				System.out.println("\t" + product.getText().trim());
			}
			count++;
		}

		System.out.println("Elements in the firstCount are : ");
		for (int i = 0; i < firstCount.size(); i++) {
			System.out.println(firstCount.get(i));
		}
		for (WebElement courseActions : coursePopUpActions) {
			System.out.println(courseActions.getText());
			if (courseActions.getText().trim().contains("Transfer")) {
				courseActions.click();
			}
		}
		firstCourse.click();
		Thread.sleep(1000);
		transferConfirmationButton.click();
	}

	public void clickingOntheReprintOption(String courseName) {

		
	}

}
