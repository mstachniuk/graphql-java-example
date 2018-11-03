package io.github.mstachniuk.graphqljavaexample;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

import java.io.File;

import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
import io.github.mstachniuk.graphqljavaexample.user.UserTypeResolver;
import io.github.mstachniuk.graphqljavaexample.user.UsersFetcher;

@SpringBootApplication
public class Main {

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
	private UsersFetcher usersFetcher;

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	@Qualifier("SchemaFirst")
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
						builder.dataFetcher("users", usersFetcher))
				.build();
		return schemaGenerator.makeExecutableSchema(registry, runtimeWiring);
	}
}
