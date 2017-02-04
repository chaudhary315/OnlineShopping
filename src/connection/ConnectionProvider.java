package connection;

import java.sql.*;
//import java.sql.Connection;
//import java.sql.DriverManager;

import connection.ConnectionManager;
public class ConnectionProvider implements ConnectionManager {
	private static Connection con=null;  
	static{  
	try{  
	Class.forName(DRIVER);  
	con=(Connection) DriverManager.getConnection(CONNECTION_URL,USERNAME,PASSWORD);  
	}catch(Exception e){e.printStackTrace();}  
	}  
	  
	public static Connection getCon(){  
	    return con;  
	}  
	
}
