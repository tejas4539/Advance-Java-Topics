package Tester;

import static utils.DBUtils.openConnection;
import java.sql.*;

public class TestStatement {
	public static void main(String[] args) {
		// 1. using try with resources to open connection.
		// 2.create empty Statement object, to hold query.
		// 3.execute the select query:public ResultSet executeQuery(String sql) throws
		// sqlException
		try (Connection con = openConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from employee");) {
			while (rs.next()) {
				System.out.printf(
						"empid: %d |ename: %s |department: %s |designation: %s |salary: %.1f |hire_date: %s %n",
						rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5),
						rs.getDate(6));
			} // rs.close(),st.close(),con.close()
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
