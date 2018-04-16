package io.github.mstachniuk.graphqljavaexample;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.FieldDataFetcher;

class CustomerDataFetcher extends FieldDataFetcher {

    public CustomerDataFetcher(String fieldName) {
        super(fieldName);
    }

    @Override
    public Object get(DataFetchingEnvironment environment) {
        String id = environment.getArgument("id");
        return new Customer(id, "name", "a@b.com");
    }
}
