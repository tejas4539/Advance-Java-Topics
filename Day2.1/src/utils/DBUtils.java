package utils;

import java.sql.*;

public class DBUtils {
	// add static method to return DB connection instance
	// modifying the  below code to ensure SINGLETON instance of DB connection
	// (not scalable solution, will be replaced by connection pool, from hibernate
	// onwards)
//	public static Connection openConnection()throws SQLException {
//		String url="jdbc:mysql://localhost:3306/javaData?useSSl=false&allowPublicKeyRetrieval=true";
//		return DriverManager.getConnection(url, "root","tejas1947");
//	}

	private static Connection con;

	public static Connection openConnection() throws SQLException {
		if (con == null) {
			String url = "jdbc:mysql://localhost:3306/javaData?useSSl=false&allowPublicKeyRetrieval=true";
			con = DriverManager.getConnection(url, "root", "tejas");

		}
		return con;
	}
}
