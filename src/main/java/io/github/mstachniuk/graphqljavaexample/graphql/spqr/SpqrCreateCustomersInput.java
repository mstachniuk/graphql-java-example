package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.leangen.graphql.annotations.GraphQLInputField;
import io.leangen.graphql.annotations.types.GraphQLType;

import java.util.List;

@GraphQLType(name = "CreateCustomers")
public class SpqrCreateCustomersInput {

    private List<SpqrCreateCustomer> customers;
    private String clientMutationId;

    public SpqrCreateCustomersInput() {
    }

    public SpqrCreateCustomersInput(List<SpqrCreateCustomer> customers, String clientMutationId) {
        this.customers = customers;
        this.clientMutationId = clientMutationId;
    }

    public List<SpqrCreateCustomer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<SpqrCreateCustomer> customers) {
        this.customers = customers;
    }

    public String getClientMutationId() {
        return clientMutationId;
    }

    public void setClientMutationId(String clientMutationId) {
        this.clientMutationId = clientMutationId;
    }
}

@GraphQLType(name = "CreateCustomer")
class SpqrCreateCustomer {
    @GraphQLInputField
    private String name;
    @GraphQLInputField
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
