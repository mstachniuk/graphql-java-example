package io.github.mstachniuk.graphqljavaexample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.PropertyDataFetcher;
import io.github.mstachniuk.graphqljavaexample.order.OrderService;

@Component
public class OrderDataFetcher extends PropertyDataFetcher<List<Order>> {

	@Autowired
	private OrderService orderService;

	public OrderDataFetcher() {
		super("orders");
	}

	@Override
	public List<Order> get(DataFetchingEnvironment environment) {
		Customer source = environment.getSource();
		String customerId = source.getId();
		return orderService.getOrdersByCustomerId(customerId);
	}
}
