package io.github.mstachniuk.graphqljavaexample.user;

import io.github.mstachniuk.graphqljavaexample.search.SearchResult;

import java.util.List;

public class Moderator extends AbstractUser implements SearchResult {

	private List<String> permissions;

	public Moderator(String id, String name, String email, List<String> permissions) {
		super(id, name, email);
		this.permissions = permissions;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
}
