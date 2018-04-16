package io.github.mstachniuk.graphqljavaexample;

import java.util.Map;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.FieldDataFetcher;

class CompanyDataFetcher extends FieldDataFetcher {

    public CompanyDataFetcher(String fieldName) {
        super(fieldName);
    }

    @Override
    public Object get(DataFetchingEnvironment environment) {
        Map<String, Object> arguments = environment.getArguments();
        Object source = environment.getSource();
        return new Company("11", "Company Corp.", "www.company.com");
    }
}
