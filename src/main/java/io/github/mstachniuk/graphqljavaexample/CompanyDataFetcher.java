package io.github.mstachniuk.graphqljavaexample;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.PropertyDataFetcher;

class CompanyDataFetcher extends PropertyDataFetcher {

    public CompanyDataFetcher(String fieldName) {
        super(fieldName);
    }

    @Override
    public Object get(DataFetchingEnvironment environment) {
        Object source = environment.getSource();
        return new Company("11", "Company Corp.", "www.company.com");
    }
}
