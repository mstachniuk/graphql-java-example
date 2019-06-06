package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpqrConfiguration {

    @Autowired
    private SpqrCustomerQuery spqrCustomerQuery;
    @Autowired
    private SpqrCompanyQuery spqrCompanyQuery;
    @Autowired
    private SpqrOrderQuery spqrOrderQuery;
    @Autowired
    private SpqrItemQuery spqrItemQuery;
    @Autowired
    private SpqrUserQuery spqrUserQuery;
    @Autowired
    private SpqrSearchQuery spqrSearchQuery;

    public GraphQLSchema schema() {
        GraphQLSchema schema = new GraphQLSchemaGenerator()
                .withBasePackages("io.github.mstachniuk.graphqljavaexample")
                .withOperationsFromSingletons(spqrCustomerQuery,
                        spqrCompanyQuery,
                        spqrOrderQuery,
                        spqrItemQuery,
                        spqrUserQuery,
                        spqrSearchQuery)
                .generate();

        return schema;
    }
}
