/*
 * Hsuan Chen (hsuanc)
 */

package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class JDBCAdapter implements DBConstants {
	private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData metaData;
    private int numCols;

    /* Constructor - opens the database connection */
    public JDBCAdapter() {
    	String url = null;
    	String driverName = null;
    	String user = null;
    	String passwd = null;
		try {
			FileReader file = new FileReader("auto_db.txt");
			BufferedReader buff = new BufferedReader(file);
			//read in url
			String line = buff.readLine();
			url = buff.readLine();
			//read in driverName
			line = buff.readLine();
			driverName = buff.readLine();
			//read in user
			line = buff.readLine();
			user = buff.readLine();
			//read in password
			line = buff.readLine();
			passwd = buff.readLine();
			buff.close();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
    		Class.forName(driverName);
    		if(DEBUG) {
    			System.out.println("Opening db connection...");
    		}
            connection = DriverManager.getConnection(url, user, passwd);
            statement = connection.createStatement();
        } catch (ClassNotFoundException ex) {
            System.err.println("Cannot find the database driver classes.");
            ex.printStackTrace();
        }
        catch (SQLException ex) {
            System.err.println("Cannot connect to this database.");
            ex.printStackTrace();
        }
     } 
    
    /* executeQuery - execute UPDATE, INSERT, or DELETE query */
    public int executeQuery(String query) {
    	if(DEBUG) {
    		System.out.println(query);
    	}
        if (connection == null || statement == null) {
            System.err.println("There is no database to execute the query.");
            return -1;
        }
        try {
            numCols = statement.executeUpdate(query);
            if(DEBUG) {
            	System.out.println(numCols+" rows affected");
            }
            return numCols;
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }
		return -1;
    }
    
    /* selectQuery - execute SELECT query */
    public ResultSet selectQuery(String query) {
    	if(DEBUG) {
    		System.out.println(query);
    	}
        if (connection == null || statement == null) {
            System.err.println("There is no database to execute the query.");
            return null;
        }
        try {
            resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }
		return null;
    }

    /* close - close the database connection */
    public void close() {
    	if(DEBUG) {
    		System.out.println("Closing db connection...");
    	}
        try {
        	if(resultSet!=null) {
        		resultSet.close();
        	}
			statement.close();
	        connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

}