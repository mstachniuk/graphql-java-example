package io.github.mstachniuk.graphqljavaexample;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.FieldDataFetcher;

@Component
public class OrderDataFetcher extends FieldDataFetcher {
	public OrderDataFetcher() {
		super("orders");
	}

	@Override
	public Object get(DataFetchingEnvironment environment) {
		Object source = environment.getSource();
		return Arrays.asList(new Order("55", Status.NEW), new Order("66", Status.DONE));
	}
}
