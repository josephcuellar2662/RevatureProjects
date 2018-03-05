package com.revature.project.BankingApplication;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
    	//LoggingUtil.logDebug("");
    	TextFiles files = new TextFiles();
    	boolean banking = true;
    	Scanner in = new Scanner(System.in);
    	String firstName = "", lastName="";
    	String username="", password="";
    	String state = "-2";
    	User adminUser = new User("admin", "john", "doe", "theAdmin", "1234");
    	Account adminAccount = new Account(adminUser, "admin");
    	files.serializeUser(adminUser, "Users");
    	Account addAccount = null;
    	Account account = null;
    	double amount = 0.0;
    	User user = null;
    	files.serializeAccount(adminAccount, "Accounts");
    	files.deserializeAccount("theAdmin", "Accounts","admin");
    
    	//files.accountExist("");
    	System.out.println("Welcome to Banking With Revature Banking!");
    	
    	//logger.debug("Hello");
    	while(banking){
    		// Get user type ( User needs to choose customer, employee, admin, or exit program)
        	while(validInput(state) && Integer.parseInt(state) != 1 && Integer.parseInt(state) != 2 && Integer.parseInt(state) != 3 && Integer.parseInt(state) != -1  ){
            	System.out.print("Who are you? (Customer = 1, Employee = 2, Admin = 3, Exit = -1): ");
            	state = in.next();
            	if(state.equals("-1")){
            		banking = false;
            	} 
            	if(!validInput(state)){
            		state = "-2";
            	}
        	} 
        	if(state.equals("1")){ //Customer
        		System.out.print("Press 1 register or press 2 to login: ");
        		state = in.next();
        		if(state.equalsIgnoreCase("1")){
        			//Register
        			System.out.print("First Name: ");
        			firstName = in.next();
        			System.out.print("Last Name: ");
        			lastName = in.next();
        			System.out.print("Username: ");
        			username = in.next();
        			System.out.print("Password: ");
        			password = in.next();
        			user = new User("customer", firstName, lastName, username, password);
        			files.serializeUser(user, "Users");
        			state = "2";
        		}
        		//Login
        		if(state.equals("2")){
        			boolean loggedIn = true;
        			while(loggedIn){
        				System.out.println("Login as a Customer");
        				System.out.print("Username: ");
            			username = (String)in.next();
            			user = files.deserializeUser(username, "Users");
            			System.out.print("Password: ");
            			password = in.next();
            			System.out.println();
            			if(user != null && user.getPassword().equals(password)){
            				while(loggedIn){
            					System.out.println("Add Savings Account (1)");
            					System.out.println("Add Checkings Account (2)");
            					System.out.println("Deposit (3)");
            					System.out.println("Withdraw (4)");
            					System.out.println("View account information (5)");
            					System.out.println("Transfer (6)");
            					System.out.println("Add user to account (7)");
            					System.out.println("Previous menu (-1)");
            					state = in.next();

            					if(state.equals("1")){
            						System.out.print("Enter amount for your savings account: ");
            						amount = in.nextDouble();
            						// public Account(User user, String type, double balance)
            						addAccount = new Account(user, "savings",  amount);
            						System.out.println(addAccount.getBalance());
            						files.serializeAccount(addAccount, "Accounts");
            						addAccount = files.deserializeAccount(username, "Accounts","savings");
            						System.out.println("Available balance: " + addAccount.getBalance());
            						System.out.println();
            					}
            					else if(state.equals("2")){
            						System.out.print("Enter amount for your checkings account: ");
            						amount = in.nextDouble();
            						// public Account(User user, String type, double balance)
            						addAccount = new Account(user, "checkings",  amount);
            						files.serializeAccount(addAccount, "Accounts");
            						addAccount = files.deserializeAccount(username, "Accounts","checkings");
            						System.out.println("Available balance: " + addAccount.getBalance());
            						System.out.println();
            					}
            					else if(state.equals("3")){
            						System.out.println("Amount to deposit: ");
            						amount = in.nextDouble();
            						System.out.println("Deposit to checkings (1)");
            						System.out.println("Deposit to savings (2)");
            						String userInput = in.next();
            						if(userInput.equals("1")){
            							if(files.accountExist(username, "checkings")){
            								account = files.deserializeAccount(username, "Accounts","checkings");
            								account.deposit(amount);
            								files.serializeAccount(account, "Accounts");
            								System.out.println("Available balance in your checkings account: " + account.getBalance());
            								System.out.println();
            							}
            							else{
            								System.out.println("You do not have a checkings account");
            								System.out.println();
            							}
            						}
            						else if(userInput.equals("2")){
            							if(files.accountExist(username, "savings")){
            								account = files.deserializeAccount(username, "Accounts","savings");
            								account.deposit(amount);
            								files.serializeAccount(account, "Accounts");
            								System.out.println("Available balance in your savings account: " + account.getBalance());
            								System.out.println();
            							}
            							else{
            								System.out.println("You do not have a savings account");
            								System.out.println();
            							}
            						}
            					}         					
            					else if(state.equals("4")){
            						System.out.println("Amount: ");
            						amount = in.nextDouble();
            						System.out.println("Withdraw from checkings (1)");
            						System.out.println("Withdraw from savings (2)");
            						String userInput = in.next();
            						if(userInput.equals("1")){
            							if(files.accountExist(username, "checkings")){
            								account = files.deserializeAccount(username, "Accounts","checkings");
            								if(account.withdraw(amount)){
            									System.out.println("Available balance in your checkings account: " + account.getBalance());
            									files.serializeAccount(account, "Accounts");
                								System.out.println();
            								} else {
            									System.out.println("Insufficient funds");
            									System.out.println();
            								}
            							}
            							else{
            								System.out.println("You do not have a checkings account");
            								System.out.println();
            							}
            						}
            						else if(userInput.equals("2")){
            							if(files.accountExist(username, "savings")){
            								account = files.deserializeAccount(username, "Accounts","savings");
            								if(account.withdraw(amount)){
            									System.out.println("Available balance in your savings account: " + account.getBalance());
            									files.serializeAccount(account, "Accounts");
                								System.out.println();
            								} else {
            									System.out.println("Insufficient funds");
            									System.out.println();
            								}
            							}
            							else{
            								System.out.println("You do not have a checkings account");
            								System.out.println();
            							}
            						}
            					}
            					else if(state.equals("5")){
            						System.out.println("View checkings account (1)");
            						System.out.println("View savings account (2)");
            						String userInput = in.next();
            						if(userInput.equals("1")){
            							if(files.accountExist(username, "checkings")){
            								account = files.deserializeAccount(username, "Accounts","checkings");
            								System.out.println(account);
            								System.out.println();
                						} else {
                							System.out.println("Checkings account does not exist");
                							System.out.println();
                						}
            						}
            						else if(userInput.equals("2")){
            							if(files.accountExist(username, "savings")){
            								account = files.deserializeAccount(username, "Accounts","savings");
            								System.out.println(account);
            								System.out.println();
                						} else {
                							System.out.println("Savings account does not exist");
                							System.out.println();
                						}
            						}		
            					}
            					else if(state.equals("6")){
            						System.out.println("Amount: ");
            						amount = in.nextDouble();
            						System.out.println("Transfer from checkings to savings (1)");
            						System.out.println("Transfer from savings to checkings (2)");
            						String userInput = in.next();
            						if(userInput.equals("1")){
            							if(files.accountExist(username, "checkings") && (files.accountExist(username, "savings"))){
            								account = files.deserializeAccount(username, "Accounts","checkings");
            								if(account.getBalance() > amount){
            									account.withdraw(amount);
            									files.serializeAccount(account, "Accounts");
            									System.out.println("Available balance in checkings account: " + account.getBalance());
            									account = files.deserializeAccount(username,"Accounts","savings");
            									account.deposit(amount);
            									files.serializeAccount(account, "Accounts");
            									System.out.println("Available balance in savings account: " + account.getBalance());
            									System.out.println();
            								} else {
            									System.out.println("Insufficent funds");
            									System.out.println();
            								}
            							} else {
            								System.out.println("Transaction not possible. You are missing a checkings or savings account or both");
            								System.out.println();
            							}
            						}
            						else if(userInput.equals("2")){
            							if(files.accountExist(username, "checkings") && (files.accountExist(username, "savings"))){
            								account = files.deserializeAccount(username,"Accounts", "savings");
            								if(account.getBalance() > amount){
            									account.withdraw(amount);
            									files.serializeAccount(account, "Accounts");
            									System.out.println("Available balance in savings account: " + account.getBalance());
            									account = files.deserializeAccount(username, "Accounts","checkings" );
            									account.deposit(amount);
            									files.serializeAccount(account, "Accounts");
            									System.out.println("Available balance in checkings account: " + account.getBalance());
            									System.out.println();
            								} else {
            									System.out.println("Insufficent funds");
            									System.out.println();
            								}
            							} else {
            								System.out.println("Transaction not possible. You are missing a checkings or savings account or both");
            								System.out.println();
            							}
            						}
            						
            					}
            					else if(state.equals("7")){
            						System.out.print("First Name: "); firstName = in.next();
            						System.out.print("Last Name: "); lastName = in.next();
            						System.out.print("Username: "); String newUser = in.next();
            						System.out.print("Password: "); password = in.next();
            						user = new User("customer", firstName, lastName, newUser, password);
            						System.out.println("Add user to checkings account (1)");
            						System.out.println("Add user to savings account (2)");
            						String userInput = in.next();
            						if(userInput.equals("1")){
            							if(files.accountExist(username, "checkings")){
            								System.out.println("Username: " + username);
            								account = files.deserializeAccount(username, "Accounts", "checkings");
            								System.out.println("Number of users: " + account.getNumberOfUsers());
            								if(account.addUser(user)){
            									files.deleteFile(username, "checkings");
            									files.serializeAccount(account, "Accounts");
            									files.serializeUser(user, "Users");
            									System.out.println("Account updated: " + files.deserializeAccount(username, "Accounts", "checkings"));
            									System.out.println();
            								} else {
            									System.out.println("Cannot have more then two users in a account");
            									System.out.println();
            								}
            							}
            						}
            						else if(userInput.equals("2")){
            							if(files.accountExist(username, "savings")){
            								System.out.println("Username: " + username);
            								account = files.deserializeAccount(username, "Accounts", "savings");
            								System.out.println("Number of users: " + account.getNumberOfUsers());
            								if(account.addUser(user)){
            									files.deleteFile(username, "savings");
            									files.serializeAccount(account, "Accounts");
            									files.serializeUser(user, "Users");
            									System.out.println("Account updated: " + files.deserializeAccount(username, "Accounts", "savings"));
            									System.out.println();
            								} else {
            									System.out.println("Cannot have more then two users in a account");
            									System.out.println();
            								}
            							}
            						}
            					}
            					else if(state.equals("-1")){
            						loggedIn = false;
            					}
            				}
            				
            			} else {
            				System.out.println("Invalid username and password");
            				state = "-1";
            				loggedIn = false;
            			}
        			}
        		}
        		//Exit
        		if(state.equals("-1")){
        			state = "-2";
        		}
        	}
        	else if(state.equals("2")){ //Employee
        
        		files.printAllFiles();
        		System.out.println("Previous menu (-1)");
        		String userInput = in.next();
        		if(userInput.equals("-1")){
        			state = "-2";
        		}	
        	}
        	else if(state.equals("3")){ //Admin
        		System.out.println("Login as an Admin");
        		System.out.print("username: ");
        		username = in.next();
        		user = files.deserializeUser(username, "Users");
        		System.out.print("password: ");
        		password = in.next();
        		if(user != null && user.getType().equals("admin") && user.getPassword().equals(password)){
        			System.out.println("View account info (1)");
        			System.out.println("Delete account (2)");
        			System.out.println("Deposit to an account (3)");
        			System.out.println("Withdraw from an account (4)");
        			System.out.println("Transfer (5)");
        			System.out.println("Previous menu (-1)");
        			String option = in.next();
        			if(option.equals("1")){
        				files.printAllFiles();
        				System.out.println("Previous menu (-1)");
        				option = in.next();
        			}
        			else if(option.equals("2")){
        				System.out.print("Username: ");
        				username = in.next();
        				System.out.print("Account type (checkings, savings): ");
        				String accType = in.next();
        				files.deleteFile(username, accType);
        				files.printAllFiles();
        				System.out.println("Previous menu (-1)");
        				option = in.next();
        			}
        			else if(option.equals("3")){
        				System.out.print("Amount: ");
        				amount = in.nextDouble();
        				System.out.print("Username: ");
        				username = in.next();
        				System.out.println("Account type (checkings or savings)");
        				String accType = in.next();
        				if(files.accountExist(username, accType)){
        					account = files.deserializeAccount(username, "Accounts", accType);
        					account.deposit(amount);
        					files.serializeAccount(account, "Accounts");
        					System.out.println("Deposit successful");
        					System.out.println();
        				}
        			}
        			else if(option.equals("4")){
        				System.out.print("Amount: ");
        				amount = in.nextDouble();
        				System.out.print("Username: ");
        				username = in.next();
        				System.out.println("Account type (checkings or savings)");
        				String accType = in.next();
        				if(files.accountExist(username, accType)){
        					account = files.deserializeAccount(username, "Accounts", accType);
        					if(account.withdraw(amount)){
        						files.serializeAccount(account, "Accounts");
        						System.out.println("Withdrawal successful");
        						System.out.println();
        					} else {
        						System.out.println("Insufficient funds");
        						System.out.println();
        					}
        				}
        			}
        			else if(option.equals("5")){
						System.out.print("Amount: ");
						amount = in.nextDouble();
						System.out.print("Username: ");
						username = in.next();
						
						System.out.println("Transfer from checkings to savings (1)");
						System.out.println("Transfer from savings to checkings (2)");
						String userInput = in.next();
						if(userInput.equals("1")){
							if(files.accountExist(username, "checkings") && (files.accountExist(username, "savings"))){
								account = files.deserializeAccount(username, "Accounts","checkings");
								if(account.getBalance() > amount){
									account.withdraw(amount);
									files.serializeAccount(account, "Accounts");
									System.out.println("Available balance in checkings account: " + account.getBalance());
									account = files.deserializeAccount(username,"Accounts","savings");
									account.deposit(amount);
									files.serializeAccount(account, "Accounts");
									System.out.println("Available balance in savings account: " + account.getBalance());
									System.out.println();
								} else {
									System.out.println("Insufficent funds");
									System.out.println();
								}
							} else {
								System.out.println("Transaction not possible. You are missing a checkings or savings account or both");
								System.out.println();
							}
						}
						else if(userInput.equals("2")){
							if(files.accountExist(username, "checkings") && (files.accountExist(username, "savings"))){
								account = files.deserializeAccount(username,"Accounts", "savings");
								if(account.getBalance() > amount){
									account.withdraw(amount);
									files.serializeAccount(account, "Accounts");
									System.out.println("Available balance in savings account: " + account.getBalance());
									account = files.deserializeAccount(username, "Accounts","checkings" );
									account.deposit(amount);
									files.serializeAccount(account, "Accounts");
									System.out.println("Available balance in checkings account: " + account.getBalance());
									System.out.println();
								} else {
									System.out.println("Insufficent funds");
									System.out.println();
								}
							} else {
								System.out.println("Transaction not possible. You are missing a checkings or savings account or both");
								System.out.println();
							}
						}
        			}
        			else if(option.equals("-1")){
        				state = "-2";
        			}
        		}
        	}
    	}
    }
    public static boolean validInput(String input){
    	try{
    		Integer.parseInt(input);
    	} catch (NumberFormatException e){
    		//System.out.println("t");
    		return false;
    	}
    	return true;
    }
}
