package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.github.mstachniuk.graphqljavaexample.customer.Customer;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.types.GraphQLType;

import java.util.List;

@GraphQLType(name = "CreateCustomersPayload")
public class SpqrCreateCustomersPayload {

    private List<SpqrCustomer> customers;
    private String clientMutationId;

    public SpqrCreateCustomersPayload() {
    }

    public SpqrCreateCustomersPayload(List<SpqrCustomer> customers, String clientMutationId) {
        this.customers = customers;
        this.clientMutationId = clientMutationId;
    }

    public List<SpqrCustomer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<SpqrCustomer> customers) {
        this.customers = customers;
    }

    @GraphQLNonNull
    public String getClientMutationId() {
        return clientMutationId;
    }

    public void setClientMutationId(String clientMutationId) {
        this.clientMutationId = clientMutationId;
    }
}
