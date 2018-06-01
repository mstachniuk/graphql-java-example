package io.github.mstachniuk.graphqljavaexample;

public class CreateCustomerPayload {

	private Customer customer;
	private String clientMutationId;

	public CreateCustomerPayload() {
	}

	public CreateCustomerPayload(Customer customer, String clientMutationId) {
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
