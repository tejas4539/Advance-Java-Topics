package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pogo.User;

import static utils.DBUtils.opeConnection;

public class UserDaoImpl implements IUserDao {

	private Connection con;
	private PreparedStatement pst1;

	public UserDaoImpl() throws SQLException {
		con = opeConnection();
		pst1 = con.prepareStatement("select * from user where email=? and password=?");
		System.out.println("Login dao instance created");
	}

	@Override
	public User authincateUser(String email, String pass) throws SQLException{
		pst1.setString(1, email);
		pst1.setString(2, pass);
		try (ResultSet rs = pst1.executeQuery()) {
			if (rs.next()) {
				User user=new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getDate(6), rs.getString(7));
				System.out.print(user);
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void cleanUp() throws SQLException {
		if (pst1 != null) {
			pst1.close();
		}
		if (con != null) {
			con.close();
		}
		System.out.println("Login Dao Cleared");
	}

}
