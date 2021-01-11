package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.types.GraphQLType;

@GraphQLType(name = "Order")
public class SpqrOrder {
    private final String id;
    private final SpqrStatus status;

    public SpqrOrder(String id, SpqrStatus status) {
        this.id = id;
        this.status = status;
    }

    @GraphQLQuery
    public String getId() {
        return id;
    }

    @GraphQLQuery
    public SpqrStatus getStatus() {
        return status;
    }
}
