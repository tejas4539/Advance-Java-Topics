package dao;

import java.io.IOException;
import java.sql.SQLException;

import pogo.User;

public interface IUserDao {

	User authincateUser(String email,String pass) throws SQLException,IOException;
}
