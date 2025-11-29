package tester;

import java.util.List;
import java.util.Scanner;

import dao.TutorialDaoImpl;
import pojo.Tutorial;

public class TestTutorial {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			TutorialDaoImpl td = new TutorialDaoImpl();
			boolean exe = false;
			while (!exe) {
				System.out.println("Enter the option ");
				System.out.println("1.get topic");
				System.out.println("2.get tutorials details by name :");
				System.out.println("10.exit");
				int opt=sc.nextInt();sc.nextLine();
				switch (opt) {
				case 1: {
					System.out.print("enter topic id");

					List<String> res = td.getTutorialsByTopicId(sc.nextInt());
					sc.nextLine();
					res.forEach(System.out::println);
					break;
				}
				case 2: {
					System.out.print("Enter tutorial name :");
					Tutorial t = td.getTutorialsDetailsByName(sc.nextLine());
					System.out.println(td.updateVisits(t.getId()));
					System.out.println(t);
					break;
				}
				case 10: {
					td.cleanUp();
					exe=true;
					break;

				}
				default: {
					System.out.println("Invalid option");
				}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
}
