package io.github.mstachniuk.graphqljavaexample.customer;

public class DeleteCustomerPayload {

    private Customer customer;
    private String clientMutationId;

    public DeleteCustomerPayload() {
    }

    public DeleteCustomerPayload(Customer customer, String clientMutationId) {
        this.customer = customer;
        this.clientMutationId = clientMutationId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getClientMutationId() {
        return clientMutationId;
    }

    public void setClientMutationId(String clientMutationId) {
        this.clientMutationId = clientMutationId;
    }
}
