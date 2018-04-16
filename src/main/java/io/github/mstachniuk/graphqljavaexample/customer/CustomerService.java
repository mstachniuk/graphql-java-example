package io.github.mstachniuk.graphqljavaexample.customer;

import org.springframework.stereotype.Service;

import io.github.mstachniuk.graphqljavaexample.Customer;

@Service
public class CustomerService {

	public Customer getCustomerById(String id) {
		return new Customer(id, "name", "a@b.com");
	}
}
