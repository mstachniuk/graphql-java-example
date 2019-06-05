package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.github.mstachniuk.graphqljavaexample.user.AbstractUser;
import io.leangen.graphql.annotations.types.GraphQLType;

import java.util.List;

@GraphQLType(name = "Moderator")
public class SpqrModerator extends AbstractUser implements SpqrUser {

    private final List<String> permissions;

    public SpqrModerator(String id, String name, String email, List<String> permissions) {
        super(id, name, email);
        this.permissions = permissions;
    }

    public List<String> getPermissions() {
        return permissions;
    }
}
