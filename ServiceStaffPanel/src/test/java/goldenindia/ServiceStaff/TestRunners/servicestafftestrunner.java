package goldenindia.ServiceStaff.TestRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src\\test\\java\\goldenindia\\ServiceStaff\\Features\\servicestaffpanel.feature", 
glue = "goldenindia.ServiceStaff.StepDefinitions", monochrome = true, dryRun = false, tags = "@ClearItemsOfCourse", 
plugin = {
		"pretty", "html:target/cucumber.html" })
public class servicestafftestrunner extends AbstractTestNGCucumberTests {

	
}
