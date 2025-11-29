package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	private static Connection con;
	public static Connection opeConnection()throws SQLException {
		if(con==null) {
			String url = "jdbc:mysql://localhost:3306/javaData?useSSL=false&allowPublicKeyRetrieval=true";
			con=DriverManager.getConnection(url,"root","tejas");
		}
		return con;
	}
	public static void closeConnection()throws SQLException {
		if(con!=null) {
			con.close();
		}
	}
}
