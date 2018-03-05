package com.revature.project.BankingApplication;

import java.io.Serializable;

public class Login implements Serializable{
	private String username;
	private String password;

	public Login(String username,
			String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean login(String username, String password){
		return (this.username.equals(username) && this.password.equals(password));
	}
	
		
}
