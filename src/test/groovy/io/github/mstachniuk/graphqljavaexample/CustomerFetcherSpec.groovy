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
class CustomerFetcherSpec extends Specification {

    @Autowired
    GraphQLSchema graphQLSchema

    GraphQL graphQL

    def setup() {
        graphQL = GraphQL.newGraphQL(graphQLSchema).build()
    }

    def "should get customer by id"() {
        given:
        def query = """
            {
              customer(id: "2") {
                id
                name
                email
                company {
                  id
                  name
                  website
                }
              }
            }
            """

        def expected = [
                "customer": [
                        "id"     : "2",
                        "name"   : "name",
                        "email"  : "a@b.com",
                        "company": [
                                "id"     : "211",
                                "name"   : "Company Corp.",
                                "website": "www.company.com"
                        ]
                ]
        ]

        when:
        def result = graphQL.execute(query)

        then:
        result.data == expected
    }

}
