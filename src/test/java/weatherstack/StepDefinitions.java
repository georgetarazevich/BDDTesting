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
        user = new User().setCity("Minsk").setAccessKey(PropertiesValue.getAccessKey());
        query = new Query().setForecastUrl(PropertiesValue.getWeatherstackForecast());
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

    @Then("The user receive code 101")
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
