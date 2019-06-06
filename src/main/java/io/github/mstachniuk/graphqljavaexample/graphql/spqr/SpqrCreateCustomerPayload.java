package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.leangen.graphql.annotations.types.GraphQLType;

@GraphQLType(name = "CreateCustomerPayload")
public class SpqrCreateCustomerPayload {

    private SpqrCustomer customer;
    private String clientMutationId;

    public SpqrCreateCustomerPayload() {
    }

    public SpqrCreateCustomerPayload(SpqrCustomer customer, String clientMutationId) {
        this.customer = customer;
        this.clientMutationId = clientMutationId;
    }

    public SpqrCustomer getCustomer() {
        return customer;
    }

    public String getClientMutationId() {
        return clientMutationId;
    }
}
