package io.github.mstachniuk.graphqljavaexample;

public class Order {
	private final String id;
	private final Status status;

	public Order(String id, Status status) {
		this.id = id;
		this.status = status;
	}
}
