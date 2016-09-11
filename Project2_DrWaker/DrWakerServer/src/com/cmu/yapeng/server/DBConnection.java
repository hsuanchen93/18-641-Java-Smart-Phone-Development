package com.cmu.yapeng.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "yunjingl";
	public void createDB() {
		Statement statement = null;
		Connection connection = null;

		// open connection to dataBase

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			statement = connection.createStatement();
			statement.executeUpdate("DROP DATABASE Math;");
			statement.executeUpdate("CREATE DATABASE Math;");
			statement.executeUpdate("USE Math;");
			statement.executeUpdate("CREATE TABLE Math(MathId INT NOT NULL,Question varchar(255) NOT NULL,Answer varchar(255) NOT NULL,PRIMARY KEY (MathId))");
			String sql="INSERT INTO Math.Math(MathId,Question,Answer) VALUES (1,'3+10=','13')";
			statement.executeUpdate(sql);
			sql="INSERT INTO Math.Math(MathId,Question,Answer) VALUES (2,'12*3=','36')";
			statement.executeUpdate(sql);
			System.out.println("DATABASE HAS BEEN CREATED SUCCESSFULLY");
			connection.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Cannot connect to the DataBase");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Clear the database first

//		try {
//			statement.executeUpdate("DROP DATABASE MathDatabase;");
//			
//
//		} catch (Exception e) {
//			System.out.println("Clear operation undone!");
//		}

		// execute the commend to create a database

		
//			try {
//				statement.executeUpdate("CREATE DATABASE MathDatabase");
//				statement.executeUpdate("USE MathDatabase;");
//				statement.executeUpdate("CREATE TABLE Math(MathId INT NOT NULL,Question varchar(255) NOT NULL,Answer varchar(255) NOT NULL,PRIMARY KEY (MathId))");
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
		
		//System.out.println("DATABASE HAS BEEN CREATED SUCCESSFULLY");

		// close
//
//		try {
//			connection.close();
//			statement.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

	}

}
