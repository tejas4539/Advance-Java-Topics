package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static utils.DBUtils.openConnection;
import pojo.Employee;

public class EmployeeDaoImpl implements IEmployeeDao {
	private Connection con;
	private PreparedStatement pst1, pst2,pst3,pst4;
	private String querry = "select emp_id,emp_name,salary,hire_date from employee where "
			+ "department=? and hire_date between ? and ?";

	public EmployeeDaoImpl() throws SQLException {
		// get fix database connection from DBUtils
		con = openConnection();
		pst1 = con.prepareStatement(querry);
		pst2 = con.prepareStatement(
				"insert into employee (emp_name,department,designation,salary,hire_date) values(?,?,?,?,?)");
		pst3=con.prepareStatement("update employee set salary=salary+? ,department=? where emp_id=?");
		pst4=con.prepareStatement("delete from employee where emp_id=?");
		System.out.println("emp dao created...");
	}

	@Override
	public List<Employee> getSelectedEmpDetails(String department, Date beginDate, Date endDate) throws SQLException {
		List<Employee> emp = new ArrayList<>();
		// set IN params
		pst1.setString(1, department);
		pst1.setDate(2, beginDate);
		pst1.setDate(3, endDate);
		try (ResultSet rs = pst1.executeQuery()) {
			while (rs.next()) {
				emp.add(new Employee(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDate(4)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	public String addEmpDetails(Employee e) throws SQLException {
		// set IN params
		pst2.setString(1, e.getEname());
		pst2.setString(2, e.getDepartment());
		pst2.setString(3, e.getDesignation());
		pst2.setDouble(4, e.getSalary());
		pst2.setDate(5, e.getHireDate());

		// execute the querry :insert :DML:Method of PST :public int
		// executeUpdate()throws SQLException
		int i = pst2.executeUpdate();
		return i == 1 ? "Employee added Sucessfully" : "Employee details insertion failed";
	}
	

	@Override
	public String updateEmpDetails(int empid, double salarInc, String department) throws SQLException {
		pst3.setDouble(1, salarInc);
		pst3.setString(2, department);
		pst3.setInt(3, empid);
		int countrows=pst3.executeUpdate();
		return countrows==1 ? "Employee Details Updated "
				:"Employee updation failed";
		
	}
	

	@Override
	public String deleteEmpDetails(int empid) throws SQLException {
		pst4.setInt(1, empid);
		int countrows=pst4.executeUpdate();
		return countrows==1 ? "Employee Deleted Sucessfully "
				:"Employee Deletation failed";
	}

	public void cleanUp() throws SQLException {
		if (pst1 != null) {
			pst1.close();
		}
		if (pst2 != null) {
			pst2.close();
		}
		if (pst3 != null) {
			pst3.close();
		}
		if (pst4 != null) {
			pst4.close();
		}
		if (con != null) {
			con.close();
		}
		System.out.println("employee dao cleaned up!");
	}

}
