package io.github.mstachniuk.graphqljavaexample.customer;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.PropertyDataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DeleteCustomerFetcher extends PropertyDataFetcher<DeleteCustomerPayload> {

    @Autowired
    private CustomerService customerService;

    public DeleteCustomerFetcher() {
        super("deleteCustomer");
    }

    @Override
    public DeleteCustomerPayload get(DataFetchingEnvironment environment) {
        Map<String, Object> input = environment.getArgument("input");
        String customerId = (String) input.get("id");
        String clientMutationId = (String) input.get("clientMutationId");
        Customer customer = customerService.delete(customerId);
        return new DeleteCustomerPayload(customer, clientMutationId);
    }
}
