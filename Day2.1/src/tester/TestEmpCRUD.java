package tester;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import dao.EmployeeDaoImpl;
import pojo.Employee;

public class TestEmpCRUD {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			EmployeeDaoImpl dao = new EmployeeDaoImpl();
			boolean exit = false;
			while (!exit) {
				System.out.println("Select the Option :");
				System.out.println("1.Get Employee Details");
				System.out.println("2.Add new Employee");
				System.out.println("3.update Employee");
				System.out.println("4.Delete Employee");
				System.out.println("10.Exit");

				try {
					int option = sc.nextInt();
					sc.nextLine();
					switch (option) {
					case 1: {
						// get employee details
						System.out.print("Enter dept name :");
						String dept = sc.nextLine();
						System.out.print("Enter Start Date(yyyy-mm-dd) :");
						Date begin = Date.valueOf(sc.nextLine());
						System.out.print("Enter end Date(yyyy-mm-dd) :");
						Date end = Date.valueOf(sc.nextLine());
						List<Employee> emp = dao.getSelectedEmpDetails(dept, begin, end);
						emp.forEach(System.out::println);
						break;
					}
					case 2:{
						System.out.print("Enter emp name :");
						String name = sc.nextLine();
						System.out.print("Enter dept name :");
						String dept = sc.nextLine();
						System.out.print("Enter emp designation :");
						String desg = sc.nextLine();
						System.out.print("Enter emp salary :");
						Double salary = sc.nextDouble();sc.nextLine();
						System.out.print("Enter Hire Date(yyyy-mm-dd) :");
						Date hire = Date.valueOf(sc.nextLine());
						Employee e=new Employee(name, dept, desg, salary, hire);
						String res=dao.addEmpDetails(e);
						System.out.println(res);
						break;
						
					}
					case 3:{
						System.out.print("Enter employee id :");
						int id=sc.nextInt();
						System.out.print("Enter increment :");
						double salaryIncre=sc.nextDouble();
						System.out.print("Enter new department :");
						String dept=sc.next();
						System.out.println(dao.updateEmpDetails(id, salaryIncre, dept));
						break;	
					}
					case 4:{
						System.out.print("Enter employee id :");
						int id=sc.nextInt();
						System.out.println(dao.deleteEmpDetails(id));
						break;
					}
					case 10: {
						exit = true;
						// destroy(shut down):clean up db resources
						dao.cleanUp();
						break;
					}
					default: {
						System.out.println("Invalid Input");
						break;
					}

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
