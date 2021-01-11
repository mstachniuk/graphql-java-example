package io.github.mstachniuk.graphqljavaexample

import graphql.GraphQL
import graphql.schema.GraphQLSchema
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

// workaround for https://github.com/graphql-java-kickstart/graphql-spring-boot/issues/113
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = MainSchemaFirst)
class DeleteCustomerDataFetcherSpec extends Specification {

    @Autowired
    GraphQLSchema graphQLSchema

    GraphQL graphQL

    def setup() {
        graphQL = GraphQL.newGraphQL(graphQLSchema).build()
    }

    def "should delete customer by id"() {
        given:
        def query = """
            mutation {
              deleteCustomer(input: {
                id: "3"
                clientMutationId: "123"
              }) {
                customer {
                  id
                  name
                }
                clientMutationId
              }
            }
            """

        def expected = [
                "deleteCustomer": [
                        "customer"        : [
                                "id"  : "3",
                                "name": "John Doe"
                        ],
                        "clientMutationId": "123"
                ]
        ]

        when:
        def result = graphQL.execute(query)

        then:
        result.data == expected
    }

}
