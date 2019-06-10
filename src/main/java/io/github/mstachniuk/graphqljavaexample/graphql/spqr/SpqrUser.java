package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.leangen.graphql.annotations.GraphQLId;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.types.GraphQLInterface;

@GraphQLInterface(name = "User", implementationAutoDiscovery = true)
public interface SpqrUser extends SpqrSearchResult {

    @GraphQLId
    @GraphQLNonNull
    String getId();

    @GraphQLNonNull
    String getName();

    @GraphQLNonNull
    String getEmail();
}
