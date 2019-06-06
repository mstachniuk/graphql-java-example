package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.github.mstachniuk.graphqljavaexample.customer.Customer;
import io.github.mstachniuk.graphqljavaexample.customer.CustomerService;
import io.github.mstachniuk.graphqljavaexample.user.Admin;
import io.github.mstachniuk.graphqljavaexample.user.Moderator;
import io.github.mstachniuk.graphqljavaexample.user.User;
import io.github.mstachniuk.graphqljavaexample.user.UserService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpqrSearchQuery {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserService userService;

    @GraphQLQuery
    public List<SpqrSearchResult> search(@GraphQLArgument(name = "input") String input) {
        List<SpqrCustomer> customers = customerService.getAll().stream()
                .filter(customer -> customer.getName().contains(input))
                .map(this::toSpqrCustomer)
                .collect(Collectors.toList());
        List<SpqrUser> users = userService.getUsers().stream()
                .filter(user -> user.getName().contains(input))
                .map(this::toSpqrUser)
                .collect(Collectors.toList());

        List<SpqrSearchResult> result = new ArrayList<>();
        result.addAll(customers);
        result.addAll(users);

        return result;
    }

    private SpqrCustomer toSpqrCustomer(Customer customer) {
        return new SpqrCustomer(customer.getId(), customer.getName(), customer.getEmail());
    }

    private SpqrUser toSpqrUser(User user) {
        if(user instanceof Admin) {
            return new SpqrAdmin(user.getId(), user.getName(), user.getEmail(), ((Admin) user).isSuperAdmin());
        } else if(user instanceof Moderator) {
            return new SpqrModerator(user.getId(), user.getName(), user.getEmail(), ((Moderator) user).getPermissions());
        }
        throw new RuntimeException("Type " + user.getClass().getName() + " NOT implemented as User");
    }
}
