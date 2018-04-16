package io.github.mstachniuk.graphqljavaexample;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.FieldDataFetcher;

@Component
public class CustomerDataFetcher extends FieldDataFetcher {

    public CustomerDataFetcher() {
        super("customers");
    }

    @Override
    public Object get(DataFetchingEnvironment environment) {
        String id = environment.getArgument("id");
        return new Customer(id, "name", "a@b.com");
    }
}
