import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;



public class SimplePostTest {
	private final static Logger logger = LoggerFactory.getLogger(SimplePostTest.class);

	@Test
	public void RegistrationSuccessful()
	{
		RestAssured.baseURI ="http://restapi.demoqa.com/customer";
		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "Virender12"); // Cast
		requestParams.put("LastName", "Singh12");
		requestParams.put("UserName", "sdimpleuser2dd201112");
		requestParams.put("Password", "password123");

		requestParams.put("Email",  "sample2ee26d912@gmail.com");
		request.body(requestParams.toString());
		Response response = request.post("/register");

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);

	}

}