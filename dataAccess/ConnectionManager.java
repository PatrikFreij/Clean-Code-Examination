package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static Connection connection;
	
	public Connection connectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moogame","root","root");
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Connection failed");
		}
		return connection;
	}

}
