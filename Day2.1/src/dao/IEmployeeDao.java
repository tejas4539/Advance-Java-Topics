package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import pojo.Employee;

public interface IEmployeeDao {
	/*
	 * Display details(id,department,salary,join_date) of all emps from specific
	 * department joined between start date n end date
	 */
	List<Employee> getSelectedEmpDetails(String department, Date beginDate, Date endDate) throws SQLException;
	
	//add a new method to insert new employee
	String addEmpDetails(Employee e)throws SQLException;
	//add a new method to update employee;
	String updateEmpDetails(int empid,double salarInc,String department)throws SQLException;
	String deleteEmpDetails(int empid)throws SQLException;
}
