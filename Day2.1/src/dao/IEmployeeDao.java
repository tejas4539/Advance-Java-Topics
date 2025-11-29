package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import pojo.Employee;

public interface IEmployeeDao {
	/*
	 * Display details(id,department,salary,join_date) of all emps from specific
	 * department joined between start date n end date
	 */
	List<Employee> getSelectedEmpDetails(String department, Date beginDate, Date endDate) throws SQLException;
	
	//add a new method to insert new employee.
	String addEmpDetails(Employee e)throws SQLException;
	//add a new method to update employee.
	String updateEmpDetails(int empid,double salarInc,String department)throws SQLException;
	//added a delete method to delete employee,
	String deleteEmpDetails(int empid)throws SQLException;
	//add a method to get department wise avg salary in order.
	Map<String, Double> getDepAvgSal()throws SQLException;
}
