package io.github.mstachniuk.graphqljavaexample.order;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.github.mstachniuk.graphqljavaexample.Order;
import io.github.mstachniuk.graphqljavaexample.Status;

@Service
public class OrderService {

	private Map<String, List<Order>> db = new HashMap<>();

	public OrderService() {
		db.put("2", Arrays.asList(new Order("55", Status.NEW),
				new Order("66", Status.DONE)));
		db.put("3", Arrays.asList(new Order("77", Status.CANCELED)));
	}

	public List<Order> getOrdersByCustomerId(String customerId) {
		return db.get(customerId);
	}

}
