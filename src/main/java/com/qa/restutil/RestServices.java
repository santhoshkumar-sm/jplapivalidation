package com.qa.restutil;

import io.restassured.response.Response;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import testrunner.TestRunner;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

public class RestServices extends TestRunner{

	private final String BASE_URL = "https://ssd-api.jpl.nasa.gov/cad.api";
	private final String API_NAME = "NASA/JPL SBDB Close Approach Data API";
	/***********************************************************************
	* Purpose - To validate the API with the given URL & Query Parameters
	* Author - Santhosh Kumar
	* Date - 07/15/21
	* Modified by Name & Date - 
	************************************************************************/
	public void prepareandValidateAPI(HashMap<String, String> paramsMap) {
		
		RestAssured.baseURI = BASE_URL;
		RequestSpecification req = RestAssured.given();
		
		for (Map.Entry mapElement : paramsMap.entrySet()) {
            
			String key = (String)mapElement.getKey();
			String value = (String)mapElement.getValue();
			req.queryParam(key, value);
        }
		
		test.log(LogStatus.INFO, "base URI :" +RestAssured.baseURI);
		Response response = req.get();
		int statCode = response.getStatusCode();
		System.out.println("Status Code : "+statCode );
		
		switch (statCode) {
		case 200:
			test.log(LogStatus.PASS, "Response : OK ");
			
			JsonPath jsonPathEvaluator = response.jsonPath();
			String count = jsonPathEvaluator.get("count");
			test.log(LogStatus.PASS, "Total No of Asteroids / Comets :" +count);
			
			Map<String, String> apiParent = response.jsonPath().getMap("signature");
			String apiName = apiParent.get("source");
			if(API_NAME.equalsIgnoreCase(apiName))
			test.log(LogStatus.PASS, "Name of the Source API : " +apiName);
			test.log(LogStatus.PASS, "Response : " +response.asString());
			test.log(LogStatus.INFO, "Time Taken to fetch the response (in MilliSeconds) : " +response.getTimeIn(TimeUnit.MILLISECONDS));
			break;
			
		case 400:
			test.log(LogStatus.FAIL, "Response : Bad Request " +response.getBody().asString());
			getStatusDetails(response);
			break;
			
		case 405:
			test.log(LogStatus.FAIL, "Response : Method Not Allowed" +response.getBody().asString());
			getStatusDetails(response);
			break;
			
		case 500:
			test.log(LogStatus.FAIL, "Response : Internal Server Error" +response.getBody().asString());
			getStatusDetails(response);
			break;
			
		case 503:
			test.log(LogStatus.FAIL, "Response : Service Unavailable" +response.getBody().asString());
			getStatusDetails(response);
			break;
		}
		
	}
	
	/**************************************************************************************
	* Purpose - To Log the Message and Status Code into the Extent Report incase of failure
	* Author - Santhosh Kumar
	* Date - 07/15/21
	* Modified by Name & Date - 
	***************************************************************************************/
	public void getStatusDetails(Response response) {
		
		JSONObject JSONResponseBody = new JSONObject(response.body().asString());
		String errMsg = JSONResponseBody.getString("message");
		String cd = JSONResponseBody.getString("code");
		test.log(LogStatus.INFO, "Status Code : " +cd+" Error Message : " +errMsg);
	}
}
