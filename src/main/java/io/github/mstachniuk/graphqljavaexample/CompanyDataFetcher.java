package io.github.mstachniuk.graphqljavaexample;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.PropertyDataFetcher;

@Component
public class CompanyDataFetcher extends PropertyDataFetcher {

    public CompanyDataFetcher() {
        super("company");
    }

    @Override
    public Object get(DataFetchingEnvironment environment) {
        Object source = environment.getSource();
        return new Company("11", "Company Corp.", "www.company.com");
    }
}
