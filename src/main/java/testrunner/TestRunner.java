package testrunner;

import io.cucumber.testng.CucumberOptions;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.cucumber.testng.AbstractTestNGCucumberTests;
/***************************************************
* 
* The class fetches the rules cucumber options and 
* starts executing the tests
* 
****************************************************/
@CucumberOptions(
		 plugin = {"pretty","html:target/cucumber-reports/cucumber-pretty","json:target/cucumber-reports/CucumberTestReport.json","rerun:target/cucumber-reports/rerun.txt"}, 
		 glue = "stepdefinitions", 
	     features = "features", 
	     tags="@apivalidation",
	     monochrome = true
		)

/******************************************************************************
* Purpose - To execute the Before & After Suite tests mentioned in TestNG class 
* Author - Santhosh Kumar
* Date - 07/15/21
* Modified by Name & Date - 
*******************************************************************************/

public class TestRunner extends AbstractTestNGCucumberTests {
	protected static ExtentTest test;
	static ExtentReports report;

	@BeforeSuite
	public static void startTest() {
		report = new ExtentReports(System.getProperty("user.dir") + "\\" + "ExtentReportResults.html");
		report.addSystemInfo("User Name", "Santhosh");
		test = report.startTest("Small-Body DataBase API Validation Demo");
		test.log(LogStatus.PASS, "Report Initialised");
	}


	@AfterSuite
	public static void endTest() {
		test.log(LogStatus.PASS, "Test ended : Report Flushed");
		report.endTest(test);
		report.flush();
	}

}