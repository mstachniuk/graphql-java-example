package io.github.mstachniuk.graphqljavaexample.user;

import graphql.TypeResolutionEnvironment;
import graphql.schema.GraphQLObjectType;
import graphql.schema.TypeResolver;

public class UserTypeResolver implements TypeResolver {
	@Override
	public GraphQLObjectType getType(TypeResolutionEnvironment env) {
		Object javaObject = env.getObject();
		if (javaObject instanceof Admin) {
			return env.getSchema().getObjectType("Admin");
		} else if (javaObject instanceof Moderator) {
			return env.getSchema().getObjectType("Moderator");
		} else {
			return env.getSchema().getObjectType("UNKNOWN");
		}
	}
}
