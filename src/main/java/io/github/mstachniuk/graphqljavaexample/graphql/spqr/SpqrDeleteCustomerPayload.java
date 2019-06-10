package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.types.GraphQLType;

@GraphQLType(name = "DeleteCustomerPayload")
public class SpqrDeleteCustomerPayload {

    private SpqrCustomer customer;
    private String clientMutationId;

    public SpqrDeleteCustomerPayload() {
    }

    public SpqrDeleteCustomerPayload(SpqrCustomer customer, String clientMutationId) {
        this.customer = customer;
        this.clientMutationId = clientMutationId;
    }

    public SpqrCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(SpqrCustomer customer) {
        this.customer = customer;
    }

    @GraphQLNonNull
    public String getClientMutationId() {
        return clientMutationId;
    }

    public void setClientMutationId(String clientMutationId) {
        this.clientMutationId = clientMutationId;
    }
}
