package io.github.mstachniuk.graphqljavaexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.FieldDataFetcher;
import io.github.mstachniuk.graphqljavaexample.customer.CustomerService;

@Component
public class CreateCustomerFetcher extends FieldDataFetcher<Customer> {

	@Autowired
	private CustomerService customerService;

	public CreateCustomerFetcher() {
		super("createCustomer");
	}

	@Override
	public Customer get(DataFetchingEnvironment environment) {
		String name = environment.getArgument("name");
		String email = environment.getArgument("email");
		return customerService.create(name, email);
	}
}
