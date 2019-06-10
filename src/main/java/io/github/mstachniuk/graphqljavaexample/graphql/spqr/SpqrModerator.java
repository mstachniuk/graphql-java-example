package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.github.mstachniuk.graphqljavaexample.user.AbstractUser;
import io.leangen.graphql.annotations.GraphQLId;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.types.GraphQLType;

import java.util.List;

@GraphQLType(name = "Moderator")
public class SpqrModerator implements SpqrUser {

    private final String id;
    private final String name;
    private final String email;
    private final List<String> permissions;

    public SpqrModerator(String id, String name, String email, List<String> permissions) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.permissions = permissions;
    }

    @Override
    @GraphQLId
    @GraphQLNonNull
    public String getId() {
        return id;
    }

    @Override
    @GraphQLNonNull
    public String getName() {
        return name;
    }

    @Override
    @GraphQLNonNull
    public String getEmail() {
        return email;
    }

    public List<String> getPermissions() {
        return permissions;
    }
}
