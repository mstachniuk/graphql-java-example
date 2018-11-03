package io.github.mstachniuk.graphqljavaexample.user;

public class Admin implements User {

	private String id;
	private String name;
	private String email;
	private boolean superAdmin;

	public Admin(String id, String name, String email, boolean superAdmin) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.superAdmin = superAdmin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isSuperAdmin() {
		return superAdmin;
	}

	public void setSuperAdmin(boolean superAdmin) {
		this.superAdmin = superAdmin;
	}
}
