package com.qa.libraries;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.relevantcodes.extentreports.LogStatus;

import testrunner.TestRunner;

public class ParamsUtility extends TestRunner{

	HashMap<String, String> params = new HashMap<String, String>();
	
	/**************************************************************
	* Purpose - To fetch the Query Parameters from the command line
	* Author - Santhosh Kumar
	* Date - 07/15/21
	* Modified by Name & Date - 
	***************************************************************/
	public HashMap<String, String> getQueryParameters() {
		
		String paramArray[]= {"date-min", "date-max", "dist-min", "dist-max", "h-min", "h-max", "v-inf-min", "v-inf-max", "v-rel-min", "v-rel-max", "class", "pha", "nea", "comet", "nea-comet", "neo", "kind", "spk", "des", "body", "sort", "limit", "diameter", "fullname"};
		List<String> paramList = Arrays.asList(paramArray);
		
		test.log(LogStatus.INFO, "Query Parameter Inputs / Conditions : ");
	    
		Properties prop = System.getProperties();
		for (String key : prop.stringPropertyNames()) {
			if (paramList.contains(key)) {
				if (prop.getProperty(key) != null) {
					params.put(key, prop.getProperty(key));
					test.log(LogStatus.INFO, key + " : " + prop.getProperty(key));
				}
			}
		}
		return params;
	}
}
