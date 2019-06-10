package io.github.mstachniuk.graphqljavaexample

import graphql.ExecutionResult
import graphql.GraphQL
import graphql.schema.GraphQLSchema
import groovy.json.JsonSlurper
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.boot.SpringApplication
import spock.lang.Ignore
import spock.lang.Specification

import static graphql.introspection.IntrospectionQuery.INTROSPECTION_QUERY
import static groovy.json.JsonOutput.toJson

class SchemaValidationSpec extends Specification {

	def "code first and schema first should be equal"() {
		given:
		def codeFirstContext = SpringApplication.run(MainCodeFirst.class, (String[]) ["--server.port=0"])
		def schemaFirstContext = SpringApplication.run(MainSchemaFirst.class, (String[]) ["--server.port=0"])

		def graphQLCodeFirst = new GraphQL(codeFirstContext.getBean(GraphQLSchema))
		def graphQLSchemaFirst = new GraphQL(schemaFirstContext.getBean(GraphQLSchema))

		when:
		ExecutionResult codeFirstResult = graphQLCodeFirst.execute(INTROSPECTION_QUERY)
		ExecutionResult schemaFirstResult = graphQLSchemaFirst.execute(INTROSPECTION_QUERY)

		then:
		def codeFirstIntroResult = toJson(codeFirstResult.data)
		def schemaFirstIntroResult = removeDeprecated(schemaFirstResult.data)

		// Uncomment for seeing  differences in better way
//		println("Code First:\n" + JsonOutput.prettyPrint(toJson(codeFirstResult.data)) + "\n--------")
//		println("Schema First:\n" + JsonOutput.prettyPrint(schemaFirstIntroResult) + "\n--------")
		JSONAssert.assertEquals(codeFirstIntroResult, schemaFirstIntroResult, false)
	}

	/**
	 * It's workaround, because of some reason Schema First return in introspection query extra `deprecated` directive
	 * what is not returned in code first.
	 * It's after migration from:
	 * com.graphql-java:graphql-spring-boot-starter:4.0.0 to
	 * com.graphql-java-kickstart:graphql-spring-boot-starter:5.2
	 */
	private def removeDeprecated(data) {
		def schemaFirstIntroResult = new JsonSlurper().parseText(toJson(data))
		def dir = schemaFirstIntroResult.__schema.directives
		println("Dir: $dir")

		for (Object o : dir) {
			if (o.name == "deprecated") {
				dir.remove(o)
				break
			}
		}
		return toJson(schemaFirstIntroResult)
	}

	@Ignore
	"code first and SPQR should be equal"() {
		given:
		def codeFirstContext = SpringApplication.run(MainCodeFirst.class, (String[]) ["--server.port=0"])
		def spqrContext = SpringApplication.run(MainSpqr.class, (String[]) ["--server.port=0"])

		def graphQLCodeFirst = new GraphQL(codeFirstContext.getBean(GraphQLSchema))
		def graphQLSpqr = new GraphQL(spqrContext.getBean(GraphQLSchema))

		when:
		ExecutionResult codeFirstResult = graphQLCodeFirst.execute(INTROSPECTION_QUERY)
		ExecutionResult spqrResult = graphQLSpqr.execute(INTROSPECTION_QUERY)

		then:
		def codeFirstIntroResult = toJson(codeFirstResult.data)
		def spqrIntroResult = toJson(spqrResult.data)
		JSONAssert.assertEquals(codeFirstIntroResult, spqrIntroResult, false)
	}
}
