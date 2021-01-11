package io.github.mstachniuk.graphqljavaexample.graphql.schemafirst;

import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import io.github.mstachniuk.graphqljavaexample.company.CompanyDataFetcher;
import io.github.mstachniuk.graphqljavaexample.customer.*;
import io.github.mstachniuk.graphqljavaexample.item.ItemDataFetcher;
import io.github.mstachniuk.graphqljavaexample.order.OrderDataFetcher;
import io.github.mstachniuk.graphqljavaexample.search.SearchDataFetcher;
import io.github.mstachniuk.graphqljavaexample.search.SearchResultResolver;
import io.github.mstachniuk.graphqljavaexample.user.UserTypeResolver;
import io.github.mstachniuk.graphqljavaexample.user.UsersDataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Component
public class SchemaFirstConfiguration {

    @Autowired
    private CustomerDataFetcher customerDataFetcher;
    @Autowired
    private CustomersDataFetcher customersDataFetcher;
    @Autowired
    private CompanyDataFetcher companyDataFetcher;
    @Autowired
    private OrderDataFetcher orderDataFetcher;
    @Autowired
    private ItemDataFetcher itemDataFetcher;
    @Autowired
    private CreateCustomerDataFetcher createCustomerDataFetcher;
    @Autowired
    private CreateCustomersDataFetcher createCustomersDataFetcher;
    @Autowired
    private DeleteCustomerDataFetcher deleteCustomerDataFetcher;
    @Autowired
    private UsersDataFetcher usersDataFetcher;
    @Autowired
    private SearchDataFetcher searchDataFetcher;

    public GraphQLSchema schema() {
        SchemaParser schemaParser = new SchemaParser();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("graphql/model.graphqls").getFile());
        TypeDefinitionRegistry registry = schemaParser.parse(file);
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", builder ->
                        builder.dataFetcher("customer", customerDataFetcher))
                .type("Query", builder ->
                        builder.dataFetcher("customers", customersDataFetcher))
                .type("Customer", builder ->
                        builder.dataFetcher("company", companyDataFetcher))
                .type("Customer", builder ->
                        builder.dataFetcher("orders", orderDataFetcher))
                .type("Order", builder ->
                        builder.dataFetcher("items", itemDataFetcher))
                .type("Mutation", builder ->
                        builder.dataFetcher("createCustomer", createCustomerDataFetcher))
                .type("Mutation", builder ->
                        builder.dataFetcher("createCustomers", createCustomersDataFetcher))
                .type("Mutation", builder ->
                        builder.dataFetcher("deleteCustomer", deleteCustomerDataFetcher))
                .type(TypeRuntimeWiring.newTypeWiring("User")
                        .typeResolver(new UserTypeResolver())
                        .build())
                .type("Query", builder ->
                        builder.dataFetcher("users", usersDataFetcher))
                .type(TypeRuntimeWiring.newTypeWiring("SearchResult")
                        .typeResolver(new SearchResultResolver())
                        .build())
                .type("Query", builder ->
                        builder.dataFetcher("search", searchDataFetcher))
                .build();
        return schemaGenerator.makeExecutableSchema(registry, runtimeWiring);
    }
}
