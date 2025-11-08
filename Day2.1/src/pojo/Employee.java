package pojo;
//emp_id, emp_name, department, designation, salary, hire_date

import java.sql.Date;

public class Employee {
	private int empId;
	private String ename;
	private String department;
	private String designation;
	private double salary;
	private Date hireDate;

	// POJO spec:required in hibernate: define arg-less constructor
	public Employee() {
		// not required in JDBC
	}

	public Employee(int empId, String ename, double salary, Date hireDate) {
		this.empId = empId;
		this.ename = ename;
		this.salary = salary;
		this.hireDate = hireDate;
	}

	public Employee(String ename, String department, String designation, double salary, Date hireDate) {
		this.ename = ename;
		this.department = department;
		this.designation = designation;
		this.salary = salary;
		this.hireDate = hireDate;
	}
	// getters and setters
	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	// toString
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", ename=" + ename + ", department=" + department + ", designation="
				+ designation + ", salary=" + salary + ", hireDate=" + hireDate + "]";
	}

}
