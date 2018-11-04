package io.github.mstachniuk.graphqljavaexample.user;

import io.github.mstachniuk.graphqljavaexample.search.SearchResult;

import java.util.List;

public class Moderator extends AbstractUser implements SearchResult {

	private List<String> permission;

	public Moderator(String id, String name, String email, List<String> permission) {
		super(id, name, email);
		this.permission = permission;
	}

	public List<String> getPermission() {
		return permission;
	}

	public void setPermission(List<String> permission) {
		this.permission = permission;
	}
}
