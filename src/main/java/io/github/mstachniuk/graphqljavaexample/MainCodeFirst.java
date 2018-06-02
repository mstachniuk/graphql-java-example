package io.github.mstachniuk.graphqljavaexample;

import static graphql.Scalars.GraphQLID;
import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLEnumType;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLInputObjectField;
import graphql.schema.GraphQLInputObjectType;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.GraphQLType;
import graphql.schema.GraphQLTypeReference;
import io.github.mstachniuk.graphqljavaexample.company.CompanyDataFetcher;
import io.github.mstachniuk.graphqljavaexample.customer.CreateCustomerFetcher;
import io.github.mstachniuk.graphqljavaexample.customer.CustomerFetcher;
import io.github.mstachniuk.graphqljavaexample.customer.CustomersFetcher;
import io.github.mstachniuk.graphqljavaexample.item.ItemDataFetcher;
import io.github.mstachniuk.graphqljavaexample.order.OrderDataFetcher;

@SpringBootApplication
public class MainCodeFirst {

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
		SpringApplication.run(MainCodeFirst.class, args);
	}

	@Bean
	@Qualifier("CodeFirst")
	public GraphQLSchema schema() {
		return GraphQLSchema.newSchema()
				.query(buildCustomerType())
				.mutation(buildMutationType())
				.build();
	}

	private GraphQLObjectType buildCustomerType() {
		GraphQLObjectType.Builder builder = GraphQLObjectType.newObject()
				.name("Query")
				.field(customerDefinition())
				.field(buildCustomersType());
		return builder.build();
	}

	private GraphQLFieldDefinition customerDefinition() {
		return GraphQLFieldDefinition.newFieldDefinition()
				.name("customer")
				.argument(GraphQLArgument.newArgument()
						.name("id")
						.type(new GraphQLNonNull(GraphQLString)))
				.type(new GraphQLNonNull(GraphQLObjectType.newObject()
						.name("Customer")
						.field(GraphQLFieldDefinition.newFieldDefinition()
								.name("id")
								.description("fields with ! are required")
								.type(new GraphQLNonNull(GraphQLID))
								.build())
						.field(GraphQLFieldDefinition.newFieldDefinition()
								.name("name")
								.type(new GraphQLNonNull(GraphQLString))
								.build())
						.field(GraphQLFieldDefinition.newFieldDefinition()
								.name("email")
								.type(new GraphQLNonNull(GraphQLString))
								.build())
						.field(GraphQLFieldDefinition.newFieldDefinition()
								.name("company")
								.type(companyDefinition())
								.dataFetcher(companyDataFetcher)
								.build())
						.field(GraphQLFieldDefinition.newFieldDefinition()
								.name("orders")
								.type(new GraphQLList(orderDefinition()))
								.dataFetcher(orderDataFetcher)
								.build())
						.build()))
				.dataFetcher(customerFetcher)
				.build();
	}

	private GraphQLObjectType companyDefinition() {
		GraphQLObjectType.Builder builder = GraphQLObjectType.newObject()
				.name("Company")
				.field(GraphQLFieldDefinition.newFieldDefinition()
						.name("id")
						.type(new GraphQLNonNull(GraphQLID))
						.build())
				.field(GraphQLFieldDefinition.newFieldDefinition()
						.name("name")
						.type(new GraphQLNonNull(GraphQLString))
						.build())
				.field(GraphQLFieldDefinition.newFieldDefinition()
						.name("website")
						.type(new GraphQLNonNull(GraphQLString))
						.build());
		return builder.build();
	}


	private GraphQLObjectType orderDefinition() {
		GraphQLObjectType.Builder builder = GraphQLObjectType.newObject()
				.name("Order")
				.field(GraphQLFieldDefinition.newFieldDefinition()
						.name("id")
						.type(new GraphQLNonNull(GraphQLID))
						.build())
				.field(GraphQLFieldDefinition.newFieldDefinition()
						.name("status")
						.type(statusDefinition())
						.build())
				.field(GraphQLFieldDefinition.newFieldDefinition()
						.name("items")
						.type(new GraphQLList(itemDefinition()))
						.dataFetcher(itemDataFetcher)
						.build());
		return builder.build();
	}

	private GraphQLEnumType statusDefinition() {
		GraphQLEnumType.Builder builder = GraphQLEnumType.newEnum()
				.name("Status")
				.value("NEW")
				.value("CANCELED")
				.value("DONE");
		return builder.build();
	}

	private GraphQLType itemDefinition() {
		GraphQLObjectType.Builder builder = GraphQLObjectType.newObject()
				.name("Item")
				.field(GraphQLFieldDefinition.newFieldDefinition()
						.name("id")
						.type(new GraphQLNonNull(GraphQLID))
						.build())
				.field(GraphQLFieldDefinition.newFieldDefinition()
						.name("name")
						.type(new GraphQLNonNull(GraphQLString))
						.build())
				.field(GraphQLFieldDefinition.newFieldDefinition()
						.name("amount")
						.type(GraphQLInt)
						.build())
				.field(GraphQLFieldDefinition.newFieldDefinition()
						.name("price")
						.type(GraphQLString)
						.build())
				.field(GraphQLFieldDefinition.newFieldDefinition()
						.name("currency")
						.type(GraphQLString)
						.build())
				.field(GraphQLFieldDefinition.newFieldDefinition()
						.name("producer")
						.type(new GraphQLTypeReference("Company"))
						.build());
		return builder.build();
	}


	private GraphQLFieldDefinition buildCustomersType() {
		GraphQLFieldDefinition.Builder builder = GraphQLFieldDefinition.newFieldDefinition()
				.name("customers")
				.type(new GraphQLNonNull(new GraphQLList(new GraphQLTypeReference("Customer"))))
				.dataFetcher(customersFetcher);
		return builder.build();
	}

	private GraphQLObjectType buildMutationType() {
		GraphQLFieldDefinition.Builder builder = GraphQLFieldDefinition.newFieldDefinition()
				.name("createCustomer")
				.argument(GraphQLArgument.newArgument()
						.name("input")
						.type(GraphQLInputObjectType.newInputObject()
								.name("CreateCustomerInput")
								.field(GraphQLInputObjectField.newInputObjectField()
										.name("name")
										.type(GraphQLString)
										.build())
								.field(GraphQLInputObjectField.newInputObjectField()
										.name("email")
										.type(GraphQLString)
										.build())
								.field(GraphQLInputObjectField.newInputObjectField()
										.name("clientMutationId")
										.type(new GraphQLNonNull(GraphQLString))
										.build())
								.build()))
				.type(new GraphQLNonNull(GraphQLObjectType.newObject()
						.name("CreateCustomerPayload")
						.field(GraphQLFieldDefinition.newFieldDefinition()
								.name("customer")
								.type(new GraphQLTypeReference("Customer"))
								.build())
						.field(GraphQLFieldDefinition.newFieldDefinition()
								.name("clientMutationId")
								.type(new GraphQLNonNull(GraphQLString))
								.build())
						.build()))
				.dataFetcher(createCustomerFetcher);
		GraphQLObjectType.Builder mutation = GraphQLObjectType.newObject()
				.name("Mutation")
				.field(builder);
		return mutation.build();
	}
}
