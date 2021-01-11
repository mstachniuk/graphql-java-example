package io.github.mstachniuk.graphqljavaexample;

import graphql.schema.GraphQLSchema;
import io.github.mstachniuk.graphqljavaexample.graphql.codefirst.CodeFirstConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainCodeFirst {

	@Autowired
	private CodeFirstConfiguration codeFirstConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(MainCodeFirst.class, args);
	}

	@Bean
	@Qualifier("CodeFirst")
	public GraphQLSchema schema() {
		return codeFirstConfiguration.schema();
	}
}
