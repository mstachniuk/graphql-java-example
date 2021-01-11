package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.leangen.graphql.annotations.GraphQLId;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.types.GraphQLType;

@GraphQLType(name = "Admin")
public class SpqrAdmin implements SpqrUser {

    private String id;
    private String name;
    private String email;
    private boolean superAdmin;

    public SpqrAdmin(String id, String name, String email, boolean superAdmin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.superAdmin = superAdmin;
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

    @GraphQLNonNull
    public boolean isSuperAdmin() {
        return superAdmin;
    }
}
