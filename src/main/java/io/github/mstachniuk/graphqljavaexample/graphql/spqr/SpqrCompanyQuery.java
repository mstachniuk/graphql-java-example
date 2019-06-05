package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.github.mstachniuk.graphqljavaexample.company.Company;
import io.github.mstachniuk.graphqljavaexample.company.CompanyService;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpqrCompanyQuery {

    @Autowired
    private CompanyService companyService;

    @GraphQLQuery(name = "company")
    public SpqrCompany getCompanyFotCustomer(@GraphQLContext SpqrCustomer customer) {
        return toSpqr(companyService.getCompanyByCustomerId(customer.getId()));
    }

    private SpqrCompany toSpqr(Company company) {
        return new SpqrCompany(company.getId(), company.getName(), company.getWebsite());
    }
}
