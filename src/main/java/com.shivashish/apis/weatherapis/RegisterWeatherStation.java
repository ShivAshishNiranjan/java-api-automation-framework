package com.shivashish.apis.weatherapis;

import com.shivashish.helper.config.ConfigureConstantFields;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterWeatherStation {


	private final static Logger logger = LoggerFactory.getLogger(RegisterWeatherStation.class);
	public int statusCode;
	private String apiResponse;
	String version = "3.0";

	public String getApiResponse() {
		return apiResponse;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void registerStation(String payload) {
		String baseURI = ConfigureConstantFields.getConstantFieldsValue("baseuri");
		RestAssured.baseURI = baseURI.substring(0,baseURI.lastIndexOf("/"))+"/"+version;

		RequestSpecification httpRequest = RestAssured.given();

		httpRequest.queryParam("appid", ConfigureConstantFields.getConstantFieldsValue("appid"));
		httpRequest.contentType("application/json").accept("application/json");
		httpRequest.body(payload);

		Response response = httpRequest.request(Method.POST, "/stations");


		apiResponse = response.getBody().asString();
		statusCode = response.getStatusCode();
		logger.debug("Response Body is => {} ", apiResponse);
		logger.debug("Response Code is => {} ", statusCode);


	}


}
