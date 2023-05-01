package org.bdd.utilities;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigProvider {

    private static Properties properties;
    private static final Logger Log = LogManager.getLogger();

    private static Properties getInstance() {
        if (properties == null) {
            properties = loadProperties();
        }
        return properties;
    }

    /**
     * @return loaded properties
     */
    private static Properties loadProperties() {
        try {
            properties = new Properties();
//			ClassLoader loader = Thread.currentThread().getContextClassLoader();
//			InputStream inputStream = loader.getClass().getResourceAsStream("/properties/config.properties");
            String propertiesPath = System.getProperty("user.dir") + "/src/test/resources/properties/config.properties";
            FileInputStream inputStream = new FileInputStream(propertiesPath);
            properties.load(inputStream);
            Log.info("Loaded properties file... src/test/resources/properties/config.properties");
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * Use for to access string value from property file
     *
     * @param key- value from the property file
     * @return String Value
     */
    public static String getAsString(String key) {
        return getInstance().getProperty(key).trim();
    }

    /**
     * Use to access int value from property file
     * * @param key- value from the property file
     *
     * @return integer converted from a string
     */
    public static int getAsInteger(String key) {
        return Integer.parseInt(getInstance().getProperty(key).trim());
    }
}