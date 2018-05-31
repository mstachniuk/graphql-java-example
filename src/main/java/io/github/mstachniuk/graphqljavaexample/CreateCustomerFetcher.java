package io.github.mstachniuk.graphqljavaexample;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.PropertyDataFetcher;
import io.github.mstachniuk.graphqljavaexample.customer.CustomerService;

@Component
public class CreateCustomerFetcher extends PropertyDataFetcher<Customer> {

	@Autowired
	private CustomerService customerService;

	public CreateCustomerFetcher() {
		super("createCustomer");
	}

	@Override
	public Customer get(DataFetchingEnvironment environment) {
		Map<String, Object> input = environment.getArgument("input");
		String name = (String) input.get("name");
		String email = (String) input.get("email");
		return customerService.create(name, email);
	}
}
