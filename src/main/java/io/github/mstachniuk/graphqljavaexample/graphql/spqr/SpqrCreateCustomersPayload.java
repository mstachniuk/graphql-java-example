package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.github.mstachniuk.graphqljavaexample.customer.Customer;
import io.leangen.graphql.annotations.types.GraphQLType;

import java.util.List;

@GraphQLType
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

    public String getClientMutationId() {
        return clientMutationId;
    }

    public void setClientMutationId(String clientMutationId) {
        this.clientMutationId = clientMutationId;
    }
}
