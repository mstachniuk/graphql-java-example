package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.github.mstachniuk.graphqljavaexample.customer.Customer;
import io.github.mstachniuk.graphqljavaexample.customer.CustomerService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpqrCustomerQuery {

    @Autowired
    private CustomerService customerService;

    @GraphQLQuery(name = "customer")
    public SpqrCustomer getCustomerById(@GraphQLArgument(name = "id") String id) {
        Customer customer = customerService.getCustomerById(id);
        return toSpqr(customer);
    }

    private SpqrCustomer toSpqr(Customer customer) {
        return new SpqrCustomer(customer.getId(), customer.getName(), customer.getEmail());
    }
}
