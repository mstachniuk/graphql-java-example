package io.github.mstachniuk.graphqljavaexample.item;

import io.github.mstachniuk.graphqljavaexample.company.Company;

public class Item {
	private final String id;
	private final String name;
	private final int amount;
	private final String price;
	private final String currency;
	private final Company producer;

	public Item(String id, String name, int amount, String price, String currency, Company producer) {
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.price = price;
		this.currency = currency;
		this.producer = producer;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAmount() {
		return amount;
	}

	public String getPrice() {
		return price;
	}

	public String getCurrency() {
		return currency;
	}

	public Company getProducer() {
		return producer;
	}
}
