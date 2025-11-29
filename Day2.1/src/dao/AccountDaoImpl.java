package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import static utils.DBUtils.openConnection;

public class AccountDaoImpl implements IAccountDao {
    private Connection con;
    private CallableStatement cst1;
    
    public AccountDaoImpl()throws SQLException {
    	//get db connection from DBUtils
    	con=openConnection();
    	//cst to represent invocation of stored procedure
    	//{call ()} here {} are escape sequence
    	cst1=con.prepareCall("{call transfer_funds(?,?,?,?,?)}");
    	//register out params.
    	//API of CST public void regesterOutParameter(int parmPosition,jdbcType)throws SQLException.
    	cst1.registerOutParameter(4, Types.DOUBLE);
    	cst1.registerOutParameter(5, Types.DOUBLE);
    	System.out.println("Account dao created");
    	
    }
	@Override
	public String transferFunds(int srcAccNo, int desAccNo, double transferAmt) throws SQLException {
		cst1.setInt(1, srcAccNo);
		cst1.setInt(2, desAccNo);
		cst1.setDouble(3,transferAmt);
		//execute stored procedure
		cst1.execute();
		//getting the out parameters from the placeholder position number or given position in registeroutparameter.
		System.out.println("src balance :"+cst1.getDouble(4));
		System.out.println("destination balance :"+cst1.getDouble(5));
		return "Amount Transfer Sucessfully";
	}
	public void cleanUp() throws SQLException{
		if(cst1!=null) {
			cst1.close();
		}
		if(con!=null) {
			con.close();
		}
		System.out.println("account dao closed....");
	}

}
