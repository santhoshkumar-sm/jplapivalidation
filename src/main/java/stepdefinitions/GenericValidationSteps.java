package stepdefinitions;

import com.qa.libraries.TestClass;
import com.relevantcodes.extentreports.LogStatus;

import io.cucumber.java.en.Given;
import testrunner.TestRunner;


public class GenericValidationSteps extends TestRunner{

	@Given("the script is on the API homepage")
	public void the_script_is_on_the_api_homepage() {
	    System.out.println("From Step Definition");
	    test.log(LogStatus.PASS, "From Step Definition");
	}
	
}
