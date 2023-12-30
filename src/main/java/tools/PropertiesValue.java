package tools;

import java.time.Duration;

public class PropertiesValue {
    public static String getPropertiesValue(String propertiesValue) {
        return PropertiesManager.getProperties().getProperty(propertiesValue);
    }

    public static boolean isDemoVersionContext() {
        String demoVersionPropertyValue = PropertiesManager.getProperties().getProperty("demoVersion");
        return demoVersionPropertyValue != null && !demoVersionPropertyValue.isEmpty();
    }

    public static Duration getWaitForDriver() {
        return Duration.ofSeconds(Integer.parseInt(PropertiesManager.getProperties().getProperty("driverWaitSeconds")));
    }

    public static String getWeatherstackBaseUrl() {
        return PropertiesManager.getProperties().getProperty("weatherstackBaseUrl");
    }

    public static String getAccessKey() {
        return PropertiesManager.getProperties().getProperty("access_key");
    }

    public static String getWeatherstackCurrentWeatherApiUrl() {
        return PropertiesManager.getProperties().getProperty("weatherstackCurrentWeatherApiUrl");
    }

    public static String getWeatherstackForecast() {
        return PropertiesManager.getProperties().getProperty("weatherstackForecast");
    }
}
