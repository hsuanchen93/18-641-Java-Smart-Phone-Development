package com.cmu.yapeng.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.CountDownLatch;

public class ReadData {
	private static final String URL = "jdbc:mysql://localhost:3306";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "yunjingl";
	
	
	private int count=0;
	
	public String showAll(){
		Connection connection = null;
		Statement statement = null;
		ResultSet rs;
		String data = "";
		try {			
			//open connection first
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM Math.Math;");
			while (rs.next()) {
				System.out.format("%5s%22s%14s\n", rs.getString("MathId"), rs.getString("Question"),
						rs.getString("Answer"));
				data+=rs.getString("MathId")+" "+rs.getString("Question")+" "+rs.getString("Answer")+" ";

//				count++;
			}
			//rs=statement.executeQuery("SELECT * FROM Math.Math WHERE MathID=1;");
//			while(rs.next()){
//				data=rs.getString("MathId")+" "+rs.getString("Question")+" "+rs.getString("Answer");
//			}
			System.out.println(data);
			statement.close();
			rs.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
		
	}
}
