package io.github.mstachniuk.graphqljavaexample.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.PropertyDataFetcher;

@Component
public class UsersFetcher extends PropertyDataFetcher<List<User>> {

	@Autowired
	private UserService userService;

	public UsersFetcher() {
		super("users");
	}

	@Override
	public List<User> get(DataFetchingEnvironment environment) {
		return userService.getUsers();
	}
}
