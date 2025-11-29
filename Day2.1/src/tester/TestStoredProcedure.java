package tester;

import java.util.Scanner;

import dao.AccountDaoImpl;

public class TestStoredProcedure {

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in)) {
			  //create dao instance.
			AccountDaoImpl adao=new AccountDaoImpl();
			System.out.print("Enter src account id :");
			int sid=sc.nextInt();
			System.out.print("Enter des account id :");
			int did=sc.nextInt();
			System.out.print("Enter amount to transfer :");
			double amt=sc.nextDouble();
			
			System.out.println(adao.transferFunds(sid, did, amt));
			adao.cleanUp();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
