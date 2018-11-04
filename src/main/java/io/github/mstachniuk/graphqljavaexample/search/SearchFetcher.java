package io.github.mstachniuk.graphqljavaexample.search;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.PropertyDataFetcher;
import io.github.mstachniuk.graphqljavaexample.customer.Customer;
import io.github.mstachniuk.graphqljavaexample.customer.CustomerService;
import io.github.mstachniuk.graphqljavaexample.user.User;
import io.github.mstachniuk.graphqljavaexample.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchFetcher extends PropertyDataFetcher<List<SearchResult>> {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserService userService;

    public SearchFetcher() {
        super("search");
    }

    @Override
    public List<SearchResult> get(DataFetchingEnvironment environment) {
        String input = environment.getArgument("input");
        List<Customer> customers = customerService.getAll().stream()
                .filter(customer -> customer.getName().contains(input))
                .collect(Collectors.toList());
        List<User> users = userService.getUsers().stream()
                .filter(user -> user.getName().contains(input))
                .collect(Collectors.toList());

        List<SearchResult> result = new ArrayList<>();
        result.addAll(customers);
        result.addAll(users);
        return result;
    }
}
