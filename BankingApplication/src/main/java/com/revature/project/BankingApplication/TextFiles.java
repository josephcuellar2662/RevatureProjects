package com.revature.project.BankingApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TextFiles {
	
    public void serializeUser(User user, String directoryName){
    	File directory = new File(directoryName);
    	if(!directory.exists()){
    		directory.mkdirs();
    	}
    	String username = user.getUsername();
        // Serialization 
        try
        {   
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream( directoryName + "/" + username +  ".txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            // Method for serialization of object
            out.writeObject(user);
            out.close();
            file.close();
        }
         
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
    }
    
    public void serializeAccount(Account acc, String directoryName){
    	File directory = new File(directoryName);
    	if(!directory.exists()){
    		directory.mkdirs();
    	}
    	String username = acc.getUsernames();
    	String accountType = acc.getType();
        // Serialization 
        try
        {   
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream( directoryName + "/" + username + accountType +  ".txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            // Method for serialization of object
            out.writeObject(acc);
            out.close();
            file.close();
        }
         
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
    }
    
    public User deserializeUser(String username, String directoryName){
    	
    	 // Deserialization
    	User user = null;
        try
        {   
            // Reading the object from a file
            FileInputStream file = new FileInputStream(directoryName + "/" + username + ".txt");
            ObjectInputStream in = new ObjectInputStream(file);
             
            // Method for deserialization of object
            user = (User)in.readObject();
             
            in.close();
            file.close();
        }
         
        catch(IOException ex)
        {
            return null;
        }
         
        catch(ClassNotFoundException ex)
        {
            return null;
        }
        
        return user;
    }
    
    public Account deserializeAccount(String username, String directoryName, String accountType){
    	// Deserialization
    	Account acc = null;
    	String fileName = getFileName(username, accountType);
    	System.out.println("fileName: " + fileName);
    	try
    	{   
    		// Reading the object from a file
    		FileInputStream file = new FileInputStream(directoryName + "/" + fileName);
    		ObjectInputStream in = new ObjectInputStream(file);

    		// Method for deserialization of object
    		acc = (Account)in.readObject();
    		
    		in.close();
    		file.close();
    	}

    	catch(IOException ex)
    	{
    		return null;
    	}

    	catch(ClassNotFoundException ex)
    	{
    		return null;
    	}

    	return acc;
    }
    
    public boolean accountExist(String username, String accountType){
    	File[] files = new File("/Users/josephcuellar/Documents/workspace/BankingApplication/Accounts").listFiles();
        for (File file : files) {
        	if(file.getName().contains(username) && file.getName().contains(accountType)){
        		return true;
        	}
        }
        return false;
    }
    
    public String getFileName(String username, String accountType){
    	File[] files = new File("/Users/josephcuellar/Documents/workspace/BankingApplication/Accounts").listFiles();
        for (File file : files) {
        	if(file.getName().contains(username) && file.getName().contains(accountType)){
        		return file.getName();
        	}
        }
        return null;
    }
    
    public void deleteFile(String username, String accountType){
    	File[] files = new File("/Users/josephcuellar/Documents/workspace/BankingApplication/Accounts").listFiles();
        for (File file : files) {
        	if(file.getName().contains(username) && file.getName().contains(accountType)){
        		file.delete();
        	}
        }
    }
    
    public void printAllFiles(){
    	File[] files = new File("/Users/josephcuellar/Documents/workspace/BankingApplication/Accounts").listFiles();
        Account acc = null;
    	for (int i = 0; i<files.length; i++) {
        	if(!files[i].getName().contains("admin")){
        		String fileName = files[i].getName();
            	try
            	{   
            		// Reading the object from a file
            		FileInputStream f = new FileInputStream("Accounts" + "/" + fileName);
            		ObjectInputStream in = new ObjectInputStream(f);

            		// Method for deserialization of object
            		acc = (Account)in.readObject();
            		System.out.println(acc);
            		in.close();
            		f.close();
            	}

            	catch(IOException ex)
            	{
            		
            	}
            	catch(ClassNotFoundException ex)
            	{
            
            	}
        	}
        }
    }
    
    
   
}
