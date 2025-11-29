package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pojo.User;

import static utils.DBUtils.opeConnection;

public class UserDaoImpl implements IUserDao {

	Connection con;
	PreparedStatement pst1;

	public UserDaoImpl() throws SQLException {
		con = opeConnection();
		pst1 = con.prepareStatement("select * from user where email=? and password=?");
		System.out.println("user dao instance created");
	}

	@Override
	public User authincateUser(String email, String pass) throws SQLException, IOException {
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
		
		
		System.out.println("user Dao Cleared");
	}

}
