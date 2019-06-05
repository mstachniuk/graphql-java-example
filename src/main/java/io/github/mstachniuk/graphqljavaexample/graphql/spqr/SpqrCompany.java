package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.types.GraphQLType;

@GraphQLType(name = "Company")
public class SpqrCompany {
    private String id;
    private String name;
    private String website;

    public SpqrCompany(String id, String name, String website) {
        this.id = id;
        this.name = name;
        this.website = website;
    }

    @GraphQLQuery
    public String getId() {
        return id;
    }

    @GraphQLQuery
    public String getName() {
        return name;
    }

    @GraphQLQuery
    public String getWebsite() {
        return website;
    }
}
