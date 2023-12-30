package weatherstack;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import fw.HttpHelper;
import fw.model.Query;
import fw.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import tools.PropertiesValue;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions {
    private static Logger log = Logger.getLogger(StepDefinitions.class.getName());
    private Connection.Response weatherstackMainPageresponse = null;
    private Connection.Response weatherstackInformationForTheCity = null;
    private Connection.Response invalidAccessKeyResponse = null;
    private Connection.Response languageWeatherstackForecastResponse = null;
    private Document documentWeatherstackMainPage = null;
    private DocumentContext jsonContextWeatherstackInformationForTheCity = null;
    private int currentCityTemperature = 100;

    private User user;
    private Query query;


    @Given("^Current Weatherstack information for the city \"(.*)\"$")
    public void weatherstackInformationForTheCity(String city) throws IOException, InterruptedException {
        log.info("the city: " + city);
        weatherstackInformationForTheCity = HttpHelper.getCurrentWeatherCity(city, PropertiesValue.getAccessKey());
    }

    @Given("The main page Weatherstack")
    public void theMainPageWeatherstack() throws IOException, InterruptedException {
        weatherstackMainPageresponse = HttpHelper.getWeatherstackMainPage();
    }

    @Given("The user has query to send")
    public void TheUserHasInformationToSend() throws IOException, InterruptedException {
        user = new User().setCity(PropertiesValue.getCity()).setAccessKey(PropertiesValue.getAccessKey());
        query = new Query().setForecastUrl(PropertiesValue.getWeatherstackForecast());
    }

    @Given("^The user has query to send  with \"(.*)\"$")
    public void TheUserHasInformationToSend(String language) throws IOException, InterruptedException {
        user = new User().setCity(PropertiesValue.getCity()).setAccessKey(PropertiesValue.getAccessKey());
        query = new Query().setForecastUrl(PropertiesValue.getWeatherstackForecast()).setLanguage(language);
    }

    @When("The User explicitly requests info in Language by default")
    public void userExplicitlyRequestsInfoInLanguageByDefault() throws IOException {
        log.info("the Language: " + query.getLanguage());
        languageWeatherstackForecastResponse = HttpHelper.getLanguageWeatherstackForecast(query, user);
    }

    @When("The user requests info in Language different than default")
    public void userRequestsInfoInLanguageDifferentThanDefault() throws IOException {
        log.info("the Language: " + query.getLanguage());
        languageWeatherstackForecastResponse = HttpHelper.getLanguageWeatherstackForecast(query, user);
    }

    @When("^The User requests info with city name \"(.*)\"$")
    public void userRequestsInfoWithCityName(String city) throws IOException {
        user.setCity(city);
        log.info("the city: " + user.getCity());
        languageWeatherstackForecastResponse = HttpHelper.getCurrentWeatherCity(user.getCity(), user.getAccessKey());
    }

    @Then("The user receive code 105 function_access_restricted")
    public void userReceiveCode105function_access_restricted() {
        DocumentContext jsonContextLanguageWeatherstackForecast = JsonPath.parse(languageWeatherstackForecastResponse.body());
        int errorCode = jsonContextLanguageWeatherstackForecast.read("$['error']['code']");
        log.info("errorCode: " + errorCode);
        assertThat(errorCode).isEqualTo(105);
    }

    @Then("The user receive code 605 invalid_language")
    public void UserReceiveCode605invalid_language() {
        DocumentContext jsonContextLanguageWeatherstackForecast = JsonPath.parse(languageWeatherstackForecastResponse.body());
        int errorCode = jsonContextLanguageWeatherstackForecast.read("$['error']['code']");
        log.info("errorCode: " + errorCode);
        assertThat(errorCode).isEqualTo(605);
    }

    @Then("The user receive code 615 request_failed")
    public void UserReceiveCode615request_failed() {
        DocumentContext jsonContextLanguageWeatherstackForecast = JsonPath.parse(languageWeatherstackForecastResponse.body());
        int errorCode = jsonContextLanguageWeatherstackForecast.read("$['error']['code']");
        log.info("errorCode: " + errorCode);
        assertThat(errorCode).isEqualTo(615);
    }

    @When("The user sends query with invalid access_key")
    public void TheUserSendsInvalidAccess_key() throws IOException {
        user.setAccessKey("12345");
        log.info("the city and AccessKey: " + user.getCity() + " " + user.getAccessKey());
        invalidAccessKeyResponse = HttpHelper.getCurrentWeatherCity(user.getCity(), user.getAccessKey());
    }

    @When("A user open the main page Weatherstack")
    public void userOpenTheMainPageWeatherstack() throws IOException {
        documentWeatherstackMainPage = weatherstackMainPageresponse.parse();
    }

    @When("A user parsing Weatherstack information")
    public void userParsingWeatherstackInformation() {
        jsonContextWeatherstackInformationForTheCity = JsonPath.parse(weatherstackInformationForTheCity.body());
    }

    @Then("The user receive code 101 invalid_access_key")
    public void userReceiveCode101() {
        DocumentContext jsonContextAccessKeyInfo = JsonPath.parse(invalidAccessKeyResponse.body());
        int errorCode = jsonContextAccessKeyInfo.read("$['error']['code']");
        log.info("errorCode: " + errorCode);
        assertThat(errorCode).isEqualTo(101);
    }

    @Then("A user see current Temperature info")
    public void userSeeTemperatureInfo() {
        currentCityTemperature = jsonContextWeatherstackInformationForTheCity.read("$['current']['temperature']");
        log.info("currentTemperature: " + currentCityTemperature);
    }

    @Then("^The current Temperature is Less Then \"(.*)\" C$")
    public void currentTemperatureIsLessThen(int temperature) {
        assertThat(currentCityTemperature).isLessThan(temperature);
    }

    @Then("A user see the Weather forecast for the 5 days")
    public void userSeeTheWeatherForecastForThe5days() {
        Elements weekDaysElements = documentWeatherstackMainPage.selectXpath("//*[@class='week']//*[@class='day']");
        log.info("weekDaysElements.size(): " + weekDaysElements.size());
        assertThat(weekDaysElements.size()).isEqualTo(5);
    }
}
