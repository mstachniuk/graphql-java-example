package io.github.mstachniuk.graphqljavaexample.company;

import org.springframework.stereotype.Service;

@Service
public class CompanyService {

	public Company getCompanyByCustomerId(String customerId) {
		return new Company(customerId + "11", "Company Corp.", "www.company.com");
	}
}
