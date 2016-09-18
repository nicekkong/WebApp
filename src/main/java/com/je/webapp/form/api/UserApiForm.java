package com.je.webapp.form.api;

public class UserApiForm {
	
	String id;

	public String getId() {
		return id;
	}

	public UserApiForm setId(String id) {
		this.id = id;
		return this;
	}

	@Override
	public String toString() {
		return "UserApiForm{" +
				"id=" + id +
				'}';
	}
}