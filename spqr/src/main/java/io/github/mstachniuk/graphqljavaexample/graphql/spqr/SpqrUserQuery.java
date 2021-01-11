package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.github.mstachniuk.graphqljavaexample.user.Admin;
import io.github.mstachniuk.graphqljavaexample.user.Moderator;
import io.github.mstachniuk.graphqljavaexample.user.User;
import io.github.mstachniuk.graphqljavaexample.user.UserService;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpqrUserQuery {

    @Autowired
    private UserService userService;

    @GraphQLQuery(name = "users")
    public List<SpqrUser> getAllUsers() {
        return userService.getUsers().stream()
                .map(this::toSpqr)
                .collect(Collectors.toList());
    }

    private SpqrUser toSpqr(User user) {
        if(user instanceof Admin) {
            return new SpqrAdmin(user.getId(), user.getName(), user.getEmail(), ((Admin) user).isSuperAdmin());
        } else if(user instanceof Moderator) {
            return new SpqrModerator(user.getId(), user.getName(), user.getEmail(), ((Moderator) user).getPermissions());
        }
        throw new RuntimeException("Type " + user.getClass().getName() + " NOT implemented as User");
    }
}
