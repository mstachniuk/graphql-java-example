package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.leangen.graphql.annotations.GraphQLEnumValue;
import io.leangen.graphql.annotations.types.GraphQLType;

@GraphQLType(name = "Status")
public enum SpqrStatus {
    @GraphQLEnumValue
    NEW,
    @GraphQLEnumValue
    CANCELED,
    @GraphQLEnumValue
    DONE
}
