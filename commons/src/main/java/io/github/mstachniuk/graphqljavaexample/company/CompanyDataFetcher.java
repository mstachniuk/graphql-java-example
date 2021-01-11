package io.github.mstachniuk.graphqljavaexample.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.PropertyDataFetcher;
import io.github.mstachniuk.graphqljavaexample.customer.Customer;

@Component
public class CompanyDataFetcher extends PropertyDataFetcher<Company> {

	@Autowired
	private CompanyService companyService;

    public CompanyDataFetcher() {
        super("company");
    }

    @Override
    public Company get(DataFetchingEnvironment environment) {
        Customer source = environment.getSource();
	    String customerId = source.getId();
	    return companyService.getCompanyByCustomerId(customerId);
    }
}
