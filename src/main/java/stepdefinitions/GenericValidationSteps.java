package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import testrunner.TestRunner;
import java.util.HashMap;
import com.qa.libraries.ParamsUtility;
import com.qa.restutil.RestServices;

/*******************************************************************************************************
* Purpose - This class is to call the respective methods and logic from libraries and utilities packages 
* Author - Santhosh Kumar
* Date - 07/15/21
* Modified by Name & Date - 
********************************************************************************************************/

public class GenericValidationSteps extends TestRunner{
	RestServices rs = new RestServices();
	ParamsUtility pu = new ParamsUtility();
	
	HashMap<String, String> paramsMap = new HashMap<String, String>();
	
	@Given("the query parameter from command line arguments")
	public void query_parameter_from_command_line_arguments() {
	    
		paramsMap = pu.getQueryParameters();
	}
	
	@Then("send GET http request and get the asteroids and comets list")
	public void i_set_get_employee_service_api_endpoint() {
		
	    rs.prepareandValidateAPI(paramsMap);
	}
}
