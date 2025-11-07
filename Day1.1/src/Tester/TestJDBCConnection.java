package Tester;
import java.sql.*;

public class TestJDBCConnection {
  
	public static void main(String[] args)throws ClassNotFoundException {
		String url="jdbc:mysql://localhost:3306/javaData";
		String uname="root";
		String pass="roo";
		//loads the class for jdbc driver
		Class.forName("com.mysql.cj.jdbc.Driver");//optional to write
		try(Connection con=DriverManager.getConnection(url, uname, pass)){
			 System.out.println(con);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
}  
