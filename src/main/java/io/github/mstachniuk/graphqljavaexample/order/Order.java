package io.github.mstachniuk.graphqljavaexample.order;

public class Order {
	private final String id;
	private final Status status;

	public Order(String id, Status status) {
		this.id = id;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public Status getStatus() {
		return status;
	}
}
