package com.shivashish.test;

import com.google.gson.Gson;
import com.shivashish.apis.weatherapis.GetCurrentWeather;
import com.shivashish.helper.weatherapis.CurrentWeather;
import com.shivashish.utils.commonutils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class TestCurrentWeatherAPI {

	private final static Logger logger = LoggerFactory.getLogger(TestCurrentWeatherAPI.class);
	GetCurrentWeather getCurrentWeather;
	SoftAssert softAssert;


	@BeforeClass
	public void beforeClass() {
		logger.debug("In Before Class method");
		getCurrentWeather = new GetCurrentWeather();
	}


	@BeforeMethod
	public void beforeMethod(Method method) {
		logger.debug("In Before Method");
		logger.debug("method name is: {} ", method.getName());
		logger.debug("***********************************************************************************************************************");

	}


	@DataProvider(name = "CityNames", parallel = false)
	public Object[][] getCityName() {

		List<String> cities = Arrays.asList("Delhi", "Mumbai", "Calcutta" , "Chennai");

		Object[][] cityNames = new Object[cities.size()][];

		int i = 0;
		for (String city : cities) {
			cityNames[i] = new String[1];
			cityNames[i][0] = city;
			i++;
		}

		return cityNames;

	}

	@Test(dataProvider = "CityNames")
	public void testCurrentWeatherInCity(String cityName) {

		softAssert = new SoftAssert();
		getCurrentWeather.getCurrentWeatherOfCity(cityName);
		softAssert.assertTrue(getCurrentWeather.getStatusCode() == 200, "Get Current Weather API Repsonse Code is Incorrect");
		softAssert.assertTrue(JsonUtils.isJSONValid(getCurrentWeather.getApiResponse()), "Get Current Weather API Repsonse is Not Valid Json");

		Gson gson = new Gson();
		CurrentWeather currentWeatherInCity = gson.fromJson(getCurrentWeather.getApiResponse(), CurrentWeather.class);
		softAssert.assertTrue(currentWeatherInCity.getName().toLowerCase().contentEquals(cityName.toLowerCase()), "Incorrect City Detail in Json Response : Expected" +
				": " +  cityName.toLowerCase() + " but found : " + currentWeatherInCity.getName().toLowerCase());
		softAssert.assertAll();

	}

	@AfterMethod
	public void afterMethod(ITestResult result) {

		logger.debug("In After Method");
		logger.debug("method name is: {}", result.getMethod().getMethodName());
		logger.debug("***********************************************************************************************************************");
	}

	@AfterClass
	public void afterClass() {
		logger.debug("In After Class method");
	}

}
