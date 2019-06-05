package io.github.mstachniuk.graphqljavaexample.company;

public class Company {
    private String id;
    private String name;
    private String website;

    public Company(String id, String name, String website) {
        this.id = id;
        this.name = name;
        this.website = website;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }
}
