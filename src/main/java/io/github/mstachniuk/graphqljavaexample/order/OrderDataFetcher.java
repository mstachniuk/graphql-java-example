package io.github.mstachniuk.graphqljavaexample.order;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.PropertyDataFetcher;
import io.github.mstachniuk.graphqljavaexample.customer.Customer;

@Component
public class OrderDataFetcher extends PropertyDataFetcher<List<Order>> {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderDataFetcher.class);

	@Autowired
	private OrderService orderService;

	public OrderDataFetcher() {
		super("orders");
	}

	@Override
	public List<Order> get(DataFetchingEnvironment environment) {
		Customer source = environment.getSource();
		String customerId = source.getId();
		LOGGER.trace("OrderDataFetcher get by {}", customerId);
		return orderService.getOrdersByCustomerId(customerId);
	}
}
