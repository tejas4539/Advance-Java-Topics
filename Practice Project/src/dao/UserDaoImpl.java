package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static utils.DBUtils.openConnection;

import pojo.User;

public class UserDaoImpl implements IUserDao{
     Connection con;
     PreparedStatement pst1;
	 
	public UserDaoImpl()throws SQLException {
		con=openConnection();
		pst1=con.prepareStatement("select * from user where email=? and password =?");
		System.out.println("User Dao created...");
	}

	@Override
	public User authenticateUser(String username, String Password) throws SQLException {
		pst1.setString(1, username);
		pst1.setString(2, Password);
		try(ResultSet rs=pst1.executeQuery()){
			if(rs.next()) {
				User u=new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getDate(6),rs.getString(7)); 
				return u;	
			}
			return null;
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void cleanUp()throws SQLException {
		if(pst1!=null) {
			pst1.close();
		}
		if(con!=null) {
			con.close();
		}
		System.out.println("User Dao Cleaned Up....");
	}

}
