package fw.model;

public class Query {

    private String language;
    private String unit;
    private String forecastUrl;

    public String getLanguage() {
        return language;
    }

    public Query setLanguage(String language) {
        this.language = language;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public Query setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public String getForecastUrl() {
        return forecastUrl;
    }

    public Query setForecastUrl(String forecastUrl) {
        this.forecastUrl = forecastUrl;
        return this;
    }
}
