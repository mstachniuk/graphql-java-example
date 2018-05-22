package io.github.mstachniuk.graphqljavaexample.customer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import io.github.mstachniuk.graphqljavaexample.Customer;

@Service
public class CustomerService {

	private Map<String, Customer> db = new HashMap<>();
	private Random random = new Random();

	public CustomerService() {
		db.put("2", new Customer("2", "name", "a@b.com"));
	}

	public Customer getCustomerById(String id) {
		return db.get(id);
	}

	public Customer create(String name, String email) {
		Customer customer = new Customer(generateId(), name, email);
		db.put(customer.getId(), customer);
		return customer;
	}

	private String generateId() {
		String id = String.valueOf(random.nextInt(1000));
		while (db.get(id) != null) {
			id = String.valueOf(random.nextInt(1000));
		}
		return id;
	}
}
