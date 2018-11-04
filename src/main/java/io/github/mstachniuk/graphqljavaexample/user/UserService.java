package io.github.mstachniuk.graphqljavaexample.user;

import static java.util.Arrays.asList;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	public List<User> getUsers() {
		Admin admin = new Admin("777", "Admin a", "admin@me.com", true);
		Moderator moderator = new Moderator("888", "Moderator", "mod@me.com", asList("Delete Customer", "Delete comment"));

		return asList(admin, moderator);
	}
}
