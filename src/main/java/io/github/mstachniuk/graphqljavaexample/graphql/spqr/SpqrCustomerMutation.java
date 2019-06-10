package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.github.mstachniuk.graphqljavaexample.customer.Customer;
import io.github.mstachniuk.graphqljavaexample.customer.CustomerService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class SpqrCustomerMutation {

    @Autowired
    private CustomerService customerService;

    @GraphQLMutation
    public @GraphQLNonNull SpqrCreateCustomerPayload createCustomer(@GraphQLArgument(name = "input") SpqrCreateCustomerInput input) {
        Customer customer = customerService.create(input.getName(), input.getEmail());
        return new SpqrCreateCustomerPayload(toSpqr(customer), input.getClientMutationId());
    }

    @GraphQLMutation
    public @GraphQLNonNull SpqrCreateCustomersPayload createCustomers(@GraphQLArgument(name = "input") SpqrCreateCustomersInput input) {
        List<SpqrCustomer> customers = input.getCustomers().stream()
                .map(customer -> customerService.create(customer.getName(), customer.getEmail()))
                .map(this::toSpqr)
                .collect(toList());
        return new SpqrCreateCustomersPayload(customers, input.getClientMutationId());
    }

    @GraphQLMutation
    public @GraphQLNonNull SpqrDeleteCustomerPayload deleteCustomer(@GraphQLArgument(name = "input") SpqrDeleteCustomerInput input) {
        Customer customer = customerService.delete(input.getId());
        return new SpqrDeleteCustomerPayload(toSpqr(customer), input.getClientMutationId());
    }

    private SpqrCustomer toSpqr(Customer customer) {
        return new SpqrCustomer(customer.getId(), customer.getName(), customer.getEmail());
    }

}
