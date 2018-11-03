package io.github.mstachniuk.graphqljavaexample.user;

import java.util.List;

public class Moderator implements User {

	private String id;
	private String name;
	private String email;
	private List<String> permission;

	public Moderator(String id, String name, String email, List<String> permission) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.permission = permission;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getPermission() {
		return permission;
	}

	public void setPermission(List<String> permission) {
		this.permission = permission;
	}
}
