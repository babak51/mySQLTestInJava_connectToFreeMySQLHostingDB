package com.babak51.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * The aim was to connect to a DB I have created on "Free MySQL Hosting("sql3180489").
 * I wanted to see if I could connect to the DB and dump the records in a Table in the DB.  
 * My PC had to be online, otherwise I would not be able to connect to the DB. 
 * I have had already used "HeidiSQL" and created a table called "Customers" in
 * the DB "sql3180489" as below:
 * Customers:
 * ------------------------------------------------------------
 * |Field | Type        | NULL    |Key     |Default   | Extra |
 * ------------------------------------------------------------
 * |ID    |INT(10)      |NO       |PRI     |          |       |
 * |NAME  |VARCHAR(20)  |NO       |        |          |       |
 * |AGE   |INT(4)       |NO       |        |          |       |
 * |Email |CHAR(25)     |NO       |        |          |       |
 * |PHONE |CHAR(25)     |YES      |        |NULL      |       |
 * |AMOUNT|DECIMAL(12,2)|YES      |        |NULL      |       |
 * ------------------------------------------------------------
 * 
 * 
 * 
 * @author babak51
 * @date 06/16/2017
 */
public class Solution {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	public void readDataBase() throws Exception{
		// This will load the MySQL driver, each DB has its own driver
		
		// you need to select the project then use properties->java build path->libraries->add the 
		// connector as an external jar the location of the jar is given above on my PC.  
		Class.forName("com.mysql.jdbc.Driver"); 
				
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net/sql3180489?user=sql3180489&password=n4XWwunZCu");
	
		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();
		
		System.out.println("\n1A) --------DBs--------");
		// Result set get the result of the SQL query
		resultSet = statement.executeQuery("SHOW DATABASES");
		
		// ResultSet is initially before the first data set
		while(resultSet.next()){
			System.out.println(resultSet.getString(1));
		}
		
		System.out.println("\n1B) --------USE the 'sql3180489' DB--------");
		statement.executeQuery("USE sql3180489");
		
		System.out.println("\n2) --------Tables--------");
		// Result set get the result of the SQL query
		resultSet = statement.executeQuery("SHOW TABLES");
			
		// ResultSet is initially before the first data set
		while(resultSet.next()){
		    System.out.println(resultSet.getString(1));
		}
		
		System.out.println("\n3) --------DESC Customers--------");
		// Result set get the result of the SQL query
		resultSet = statement.executeQuery("DESC Customers");
			
		// ResultSet is initially before the first data set
		while(resultSet.next()){
		    System.out.println(resultSet.getString(1));
		}
		
		
		System.out.println("\n5) --------SELECT * FROM Customers--------");
		// Result set get the result of the SQL query
		resultSet = statement.executeQuery("SELECT * FROM Customers");
			
		// ResultSet is initially before the first data set
		while(resultSet.next()){
		    System.out.format("| %3s | %15s | %4s | %20s | %15s | %7s |\n"
		    		 ,resultSet.getString("ID") 
		    		 ,resultSet.getString("NAME") 
		    		 ,resultSet.getString("AGE") 
		    		 ,resultSet.getString("EMAIL")
		    		 ,resultSet.getString("PHONE")
		    		 ,resultSet.getString("AMOUNT"));
		}
	}
 
	public static void main(String[] args) throws Exception{
		Solution mySqlAccess = new Solution();
		mySqlAccess.readDataBase();
		System.out.println("----Done!----");
	}
}

