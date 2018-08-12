package utils.commonutils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class APIUtils {

	private final static Logger logger = LoggerFactory.getLogger(APIUtils.class);
	static String apiResponseTime;
	static String apiStatusCode;


	public static Boolean validJsonResponse(String strResponse) {
		return validJsonResponse(strResponse, "");
	}

	public static Boolean validJsonResponse(String strResponse, String additionalInfo) {
		// To check if the response is valid JSON or HTML?
		Boolean isValidJson = false;
		String responseAsString = strResponse;
		try {
			Object response = new JSONTokener(responseAsString).nextValue();

			if ((response instanceof JSONObject) || (response instanceof JSONArray)) {
				isValidJson = true;
			}
		} catch (Exception e) {
			logger.error("Not a valid JSON response {}", additionalInfo);
		}
		return isValidJson;
	}



}
