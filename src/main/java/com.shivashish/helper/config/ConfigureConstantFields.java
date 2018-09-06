package com.shivashish.helper.config;

import com.shivashish.utils.commonutils.ConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;

import java.util.HashMap;
import java.util.Map;

public class ConfigureConstantFields {
	private final static Logger logger = LoggerFactory.getLogger(ConfigureConstantFields.class);
	private static Map<String, String> constantFields = new HashMap<String, String>();
	private static Map<String, String> configFilesMap = new HashMap<String, String>();

	public static String getConfigFilesValue(String key) {
		return configFilesMap.get(key.toLowerCase());
	}


	public static String getConstantFieldsValue(String key) {
		return constantFields.get(key.toLowerCase());
	}

	@BeforeSuite
	public void configureConstantFieldsProperties() {
		try {
			logger.info("Configuring Constant Fields Across All Suit");
			String baseFilePath = "src//main//resources";
			String baseFileName = "base.cfg";

			constantFields = ConfigReader.getAllDefaultProperties(baseFilePath, baseFileName);
			configFilesMap = ConfigReader.getAllConstantProperties(baseFilePath, baseFileName, "config files detail");

		} catch (Exception e) {
			logger.error("Exception while doing ConstantField configuration {}", e.getMessage());
		}
	}

}
