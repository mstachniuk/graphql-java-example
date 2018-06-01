package io.github.mstachniuk.graphqljavaexample;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.PropertyDataFetcher;
import io.github.mstachniuk.graphqljavaexample.customer.CustomerService;

@Component
public class CreateCustomerFetcher extends PropertyDataFetcher<CreateCustomerPayload> {

	@Autowired
	private CustomerService customerService;

	public CreateCustomerFetcher() {
		super("createCustomer");
	}

	@Override
	public CreateCustomerPayload get(DataFetchingEnvironment environment) {
		Map<String, Object> input = environment.getArgument("input");
		String name = (String) input.get("name");
		String email = (String) input.get("email");
		String clientMutationId = (String) input.get("clientMutationId");
		Customer customer = customerService.create(name, email);
		return new CreateCustomerPayload(customer, clientMutationId);
	}
}
