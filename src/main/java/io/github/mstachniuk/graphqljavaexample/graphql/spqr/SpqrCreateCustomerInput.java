package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.leangen.graphql.annotations.GraphQLInputField;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.types.GraphQLType;

@GraphQLType(name = "CreateCustomer")
public class SpqrCreateCustomerInput {

    @GraphQLNonNull
    @GraphQLInputField
    private String clientMutationId;

    @GraphQLInputField
    private String name;

    @GraphQLInputField
    private String email;

    public String getClientMutationId() {
        return clientMutationId;
    }

    public void setClientMutationId(String clientMutationId) {
        this.clientMutationId = clientMutationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
