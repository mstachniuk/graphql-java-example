package io.github.mstachniuk.graphqljavaexample.user;

import java.util.List;

public class Moderator extends AbstractUser {

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
