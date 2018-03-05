package com.revature.project.BankingApplication;

import java.io.Serializable;
import java.util.Arrays;

public class Account implements Transaction, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User users[] = new User[2];
	private String type;
	private double balance;
	private int numberOfUsers = 0;

	public Account(User user, String type){
		super();
		this.users[0] = user;
		this.numberOfUsers++;
		this.type = type;
		this.balance = 0;
	}
	
	public Account(User user, String type, double balance){
		super();
		this.users[numberOfUsers] = user;
		this.numberOfUsers++;
		this.type = type;
		this.balance = balance;
	}

	public User[] getUsers() {
		return users;
	}

	public void setUsers(User[] users) {
		this.users = users;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void deposit(double amount){
		this.balance += amount;
	}
	public boolean withdraw(double amount){
		if(this.balance > amount){
			this.balance -= amount;
			return true;
		}
		return false;
	}
	
	public boolean transfer(Account from, Account to, double amount){
		if(from.getBalance() > amount){
			from.withdraw(amount);
			to.deposit(amount);
			return true;
		}
		return false;
	}
	
	public boolean addUser(User user){
		if(this.numberOfUsers < 2){
			this.users[this.numberOfUsers] = user;
			numberOfUsers++;
			return true;
		}
		return false;
	}
	
	public int getNumberOfUsers() {
		return numberOfUsers;
	}

	public void setNumberOfUsers(int numberOfUsers) {
		this.numberOfUsers = numberOfUsers;
	}

	public String getUsernames(){
		String usernames = "";
		for(int i=0; i<this.users.length; i++){
			if(users[i] != null){
				usernames += users[i].getUsername();
			}
		}
		return usernames;
	}
	
	@Override
	public String toString() {
		return "Account [users=" + Arrays.toString(users) + ", type=" + type
				+ ", balance=" + balance + "]";
	}

	
}
