package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.types.GraphQLType;

@GraphQLType(name = "Customer")
public class SpqrCustomer implements SpqrSearchResult {
    private String id;
    private String name;
    private String email;

    public SpqrCustomer(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @GraphQLQuery(name = "id", description = "fields with ! are not null")
    public String getId() {
        return id;
    }

    @GraphQLQuery
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
