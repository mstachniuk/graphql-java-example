package io.github.mstachniuk.graphqljavaexample;

import java.util.Arrays;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.FieldDataFetcher;

public class OrderDataFetcher extends FieldDataFetcher {
	public OrderDataFetcher(String orders) {
		super(orders);
	}

	@Override
	public Object get(DataFetchingEnvironment environment) {
		Object source = environment.getSource();
		return Arrays.asList(new Order("55", Status.NEW), new Order("66", Status.DONE));
	}
}
