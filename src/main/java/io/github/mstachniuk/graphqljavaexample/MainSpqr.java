package io.github.mstachniuk.graphqljavaexample;

import graphql.schema.GraphQLSchema;
import io.github.mstachniuk.graphqljavaexample.graphql.spqr.SpqrConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainSpqr {

	@Autowired
	private SpqrConfiguration spqrConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(MainSpqr.class, args);
	}

	@Bean
	@Qualifier("Spqr")
	public GraphQLSchema schema() {
		return spqrConfiguration.schema();
	}
}
