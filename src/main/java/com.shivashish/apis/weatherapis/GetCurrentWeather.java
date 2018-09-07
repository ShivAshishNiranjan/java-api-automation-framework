package com.shivashish.apis.weatherapis;

import com.shivashish.helper.config.ConfigureConstantFields;
import com.shivashish.utils.apiutils.APIMaster;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetCurrentWeather extends APIMaster {

	private final static Logger logger = LoggerFactory.getLogger(GetCurrentWeather.class);

	public void getCurrentWeatherOfCity(String city) {
		RestAssured.baseURI = ConfigureConstantFields.getConstantFieldsValue("baseuri");

		RequestSpecification httpRequest = RestAssured.given();

		httpRequest.param("q", city).param("appid", ConfigureConstantFields.getConstantFieldsValue("appid"));
		Response response = httpRequest.request(Method.GET, "/weather");

		apiResponse = response.getBody().asString();

		statusCode = response.getStatusCode();
		logger.info("Response Body is => {} ", apiResponse);
		logger.debug("Response Code is => {} ", statusCode);


	}


}
