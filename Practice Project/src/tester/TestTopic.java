package tester;

import java.util.List;
import java.util.Scanner;

import dao.TopicDaoImpl;
import pojo.Topic;

public class TestTopic {

	public static void main(String[] args) {
		 try(Scanner sc=new Scanner(System.in)){
			 TopicDaoImpl tp=new TopicDaoImpl();
			 List<Topic> allTp=tp.getAllAvailable();
			 allTp.forEach(System.out::println);
			 tp.cleanUp();
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	}

}
