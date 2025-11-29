package dao;

import java.sql.SQLException;

import pojo.User;

public interface IUserDao {

	User authenticateUser(String username, String Password) throws SQLException;
}
