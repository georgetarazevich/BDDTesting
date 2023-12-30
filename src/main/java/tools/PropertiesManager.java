package tools;

import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Set;

public class PropertiesManager {
    private static final String configFileLocal = "src/test/resources/appLocal.properties";
    private static final String configFile = "src/test/resources/app.properties";
    private static final Properties properties;
    private static Logger logger = Logger.getLogger(PropertiesManager.class);

    static {
        properties = new Properties();
        Properties globalProperties = new Properties();
        Properties localProperties = new Properties();

        logger.info("ConfigFile:  " + configFile + "\n");
        logger.info("\n Full path: user.dir + configFile:  " + System.getProperty("user.dir") + "\\"
                + configFile);
        logger.info("Checking existence of configFileLocal...  " + "\n");
        boolean existsPropertiesLocal = Files.exists(Paths.get(configFileLocal));

        logger.info("ConfigFileLocal exist: " + existsPropertiesLocal + "\n");

        try {
            if (existsPropertiesLocal) {
                localProperties.load(new FileReader(configFileLocal));
                globalProperties.load(new FileReader(configFile));

                properties.putAll(globalProperties);
                properties.putAll(localProperties);
            } else {
                properties.load(new FileReader(configFile));
            }
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }

        for (String key : properties.stringPropertyNames()) {
            if (System.getProperty(key) != null) {
                String value = System.getProperty(key);
                logger.info("Overriding property " + key + " with value passed to Java directly: " + value);
                properties.setProperty(key, value);
            }
        }

        logger.info("Properties info: ... " + "\n");

        Set<String> keys = properties.stringPropertyNames();
        for (String key : keys) {
            logger.info("Key:Value - " + key + " : " + properties.getProperty(key));
        }
        logger.info("\n\nGoing ahead ... \n");
    }

    public static Properties getProperties() {
        return properties;
    }
}
