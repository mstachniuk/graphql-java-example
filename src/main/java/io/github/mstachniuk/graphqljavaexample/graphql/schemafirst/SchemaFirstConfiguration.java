package io.github.mstachniuk.graphqljavaexample.graphql.schemafirst;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import io.github.mstachniuk.graphqljavaexample.company.CompanyDataFetcher;
import io.github.mstachniuk.graphqljavaexample.customer.CreateCustomerFetcher;
import io.github.mstachniuk.graphqljavaexample.customer.CreateCustomersFetcher;
import io.github.mstachniuk.graphqljavaexample.customer.CustomerFetcher;
import io.github.mstachniuk.graphqljavaexample.customer.CustomersFetcher;
import io.github.mstachniuk.graphqljavaexample.item.ItemDataFetcher;
import io.github.mstachniuk.graphqljavaexample.order.OrderDataFetcher;
import io.github.mstachniuk.graphqljavaexample.search.SearchFetcher;
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
    private CustomerFetcher customerFetcher;
    @Autowired
    private CustomersFetcher customersFetcher;
    @Autowired
    private CompanyDataFetcher companyDataFetcher;
    @Autowired
    private OrderDataFetcher orderDataFetcher;
    @Autowired
    private ItemDataFetcher itemDataFetcher;
    @Autowired
    private CreateCustomerFetcher createCustomerFetcher;
    @Autowired
    private CreateCustomersFetcher createCustomersFetcher;
    @Autowired
    private DataFetcher deleteCustomerFetcher;
    @Autowired
    private UsersDataFetcher usersDataFetcher;
    @Autowired
    private SearchFetcher searchFetcher;

    public GraphQLSchema schema() {
        SchemaParser schemaParser = new SchemaParser();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("graphql/model.graphqls").getFile());
        TypeDefinitionRegistry registry = schemaParser.parse(file);
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", builder ->
                        builder.dataFetcher("customer", customerFetcher))
                .type("Query", builder ->
                        builder.dataFetcher("customers", customersFetcher))
                .type("Customer", builder ->
                        builder.dataFetcher("company", companyDataFetcher))
                .type("Customer", builder ->
                        builder.dataFetcher("orders", orderDataFetcher))
                .type("Order", builder ->
                        builder.dataFetcher("items", itemDataFetcher))
                .type("Mutation", builder ->
                        builder.dataFetcher("createCustomer", createCustomerFetcher))
                .type("Mutation", builder ->
                        builder.dataFetcher("createCustomers", createCustomersFetcher))
                .type("Mutation", builder ->
                        builder.dataFetcher("deleteCustomer", deleteCustomerFetcher))
                .type(newTypeWiring("User")
                        .typeResolver(new UserTypeResolver())
                        .build())
                .type("Query", builder ->
                        builder.dataFetcher("users", usersDataFetcher))
                .type(newTypeWiring("SearchResult")
                        .typeResolver(new SearchResultResolver())
                        .build())
                .type("Query", builder ->
                        builder.dataFetcher("search", searchFetcher))
                .build();
        return schemaGenerator.makeExecutableSchema(registry, runtimeWiring);
    }
}
