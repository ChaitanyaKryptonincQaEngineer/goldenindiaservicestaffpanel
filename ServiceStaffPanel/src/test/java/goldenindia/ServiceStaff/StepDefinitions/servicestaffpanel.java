package goldenindia.ServiceStaff.StepDefinitions;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import goldenindia.ServiceStaff.Base.servicestaffbase;
import goldenindia.ServiceStaff.PageObjects.OrderPage;
import goldenindia.ServiceStaff.PageObjects.PaymentPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class servicestaffpanel extends servicestaffbase {

	public OrderPage orPage;
	public PaymentPage payPage;
	SoftAssert softAssert = new SoftAssert();

	public servicestaffpanel(Scenario scenario) {
	}

	public servicestaffpanel() {

	}

	@Given("I launch the service staff application")
	public void i_launch_the_service_staff_application() throws IOException {
		lpage = launchApplication();
	}

	@And("^I am logged in to the service staff panel with (.*) and (.*)$")
	public void i_am_logged_in_to_the_service_staff_panel_with_and(String staffID, String pinCode) {
		if (lpage != null) {
			System.out.println("Staff ID: " + staffID);
			System.out.println("PIN Code: " + pinCode);
			lpage.loggingtoTheServicestaffPanel(staffID, pinCode);
		} else {
			System.out.println("Login Page object is null. Make sure it is initialized.");
		}
	}

	@Then("I clock in to the service staff panel")
	public void i_clocked_in_to_the_service_staff_panel() {
		orPage = lpage.clickOnClockInButton();

	}

	@Then("^I have selected a table (.*) to serve$")
	public void i_have_selected_a_table_to_serve(String string) throws InterruptedException {

		List<WebElement> tables = orPage.getTableNumbers();

		for (WebElement webElement : tables) {
			System.out.println(webElement.getText());
		}
		System.out.println("Feature table value is " + string);

		Thread.sleep(1000);

		tables.stream()
				// Check if the desired table number exists
				.filter(table -> table.getText().contains(string))
				// If the table number is found, perform any necessary actions
				.findFirst().ifPresentOrElse(table -> {
					// Table found, do nothing
					table.click();
				}, () -> {
					// Table not found, perform click action on Table 1
					tables.get(0).click();
				});

	}

	@When("I add items to the course")
	public void i_add_items_to_the_course() {

		try {
			orPage.addItemsToCart();
			;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@When("I send the order")
	public void i_send_the_order() {
		try {
			orPage.clickOnSentItemsButton();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("the items should be sent successfully")
	public void the_items_should_be_sent_successfully() {
		Assert.assertEquals(orPage.getSuccessMessageAfterSendingItems().getText(), "Order sent to kitchen.");
	}

	@Then("I proceed to payment")
	public void i_proceed_to_payment() {
		payPage = orPage.clickOnPayButton();

	}

	@Then("I complete the payment process")
	public void i_complete_the_payment_process() {
		System.out.println("You completing the payment");
		payPage.paymentCompletion();

	}

	@Then("I log out")
	public void i_log_out() {
		System.out.println("You are logging out");
	}

	@Then("I generate a daily summary by selecting the email option and printing")
	public void i_generate_a_daily_summary_by_selecting_the_email_option_and_printing() {
		System.out.println("You are clocking out and printing");
	}

	@Then("a pop-up should appear confirming the action")
	public void a_pop_up_should_appear_confirming_the_action() {
		System.out.println("A message will appear in the after confirmation");
	}

	@When("I add courses")
	public void i_add_courses() {
		System.out.println("Adding the courses");
		orPage.addingCourse();
	}

	@When("I add products to the courses")
	public void i_add_products_to_the_courses() throws InterruptedException {
		System.out.println("Adding the products");
		orPage.addingTheProductsToCourse();
	}

	@Then("I sent the items to the kitchen.")
	public void i_sent_the_items_to_the_kitchen() throws InterruptedException {
		orPage.clickOnSentItemsButton();
	}

	@When("I fire the courses")
	public void i_fire_the_courses() throws InterruptedException {
		orPage.firingCourse();
		System.out.println("Firing the courses");
	}

	@Then("I should receive an error message")
	public void i_should_receive_an_error_message() throws InterruptedException {
		String message = orPage.gettingFireCourseMessage();
		Assert.assertEquals(message, "Course " + orPage.gettingFireCourseSequence() + " has been fired successfully");
		System.out.println("You are receivng an error message");
	}

	@When("I edit a product")
	public void i_edit_a_product() {

		
	}

	@And("I choose to transfer to another course")
	public void i_choose_to_transfer_to_another_course() {
		
	}

	@And("I check whether product transfer to the another course or not")
	public void i_check_whether_product_transfer_to_the_another_course_or_not() {

		
	}

	@When("I add courses to the cart")
	public void i_add_courses_to_the_cart() {

	}

	@When("I transfer a course to another")
	public void i_transfer_a_course_to_another() {

	}

	@Then("I ensure all products are transferred")
	public void i_ensure_all_products_are_transferred() {

	}

	@Given("I edit the courses")
	public void i_edit_the_courses() {

	}

	@Then("I clear all items")
	public void i_clear_all_items() {

	}

	@Then("I ensure all products are removed")
	public void i_ensure_all_products_are_removed() {

	}

	@After
	public void tree_Down() throws InterruptedException, IOException {

		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File srcfile = ts.getScreenshotAs(OutputType.FILE);
			File destDir = new File(System.getProperty("user.dir") + "//servicestaffpanelscreenshots");

			// Create directory if it doesn't exist
			if (!destDir.exists()) {
				destDir.mkdir();
			}

			File destFile = new File(destDir, "screenshot.png");
			FileUtils.copyFile(srcfile, destFile);
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Before
	public void deletingScenarioFiles() {

	}

}
