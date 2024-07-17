package com.abhi.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	public static String rootPth = System.getProperty("user.dir");
	public static File filePath;
	public static FileInputStream fileInput;
	public static Properties prop;

	public static String getProperties(String getPropertyValue) {

		filePath = new File(rootPth + ".\\src\\test\\resources\\config.properties");
		prop = new Properties();
		try {
			fileInput = new FileInputStream(filePath);
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop.getProperty(getPropertyValue);
	}
}
