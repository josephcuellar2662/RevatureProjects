package com.revature.project.BankingApplication;

import java.io.Serializable;

public class User extends Login implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	private String firstName;
	private String lastName;

	
	
	public User(String type, String firstName, String lastName, String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
		this.type = type;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "User [type=" + type + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", Username=" + this.getUsername() + "]";
	}
	
	
}
