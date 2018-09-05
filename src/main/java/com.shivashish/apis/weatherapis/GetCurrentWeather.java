package com.shivashish.apis.weatherapis;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetCurrentWeather {

	private final static Logger logger = LoggerFactory.getLogger(GetCurrentWeather.class);
	public int statusCode;
	private String apiResponse;

	public String getApiResponse() {
		return apiResponse;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void getCurrentWeatherOfCity(String city) {
		RestAssured.baseURI = "http://api.openweathermap.org/data/2.5";

		RequestSpecification httpRequest = RestAssured.given();

		httpRequest.param("q", city).param("appid", "2552b9adeb625469c78933edb12f574c");
		Response response = httpRequest.request(Method.GET, "/weather");

		apiResponse = response.getBody().asString();

		statusCode = response.getStatusCode();
		logger.debug("Response Body is => {} ", apiResponse);
		logger.debug("Response Code is => {} ", statusCode);


	}


}
