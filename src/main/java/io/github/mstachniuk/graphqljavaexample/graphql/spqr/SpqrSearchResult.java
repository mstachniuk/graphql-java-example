package io.github.mstachniuk.graphqljavaexample.graphql.spqr;


import io.github.mstachniuk.graphqljavaexample.user.Moderator;
import io.leangen.graphql.annotations.types.GraphQLUnion;

@GraphQLUnion(name = "SearchResult", possibleTypes = {SpqrCustomer.class, SpqrAdmin.class, Moderator.class})
public interface SpqrSearchResult {
}
