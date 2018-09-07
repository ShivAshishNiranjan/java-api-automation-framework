package com.shivashish.test;

import com.google.gson.Gson;
import com.shivashish.apis.weatherapis.RegisterWeatherStation;
import com.shivashish.helper.weatherapis.RegisterWeatherStationResponse;
import com.shivashish.utils.commonutils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;

public class TestRegisterWeatherStation {
	private final static Logger logger = LoggerFactory.getLogger(TestRegisterWeatherStation.class);
	RegisterWeatherStation registerStation;
	SoftAssert softAssert;


	@BeforeClass
	public void beforeClass() {
		logger.debug("In Before Class method");
		registerStation = new RegisterWeatherStation();
	}


	@BeforeMethod
	public void beforeMethod(Method method) {
		logger.debug("In Before Method");
		logger.debug("method name is: {} ", method.getName());
		softAssert = new SoftAssert();
		logger.debug("***********************************************************************************************************************");

	}


	@Test
	public void testRegisterWeatherStation() {


		Gson gson = new Gson();
		String payload = "{\n" +
				"    \"external_id\": \"SF_TEST001\",\n" +
				"    \"name\": \"San Francisco Test Station\",\n" +
				"    \"latitude\": 37.76,\n" +
				"    \"longitude\": -122.43,\n" +
				"    \"altitude\": 150\n" +
				"}\n";
		registerStation.registerStation(payload);
		softAssert.assertTrue(registerStation.getStatusCode() == 201, "Register Weather Station API Response Code is Incorrect");

		boolean isAPIResponseIsJson = JsonUtils.isJSONValid(registerStation.getApiResponse());
		softAssert.assertTrue(isAPIResponseIsJson, "Register Weather Station API Response is Not Valid Json");


		if (isAPIResponseIsJson) {
			RegisterWeatherStationResponse registerWeatherStationResponse = gson.fromJson(registerStation.getApiResponse(), RegisterWeatherStationResponse.class);
			softAssert.assertTrue(registerWeatherStationResponse.getExternalId().contentEquals("SF_TEST001"), "Incorrect external_id Detail in Json Response : Expected" +
					": " + "SF_TEST001" + " but found : " + registerWeatherStationResponse.getExternalId());
			softAssert.assertTrue(registerWeatherStationResponse.getID() != null &&
							! registerWeatherStationResponse.getID().isEmpty() ,"New Station Id is Not Getting when hitting Register Weather Station API  ");
		}


	}

	@AfterMethod
	public void afterMethod(ITestResult result) {

		softAssert.assertAll();
		logger.debug("In After Method");
		logger.debug("method name is: {}", result.getMethod().getMethodName());
		logger.debug("***********************************************************************************************************************");
	}

	@AfterClass
	public void afterClass() {
		logger.debug("In After Class method");
	}
}
