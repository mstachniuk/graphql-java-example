package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.leangen.graphql.annotations.GraphQLId;
import io.leangen.graphql.annotations.GraphQLNonNull;
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

    @GraphQLId
    @GraphQLQuery
    @GraphQLNonNull
    public String getId() {
        return id;
    }

    @GraphQLQuery
    @GraphQLNonNull
    public String getName() {
        return name;
    }

    @GraphQLQuery
    @GraphQLNonNull
    public String getWebsite() {
        return website;
    }
}
