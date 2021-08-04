package com.test.until;

import com.test.constant.PropertyConstant;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ConfigurationUtil {
    private static ConfigurationUtil instance;
    private Properties prop = new Properties();

    private ConfigurationUtil(String configFile) throws IOException {
        FileInputStream inputStream = new FileInputStream(configFile);
        prop.load(inputStream);
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return prop.getProperty(key, defaultValue);
    }


    public static ConfigurationUtil getInstance() throws IOException {
        if (instance == null) {
            String configFile = PropertyConstant.PROPERTY_FILE;

            instance = new ConfigurationUtil(configFile);
        }
        return instance;
    }

    public boolean containsKey(String key) {
        return prop.containsKey(key);
    }

}