package io.github.mstachniuk.graphqljavaexample.customer;

import java.util.List;

public class CreateCustomersPayload {

	private List<Customer> customers;
	private String clientMutationId;

	public CreateCustomersPayload() {
	}

	public CreateCustomersPayload(List<Customer> customers, String clientMutationId) {
		this.customers = customers;
		this.clientMutationId = clientMutationId;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public String getClientMutationId() {
		return clientMutationId;
	}

	public void setClientMutationId(String clientMutationId) {
		this.clientMutationId = clientMutationId;
	}
}
