package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.leangen.graphql.annotations.types.GraphQLInterface;

@GraphQLInterface(name = "User", implementationAutoDiscovery = true)
public interface SpqrUser extends SpqrSearchResult {
    String getId();
    String getName();
    String getEmail();
}
