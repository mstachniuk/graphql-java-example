package io.github.mstachniuk.graphqljavaexample

import graphql.ExecutionResult
import graphql.GraphQL
import graphql.schema.GraphQLSchema
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.boot.SpringApplication
import spock.lang.Specification

import static graphql.introspection.IntrospectionQuery.INTROSPECTION_QUERY
import static groovy.json.JsonOutput.toJson

class SchemaValidationSpec extends Specification {

	def "code first and schema first should be equals"() {
		given:
		def codeFirstContext = SpringApplication.run(MainCodeFirst.class, (String[]) ["--server.port=0"])
		def schemaFirstContext = SpringApplication.run(MainSchemaFirst.class, (String[]) ["--server.port=0"])

		def graphQLSchemaFirst = new GraphQL(schemaFirstContext.getBean(GraphQLSchema))
		def graphQLCodeFirst = new GraphQL(codeFirstContext.getBean(GraphQLSchema))

		when:
		ExecutionResult schemaFirstResult = graphQLSchemaFirst.execute(INTROSPECTION_QUERY)
		ExecutionResult codeFirstResult = graphQLCodeFirst.execute(INTROSPECTION_QUERY)

		then:
		// Uncomment for seeing  differences in better way
//		prettyPrint(toJson(codeFirstResult.data)) == prettyPrint(toJson(schemaFirstResult.data))
		JSONAssert.assertEquals(toJson(codeFirstResult.data), toJson(schemaFirstResult.data), false)
	}
}
