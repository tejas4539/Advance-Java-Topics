package pojo;

import java.sql.Date;

public class User {
	private int userId;
	private String uname;
	private String email;
	private String password;
	private double regAmount;
	private Date regDate;
	private String role;

	public User() {

	}

	public User(int userId, String uname, String email, String password, double regAmount, Date regDate, String role) {
		super();
		this.userId = userId;
		this.uname = uname;
		this.email = email;
		this.password = password;
		this.regAmount = regAmount;
		this.regDate = regDate;
		this.role = role;
	}

	public int getId() {
		return userId;
	}

	public void setId(int userId) {
		this.userId = userId;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getRegAmount() {
		return regAmount;
	}

	public void setRegAmount(double regAmount) {
		this.regAmount = regAmount;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "userId=" + userId + ",uname=" + uname + ",email=" + email + ",regAmount=" + regAmount
				+ ",regDate=" + regDate + ",role=" + role ;
	}

    
}
