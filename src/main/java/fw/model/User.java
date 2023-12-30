package fw.model;

public class User {
    private String accessKey;
    private String city;

    public String getAccessKey() {
        return accessKey;
    }

    public User setAccessKey(String accessKey) {
        this.accessKey = accessKey;
        return this;
    }

    public String getCity() {
        return city;
    }

    public User setCity(String city) {
        this.city = city;
        return this;
    }
}
