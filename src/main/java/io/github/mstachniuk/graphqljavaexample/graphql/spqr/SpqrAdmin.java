package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.github.mstachniuk.graphqljavaexample.user.AbstractUser;
import io.leangen.graphql.annotations.types.GraphQLType;

@GraphQLType(name = "Admin")
public class SpqrAdmin extends AbstractUser implements SpqrUser {

    private boolean superAdmin;

    public SpqrAdmin(String id, String name, String email, boolean superAdmin) {
        super(id, name, email);
        this.superAdmin = superAdmin;
    }

    public boolean isSuperAdmin() {
        return superAdmin;
    }
}
