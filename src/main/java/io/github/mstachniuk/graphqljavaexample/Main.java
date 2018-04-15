package io.github.mstachniuk.graphqljavaexample;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.FieldDataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.Map;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public GraphQLSchema schema() {
        SchemaParser schemaParser = new SchemaParser();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("graphql/model.graphqls").getFile());
        TypeDefinitionRegistry registry = schemaParser.parse(file);
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", builder ->
                        builder.dataFetcher("customers", new CustomerDataFetcher("customers"))
                                )
                .type("customers", builder ->
                        builder.dataFetcher("company", new CompanyDataFetcher("company")))
                .build();
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(registry, runtimeWiring);
        return graphQLSchema;
    }

    public Customer getCustomerById() {
        return new Customer("123", "name", "a@b.com");
    }
}

class CustomerDataFetcher extends FieldDataFetcher {

    public CustomerDataFetcher(String fieldName) {
        super(fieldName);
    }

    @Override
    public Object get(DataFetchingEnvironment environment) {
        String id = environment.getArgument("id");
        return new Customer(id, "name", "a@b.com");
    }
}

class Customer {
    private String id;
    private String name;
    private String email;

    public Customer(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}

class CompanyDataFetcher extends FieldDataFetcher {

    public CompanyDataFetcher(String fieldName) {
        super(fieldName);
    }

    @Override
    public Object get(DataFetchingEnvironment environment) {
        Map<String, Object> arguments = environment.getArguments();
        return new Company("11", "Company Corp.", "www.company.com");
    }
}

class Company {
    private String id;
    private String name;
    private String website;

    public Company(String id, String name, String website) {
        this.id = id;
        this.name = name;
        this.website = website;
    }
}
