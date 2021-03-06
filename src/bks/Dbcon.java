package bks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dbcon {
	
	private static String name="shelton";
	private static String pass="shell";
	static boolean flag=false;

	public static Connection Conn()
	{
		/*
		 * String driver; String user; String url; String password;
		 */
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		Connection conn=null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bks","root","shel10");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
		return conn;	
	}

}
