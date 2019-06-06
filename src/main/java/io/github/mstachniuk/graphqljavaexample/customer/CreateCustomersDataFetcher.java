package io.github.mstachniuk.graphqljavaexample.customer;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.PropertyDataFetcher;

@Component
public class CreateCustomersDataFetcher extends PropertyDataFetcher<CreateCustomersPayload> {

	@Autowired
	private CustomerService customerService;

	public CreateCustomersDataFetcher() {
		super("createCustomer");
	}

	@Override
	public CreateCustomersPayload get(DataFetchingEnvironment environment) {
		Map<String, Object> input = environment.getArgument("input");
		List<Map<String, String>> customers = (List<Map<String, String>>) input.get("customers");
		List<Customer> createdCustomers = customers.stream()
				.map(customer -> {
					String name = customer.get("name");
					String email = customer.get("email");
					return customerService.create(name, email);
				})
				.collect(toList());
		String clientMutationId = (String) input.get("clientMutationId");
		return new CreateCustomersPayload(createdCustomers, clientMutationId);
	}
}
