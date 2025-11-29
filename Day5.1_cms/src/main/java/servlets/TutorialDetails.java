package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pojo.Tutorial;

import java.io.IOException;
import java.io.PrintWriter;

import dao.TutorialDaoImpl;
@WebServlet("/tutorialDetails")
public class TutorialDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
		 try(PrintWriter pw=response.getWriter()){
			 //get selected tutorial name from request parameter.
			 String tutorial_name=request.getParameter("tutorial_name");
			 
			 //get HttpSession to retrieve tutorial dao instance
			 HttpSession hs=request.getSession();
			 TutorialDaoImpl tutorial=(TutorialDaoImpl) hs.getAttribute("tutorialDao");
			 //invoke dao method,to get details of selected tutorial.	
			 Tutorial tut=tutorial.getTutorialsDetailsByName(tutorial_name);
			 //update visits.
			 String updateVisitor=tutorial.updateVisits(tut.getId());
			 System.out.println(updateVisitor);
			 pw.print("<h5>Tutorial :"+tut.getName()+"<h5>");
			 pw.print("<h5>Author :"+tut.getAuthor()+"<h5>");
			 pw.print("<h5>Published on : "+tut.getPublishDate()+"<h5>");
			 pw.print("<h5>Visits "+tut.getVisits()+"<h5>");
			 pw.print("<h5>Contents "+tut.getContents()+"<h5>");

			 
			// send logout link to client.
			 
		    pw.print("<h5><a href='tutorials?topic_id="+tut.getTopicId()+"'>Back</a></h5>");
		    pw.print("<h5><a href='logout'>Logout</a></h5>");
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	}

}
