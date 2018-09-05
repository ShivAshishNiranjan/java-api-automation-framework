package com.shivashish.test;

import com.shivashish.apis.weatherapis.GetCurrentWeather;
import com.shivashish.utils.commonutils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;

public class TestCurrentWeatherAPI {

	private final static Logger logger = LoggerFactory.getLogger(TestCurrentWeatherAPI.class);
	GetCurrentWeather getCurrentWeather;
	SoftAssert softAssert ;


	@BeforeClass
	public void beforeClass() {
		logger.info("In Before Class method");
		getCurrentWeather = new GetCurrentWeather();
	}


	@BeforeMethod
	public void beforeMethod(Method method) {
		logger.debug("In Before Method");
		logger.debug("method name is: {} ", method.getName());
		softAssert = new SoftAssert();
		logger.debug("***********************************************************************************************************************");

	}


	@Test
	public void TestCurrentWeatherInCity() {

		getCurrentWeather.getCurrentWeatherOfCity("London");
		softAssert.assertTrue(getCurrentWeather.getStatusCode()==200,"Get Current Weather API Repsonse Code is Incorrect");
		softAssert.assertTrue(JsonUtils.isJSONValid(getCurrentWeather.getApiResponse()) ,"Get Current Weather API Repsonse is Not Valid Json");




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
