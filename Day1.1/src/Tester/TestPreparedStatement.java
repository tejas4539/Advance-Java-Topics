package Tester;

import java.util.Scanner;
import java.sql.*;
import static utils.DBUtils.openConnection;

public class TestPreparedStatement {
	public static void main(String[] args) {
		String sql = "select * from employee where department=? and hire_date > ? order by salary";
		try (Scanner sc = new Scanner(System.in);
				Connection con = openConnection(); // Establish Database Connection
				PreparedStatement pst = con.prepareStatement(sql);) {
			System.out.print("Enter department name :");
			String deptId = sc.nextLine();
			System.out.print("Enter Date :");
			Date date = Date.valueOf(sc.next());
			pst.setString(1, deptId);
			pst.setDate(2, date);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					System.out.printf(
							"empid: %d |ename: %s |department: %s |designation: %s |salary: %.1f |hire_date: %s %n",
							rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5),
							rs.getDate(6));
				}
			} // rs.close();
			catch (Exception e) {
				e.printStackTrace();
			}

		} // pst.close();con.close();
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
