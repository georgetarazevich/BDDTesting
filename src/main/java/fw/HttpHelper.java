package fw;

import fw.model.Query;
import fw.model.User;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import tools.PropertiesValue;

import java.io.IOException;

public class HttpHelper {

    private static final String WEATHERSTACK_BASEURL = PropertiesValue.getWeatherstackBaseUrl();
    private static final String WEATHERSTACK_CURRENTWEATHER_APIURL = PropertiesValue.getWeatherstackCurrentWeatherApiUrl();


    public static Connection.Response getWeatherstackMainPage() throws IOException {
        Connection.Response response = Jsoup.connect(WEATHERSTACK_BASEURL)
                .method(org.jsoup.Connection.Method.GET)
                .ignoreContentType(true)
                .execute();

        return response;
    }

    public static Connection.Response getCurrentWeatherCity(String city, String access_key) throws IOException {
        Connection.Response response = Jsoup.connect(WEATHERSTACK_CURRENTWEATHER_APIURL)
                .data("access_key", access_key)
                .data("query", city)
                .method(Connection.Method.GET)
                .ignoreContentType(true)
                .execute();

        return response;
    }

    public static Connection.Response getLanguageWeatherstackForecast(Query query, User user) throws IOException {
        Connection.Response response = Jsoup.connect(query.getForecastUrl())
                .data("access_key", user.getAccessKey())
                .data("query", user.getCity())
                .data("language", query.getLanguage())
                .method(Connection.Method.GET)
                .ignoreContentType(true)
                .execute();

        return response;
    }
}
