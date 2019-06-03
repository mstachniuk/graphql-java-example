package io.github.mstachniuk.graphqljavaexample;

import graphql.schema.GraphQLSchema;
import io.github.mstachniuk.graphqljavaexample.graphql.schemafirst.SchemaFirstConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainSchemaFirst {

	@Autowired
	private SchemaFirstConfiguration schemaFirstConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(MainSchemaFirst.class, args);
	}

	@Bean
	@Qualifier("SchemaFirst")
	public GraphQLSchema schema() {
		return schemaFirstConfiguration.schema();
	}
}
