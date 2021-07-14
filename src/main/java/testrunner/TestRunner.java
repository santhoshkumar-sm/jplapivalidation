package testrunner;

import io.cucumber.testng.CucumberOptions;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		 plugin = {"pretty","html:target/cucumber-reports/cucumber-pretty","json:target/cucumber-reports/CucumberTestReport.json","rerun:target/cucumber-reports/rerun.txt"}, 
		 glue = "stepdefinitions", 
	     features = "features", 
	     tags="@regression",
//	     dryRun = true,
	     monochrome = true
		)


public class TestRunner extends AbstractTestNGCucumberTests {
	protected static ExtentTest test;
	static ExtentReports report;

	@BeforeSuite
	public static void startTest() {
		report = new ExtentReports(System.getProperty("user.dir") + "\\" + "ExtentReportResults.html");
		report.addSystemInfo("User Name", "Santhosh");
		test = report.startTest("Data API Validation Demo");
		test.log(LogStatus.PASS, "Extent Report Created");
	}

//	@Test
//	public void test() {
//		test.log(LogStatus.PASS, "From Test Runner Class");
//	}

	@AfterSuite
	public static void endTest() {
		test.log(LogStatus.PASS, "Test ended : Report Flushed");
		report.endTest(test);
		report.flush();
	}

}