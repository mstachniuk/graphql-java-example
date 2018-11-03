package io.github.mstachniuk.graphqljavaexample.user;

public class Admin extends AbstractUser {

	private boolean superAdmin;

	public Admin(String id, String name, String email, boolean superAdmin) {
		super(id, name, email);
		this.superAdmin = superAdmin;
	}

	public boolean isSuperAdmin() {
		return superAdmin;
	}

	public void setSuperAdmin(boolean superAdmin) {
		this.superAdmin = superAdmin;
	}
}
