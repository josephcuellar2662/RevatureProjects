package com.revature.project.BankingApplication;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    // Test Account Class
    public void testGetType(){
    	User user = new User("customer", "Joseph", "Cuellar", "jecuellar", "1234");
    	Account acc = new Account(user, "checkings", 500.0);
    	acc.deposit(500);
    	assertEquals("checkings", acc.getType());
    }
    
    public void testAddUserToAccount(){
    	User user = new User("customer", "Joseph", "Cuellar", "jecuellar", "1234");
    	User user2 = new User("customer", "Lily", "Cuellar", "lcuellar", "1234");
    	Account acc = new Account(user, "checkings", 500.0);
    	acc.addUser(user2);
    	acc.deposit(500);
    	assertEquals("jecuellarlcuellar", acc.getUsernames());
    }
    
    public void testDepositAccount(){
    	User user = new User("customer", "Joseph", "Cuellar", "jecuellar", "1234");
    	Account acc = new Account(user, "checkings", 500.0);
    	acc.deposit(500);
    	assertEquals(1000.0, acc.getBalance());
    }
    
    public void testWithdrawAccount(){
    	// User(String type, String firstName, String lastName, String username, String password)
    	User user = new User("customer", "Joseph", "Cuellar", "jecuellar", "1234");
    	// public Account(User user, String type, double balance)
    	Account acc = new Account(user, "checkings", 5000.0);
    	acc.withdraw(500);
    	assertEquals(4500.0, acc.getBalance());
    }
    
    
    public void testGetBalance(){
    	// User(String type, String firstName, String lastName, String username, String password)
    	User user = new User("customer", "Joseph", "Cuellar", "jecuellar", "1234");
    	// public Account(User user, String type, double balance)
    	Account acc = new Account(user, "checkings", 5000.0);
    	assertEquals(5000.0, acc.getBalance());
    }
    
    //Test User Class
    public void testGetUsername(){
    	User user = new User("customer", "Joseph", "Cuellar", "jecuellar", "1234");
    	assertEquals("jecuellar", user.getUsername());
    }
    
    public void testGetPassword(){
    	User user = new User("customer", "Joseph", "Cuellar", "jecuellar", "1234");
    	assertEquals("1234", user.getPassword());
    }
    
    public void testGetFirstName(){
    	User user = new User("customer", "Joseph", "Cuellar", "jecuellar", "1234");
    	assertEquals("Joseph", user.getFirstName());
    }
    
    public void testGetLastName(){
    	User user = new User("customer", "Joseph", "Cuellar", "jecuellar", "1234");
    	assertEquals("Cuellar", user.getLastName());
    }
  
}
