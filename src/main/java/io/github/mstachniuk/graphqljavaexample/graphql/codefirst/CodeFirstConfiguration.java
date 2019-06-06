package io.github.mstachniuk.graphqljavaexample.graphql.codefirst;

import graphql.schema.*;
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

import java.util.LinkedHashSet;
import java.util.Set;

import static graphql.Scalars.*;

@Component
public class CodeFirstConfiguration {

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
        return GraphQLSchema.newSchema()
                .query(buildCustomerType())
                .mutation(buildMutationType())
                .additionalTypes(userImplementationDefinitions())
                .build();
    }

    private GraphQLObjectType buildCustomerType() {
        GraphQLObjectType.Builder builder = GraphQLObjectType.newObject()
                .name("Query")
                .field(customerDefinition())
                .field(customersDefinition())
                .field(usersDefinition())
                .field(searchDefinition());
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
                                .description("fields with ! are not null")
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
                .dataFetcher(customerDataFetcher)
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


    private GraphQLFieldDefinition customersDefinition() {
        GraphQLFieldDefinition.Builder builder = GraphQLFieldDefinition.newFieldDefinition()
                .name("customers")
                .type(new GraphQLNonNull(new GraphQLList(new GraphQLTypeReference("Customer"))))
                .dataFetcher(customersDataFetcher);
        return builder.build();
    }

    private GraphQLObjectType buildMutationType() {
        GraphQLFieldDefinition.Builder createCustomerBuilder = createCustomerBuilder();
        GraphQLFieldDefinition.Builder createCustomersBuilder = createCustomersBuilder();
        GraphQLFieldDefinition.Builder deleteCustomerBuilder = deleteCustomerBuilder();
        GraphQLObjectType.Builder mutation = GraphQLObjectType.newObject()
                .name("Mutation")
                .field(createCustomerBuilder)
                .field(createCustomersBuilder)
                .field(deleteCustomerBuilder);
        return mutation.build();
    }

    private GraphQLFieldDefinition.Builder createCustomerBuilder() {
        return GraphQLFieldDefinition.newFieldDefinition()
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
                .dataFetcher(createCustomerDataFetcher);
    }

    private GraphQLFieldDefinition.Builder createCustomersBuilder() {
        return GraphQLFieldDefinition.newFieldDefinition()
                .name("createCustomers")
                .argument(GraphQLArgument.newArgument()
                        .name("input")
                        .type(GraphQLInputObjectType.newInputObject()
                                .name("CreateCustomersInput")
                                .field(GraphQLInputObjectField.newInputObjectField()
                                        .name("customers")
                                        .type(new GraphQLList(GraphQLInputObjectType.newInputObject()
                                                .name("CreateCustomer")
                                                .field(GraphQLInputObjectField.newInputObjectField()
                                                        .name("name")
                                                        .type(GraphQLString)
                                                        .build())
                                                .field(GraphQLInputObjectField.newInputObjectField()
                                                        .name("email")
                                                        .type(GraphQLString)
                                                        .build())
                                                .build()))
                                        .build())
                                .field(GraphQLInputObjectField.newInputObjectField()
                                        .name("clientMutationId")
                                        .type(new GraphQLNonNull(GraphQLString))
                                        .build())
                                .build()))
                .type(new GraphQLNonNull(GraphQLObjectType.newObject()
                        .name("CreateCustomersPayload")
                        .field(GraphQLFieldDefinition.newFieldDefinition()
                                .name("customers")
                                .type(new GraphQLList(new GraphQLTypeReference("Customer")))
                                .build())
                        .field(GraphQLFieldDefinition.newFieldDefinition()
                                .name("clientMutationId")
                                .type(new GraphQLNonNull(GraphQLString))
                                .build())
                        .build()))
                .dataFetcher(createCustomersDataFetcher);
    }

    private GraphQLFieldDefinition.Builder deleteCustomerBuilder() {
        return GraphQLFieldDefinition.newFieldDefinition()
                .name("deleteCustomer")
                .argument(GraphQLArgument.newArgument()
                        .name("input")
                        .type(GraphQLInputObjectType.newInputObject()
                                .name("DeleteCustomerInput")
                                .field(GraphQLInputObjectField.newInputObjectField()
                                        .name("id")
                                        .type(new GraphQLNonNull(GraphQLID))
                                        .build())
                                .field(GraphQLInputObjectField.newInputObjectField()
                                        .name("clientMutationId")
                                        .type(new GraphQLNonNull(GraphQLString))
                                        .build())
                                .build()))
                .type(new GraphQLNonNull(GraphQLObjectType.newObject()
                        .name("DeleteCustomerPayload")
                        .field(GraphQLFieldDefinition.newFieldDefinition()
                                .name("customer")
                                .type(new GraphQLTypeReference("Customer"))
                                .build())
                        .field(GraphQLFieldDefinition.newFieldDefinition()
                                .name("clientMutationId")
                                .type(new GraphQLNonNull(GraphQLString))
                                .build())
                        .build()))
                .dataFetcher(deleteCustomerDataFetcher);
    }

    private GraphQLFieldDefinition usersDefinition() {
        return GraphQLFieldDefinition.newFieldDefinition()
                .name("users")
                .type(new GraphQLList(userInterfaceDefinition()))
                .dataFetcher(usersDataFetcher)
                .build();
    }

    private GraphQLInterfaceType userInterfaceDefinition() {
        return GraphQLInterfaceType.newInterface()
                .name("User")
                .field(GraphQLFieldDefinition.newFieldDefinition()
                        .name("id")
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
                .typeResolver(new UserTypeResolver())
                .build();
    }

    private Set<GraphQLType> userImplementationDefinitions() {
        GraphQLObjectType moderator = GraphQLObjectType.newObject()
                .name("Moderator")
                .withInterface(new GraphQLTypeReference("User"))
                .field(GraphQLFieldDefinition.newFieldDefinition()
                        .name("id")
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
                        .name("permissions")
                        .type(new GraphQLList(GraphQLString))
                        .build())
                .build();

        GraphQLObjectType admin = GraphQLObjectType.newObject()
                .name("Admin")
                .withInterface(new GraphQLTypeReference("User"))
                .field(GraphQLFieldDefinition.newFieldDefinition()
                        .name("id")
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
                        .name("superAdmin")
                        .type(new GraphQLNonNull(GraphQLBoolean))
                        .build())
                .build();

        Set<GraphQLType> result = new LinkedHashSet<>();
        result.add(admin);
        result.add(moderator);
        return result;
    }

    private GraphQLFieldDefinition searchDefinition() {
        return GraphQLFieldDefinition.newFieldDefinition()
                .name("search")
                .argument(GraphQLArgument.newArgument()
                        .name("input")
                        .type(GraphQLString)
                        .build())
                .type(new GraphQLList(GraphQLUnionType.newUnionType()
                        .name("SearchResult")
                        .possibleType(new GraphQLTypeReference("Customer"))
                        .possibleType(new GraphQLTypeReference("Admin"))
                        .possibleType(new GraphQLTypeReference("Moderator"))
                        .typeResolver(new SearchResultResolver())
                        .build()))
                .dataFetcher(searchDataFetcher)
                .build();
    }
}
