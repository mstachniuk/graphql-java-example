package io.github.mstachniuk.graphqljavaexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.PropertyDataFetcher;
import io.github.mstachniuk.graphqljavaexample.company.CompanyService;

@Component
public class CompanyDataFetcher extends PropertyDataFetcher {

	@Autowired
	private CompanyService companyService;

    public CompanyDataFetcher() {
        super("company");
    }

    @Override
    public Object get(DataFetchingEnvironment environment) {
        Customer source = environment.getSource();
	    String customerId = source.getId();
	    return companyService.getCompanyByCustomerId(customerId);
    }
}
