package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	private static final String DB_URL = "jdbc:mysql://localhost/donko";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";
	private static final String DB_JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {

		Class.forName(DB_JDBC_DRIVER);
		return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

	}

}
