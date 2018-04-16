package io.github.mstachniuk.graphqljavaexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.FieldDataFetcher;
import io.github.mstachniuk.graphqljavaexample.order.OrderService;

@Component
public class OrderDataFetcher extends FieldDataFetcher {

	@Autowired
	private OrderService orderService;

	public OrderDataFetcher() {
		super("orders");
	}

	@Override
	public Object get(DataFetchingEnvironment environment) {
		Customer source = environment.getSource();
		String customerId = source.getId();
		return orderService.getOrdersByCustomerId(customerId);
	}
}
