package io.github.mstachniuk.graphqljavaexample.customer;

import io.github.mstachniuk.graphqljavaexample.search.SearchResult;

public class Customer implements SearchResult {
    private String id;
    private String name;
    private String email;

    public Customer(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
