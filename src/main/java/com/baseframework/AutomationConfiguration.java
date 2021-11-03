package com.baseframework;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AutomationConfiguration {

    private static AutomationConfiguration globalConfiguration = null;

    private Properties properties = new Properties();

    protected AutomationConfiguration() {

        try {
            loadAllProperties();
        } catch (FileNotFoundException e) {
            e.getLocalizedMessage();
        }
    }

    public static AutomationConfiguration globalConfiguration() {
        if (globalConfiguration == null) {
            globalConfiguration = new AutomationConfiguration();
        }
        return globalConfiguration;
    }

    public static String getConfigurationValueForProperty(String propertyName) {
        return AutomationConfiguration.globalConfiguration().readConfigurationProperty(propertyName);
    }

    private void loadAllProperties() throws FileNotFoundException {
        properties = new Properties();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");

        if (inputStream != null) {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                throw new FileNotFoundException("Property file 'config.properties' not found in the classpath");
            }
        }
    }

    private String readConfigurationProperty(String propertyName) {
        String defaultValue = "";
        return properties.getProperty(propertyName, defaultValue);
    }
}