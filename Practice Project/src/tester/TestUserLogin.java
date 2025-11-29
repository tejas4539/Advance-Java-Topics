package tester;

import java.util.Scanner;

import dao.UserDaoImpl;

public class TestUserLogin {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
              UserDaoImpl user=new UserDaoImpl();
              System.out.print("enter email :");
              String username=sc.next();
              System.out.print("enter password :");
              String pass=sc.next();
              System.out.println(user.authenticateUser(username, pass));
              user.cleanUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
