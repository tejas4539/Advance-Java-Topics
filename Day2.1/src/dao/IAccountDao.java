package dao;

import java.sql.SQLException;

public interface IAccountDao {
//add a method to transfer funds;
	String transferFunds(int srcAccNo,int desAccNo,double transferAmt)throws SQLException;
}
