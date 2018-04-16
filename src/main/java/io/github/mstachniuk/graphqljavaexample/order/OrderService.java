package io.github.mstachniuk.graphqljavaexample.order;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import io.github.mstachniuk.graphqljavaexample.Order;
import io.github.mstachniuk.graphqljavaexample.Status;

@Service
public class OrderService {

	public List<Order> getOrdersByCustomerId(String customerId) {
		return Arrays.asList(new Order("55", Status.NEW), new Order("66", Status.DONE));
	}

}
