package com.hotel;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
	     
	  private static String driver="com.mysql.cj.jdbc.Driver";
	  private static  String url = "jdbc:mysql://localhost:3306/hotel";
	  private static  String username = "root";
	  private static  String password = "root";
	  private static Connection connection;
	     
	      public static Connection getConnection() {
	          try {
	  			   Class.forName(driver);
	              connection=DriverManager.getConnection(url,username,password);
	  		  }catch (Exception e) {
	  			e.printStackTrace();
	  		    } 
	          
	  	    return connection;
	      }
	          	          
}
