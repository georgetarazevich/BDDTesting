package tools;

public class PropertiesValue {
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
        return PropertiesManager.getProperties().getProperty("weatherstackForecastApiUrl");
    }

    public static String getCity() {
        return PropertiesManager.getProperties().getProperty("city");
    }
}
