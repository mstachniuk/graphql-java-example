package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.leangen.graphql.annotations.GraphQLId;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.types.GraphQLType;

@GraphQLType(name = "DeleteCustomer")
public class SpqrDeleteCustomerInput {
    private String id;
    private String clientMutationId;

    @GraphQLId
    @GraphQLNonNull
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @GraphQLNonNull
    public String getClientMutationId() {
        return clientMutationId;
    }

    public void setClientMutationId(String clientMutationId) {
        this.clientMutationId = clientMutationId;
    }
}
