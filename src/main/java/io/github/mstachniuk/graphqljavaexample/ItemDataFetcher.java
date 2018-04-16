package io.github.mstachniuk.graphqljavaexample;

import java.util.Arrays;
import java.util.List;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.PropertyDataFetcher;

public class ItemDataFetcher extends PropertyDataFetcher<List<Item>> {

	public ItemDataFetcher() {
		super("items");
	}

	@Override
	public List<Item> get(DataFetchingEnvironment environment) {
		Company duckCompany = new Company("12", "Duck Company", "duck.com");
		Item duck = new Item("101", "Rubber duck", 2, "5", "USD", duckCompany);
		Company ballCompany = new Company("13", "Ball Company", "www.com");
		Item ball = new Item("102", "Magic Ball", 1, "10", "USD", ballCompany);
		return Arrays.asList(duck, ball);
	}
}
