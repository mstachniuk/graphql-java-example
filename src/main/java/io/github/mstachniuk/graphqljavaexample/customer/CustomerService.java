package io.github.mstachniuk.graphqljavaexample.customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	private Map<String, Customer> db = new HashMap<>();
	private Random random = new Random();

	public CustomerService() {
		db.put("2", new Customer("2", "name", "a@b.com"));
		db.put("3", new Customer("3", "John Doe", "john@doe.com"));
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

	public List<Customer> getAll() {
		return new ArrayList<>(db.values());
	}

	public Customer delete(String customerId) {
		return db.remove(customerId);
	}
}
