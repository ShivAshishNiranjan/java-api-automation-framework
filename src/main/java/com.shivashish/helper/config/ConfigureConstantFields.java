package com.shivashish.helper.config;

import com.shivashish.utils.commonutils.ConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class  ConfigureConstantFields {
	private final static Logger logger = LoggerFactory.getLogger(ConfigureConstantFields.class);
	private static Map<String, String> constantFields = new HashMap<String, String>();
	private static Map<String, String> configFilesMap = new HashMap<String, String>();
	private static String resourceFolderPath;

	public static String getConfigFilesValue(String key) {
		return configFilesMap.get(key.toLowerCase());
	}


	public static String getConstantFieldsValue(String key) {
		return constantFields.get(key.toLowerCase());
	}

	// Getter Functions for Config Files Path and Names
	public static String getConfigFilesPath() {
		return resourceFolderPath + "/" + getConfigFilesValue("commonconfigfilepath");
	}


	public static String getInstaConfigFileName() {
		return getConfigFilesValue("instaconfigfilename");
	}

	@BeforeSuite
	public void configureConstantFieldsProperties() {
		try {
			logger.info("Configuring Constant Fields Across All Suit");
			String baseFileName = "base.cfg";

			File file = new File(ConfigureConstantFields.class.getClassLoader().getResource(baseFileName).getFile());
			resourceFolderPath = file.getParentFile().getAbsolutePath();

			logger.info("resourceFolder Path  : [{}]", resourceFolderPath);

			constantFields = ConfigReader.getAllDefaultProperties(resourceFolderPath, baseFileName);
			configFilesMap = ConfigReader.getAllConstantProperties(resourceFolderPath, baseFileName, "config files detail");

		} catch (Exception e) {
			logger.error("Exception while doing ConstantField configuration {}", e.getMessage());
		}
	}

}
