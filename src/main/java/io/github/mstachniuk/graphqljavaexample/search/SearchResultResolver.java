package io.github.mstachniuk.graphqljavaexample.search;

import graphql.TypeResolutionEnvironment;
import graphql.schema.GraphQLObjectType;
import graphql.schema.TypeResolver;
import io.github.mstachniuk.graphqljavaexample.customer.Customer;
import io.github.mstachniuk.graphqljavaexample.user.Admin;
import io.github.mstachniuk.graphqljavaexample.user.Moderator;
import io.github.mstachniuk.graphqljavaexample.user.User;

public class SearchResultResolver implements TypeResolver {
    @Override
    public GraphQLObjectType getType(TypeResolutionEnvironment env) {
        Object javaObject = env.getObject();
        if(javaObject instanceof Customer) {
            return env.getSchema().getObjectType("Customer");
        } else if (javaObject instanceof Admin) {
            return env.getSchema().getObjectType("Admin");
        } else if (javaObject instanceof Moderator) {
            return env.getSchema().getObjectType("Moderator");
        } else {
            return env.getSchema().getObjectType("Unknownnnn");
        }
    }
}
