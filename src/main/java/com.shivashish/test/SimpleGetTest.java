package com.shivashish.test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class SimpleGetTest {
	private final static Logger logger = LoggerFactory.getLogger(SimpleGetTest.class);

	@Test
	public void GetWeatherDetails()
	{

		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		RequestSpecification httpRequest = RestAssured.given();

		Response response = httpRequest.request(Method.GET, "/Hyderabad");

		String responseBody = response.getBody().asString();
		logger.info("Response Body is => {} " , responseBody);

	}

	@Test
	public void testCaseOne() {
		//Printing Id of the thread on using which test method got executed
		System.out.println("Test Case One with Thread Id:- "
				+ Thread.currentThread().getId());
	}

	@Test
	public void testCaseTwo() {
		////Printing Id of the thread on using which test method got executed
		System.out.println("Test Case two with Thread Id:- "
				+ Thread.currentThread().getId());
	}

}