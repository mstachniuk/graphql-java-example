package io.github.mstachniuk.graphqljavaexample;

import java.io.File;

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
				.build();
		return schemaGenerator.makeExecutableSchema(registry, runtimeWiring);
	}
}
