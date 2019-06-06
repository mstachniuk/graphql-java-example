package io.github.mstachniuk.graphqljavaexample.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.PropertyDataFetcher;

@Component
public class CustomerDataFetcher extends PropertyDataFetcher<Customer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDataFetcher.class);

    @Autowired
    private CustomerService customerService;

    public CustomerDataFetcher() {
        super("customer");
    }

    @Override
    public Customer get(DataFetchingEnvironment environment) {
        String id = environment.getArgument("id");
	    LOGGER.trace("CustomerFetcher get by {}", id);
        return customerService.getCustomerById(id);
    }
}
