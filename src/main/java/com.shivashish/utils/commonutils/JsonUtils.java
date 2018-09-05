package com.shivashish.utils.commonutils;

import org.json.*;

public class JsonUtils {


	public static boolean isJSONValid(String str) {
		try {
			new JSONObject(str);
		} catch (JSONException ex) {
			try {
				new JSONArray(str);
			} catch (JSONException ex1) {
				return false;
			}
		}
		return true;
	}


}
