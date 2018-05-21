package utils.commonutils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class APIUtils {

	private final static Logger logger = LoggerFactory.getLogger(APIUtils.class);
	static String apiResponseTime;
	String apiStatusCode;


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

	public static Boolean isApplicationErrorInResponse(String strResponse) {

		Boolean isApplicationError = false;
		try {
			JSONObject jObj = new JSONObject(strResponse);
			if (jObj.has("header")) {
				JSONObject jsonResponseData = jObj.getJSONObject("header").getJSONObject("response");
				if (jsonResponseData.get("status").toString().toLowerCase().equals("applicationerror")) {
					isApplicationError = true;
					logger.info("Error Message in response {}", jsonResponseData.get("status").toString());
				}

			}

		} catch (Exception e) {
			logger.error("Exception while Checking ApplicationError in response : {}", strResponse);
		}
		return isApplicationError;
	}

	public static boolean isPermissionDeniedInResponse(String jsonResponseStr) {
		boolean isPermissionDenied = false;
		JSONObject responseObj = new JSONObject(jsonResponseStr);

		if (responseObj.has("errorMessage") && responseObj.getString("errorMessage").contains("do not have access")) {
			isPermissionDenied = true;
		}
		if (responseObj.has("header")) {
			JSONObject jsonResponseData = responseObj.getJSONObject("header").getJSONObject("response");
			if (jsonResponseData.get("status").toString().toLowerCase().equals("applicationerror")
					&&
					(jsonResponseData.has("errorMessage") && jsonResponseData.getString("errorMessage").contains("do not have access")) || jsonResponseData.has("errorMessage") && jsonResponseData.getString("errorMessage").toLowerCase().trim().
					contains("either you do not have the required permissions or requested page does not exist anymore")) {
				isPermissionDenied = true;
				logger.error("Error Message in response {}", jsonResponseData.get("status").toString());
			}

		}
		return isPermissionDenied;
	}

}
