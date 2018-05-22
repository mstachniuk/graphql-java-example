package io.github.mstachniuk.graphqljavaexample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.PropertyDataFetcher;
import io.github.mstachniuk.graphqljavaexample.customer.CustomerService;

@Component
public class CustomersFetcher extends PropertyDataFetcher<List<Customer>> {

	@Autowired
	private CustomerService customerService;

	public CustomersFetcher() {
		super("customers");
	}

	@Override
	public List<Customer> get(DataFetchingEnvironment environment) {
		return customerService.getAll();
	}
}
