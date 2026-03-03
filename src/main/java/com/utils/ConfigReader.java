package com.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties;

    private ConfigReader() {}

    static {
        try {
            FileInputStream fis = new FileInputStream(
                    "src/test/resources/config/config.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
    }
}
