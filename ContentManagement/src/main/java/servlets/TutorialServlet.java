package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dao.TutorialDaoImpl;

@WebServlet("/tutorials")
public class TutorialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html");
	    try(PrintWriter pw=response.getWriter()){
	    	int topicId=Integer.parseInt(request.getParameter("topicId"));
	    	pw.print("<h2> From Tutorials for topic id :"+topicId+"</h2>");
	    	HttpSession hs=request.getSession();
	        TutorialDaoImpl tutorialDao=(TutorialDaoImpl) hs.getAttribute("tutorial_Dao");
	        List<String> tutorials= tutorialDao.getTutorialsByTopicId(topicId);
	        if(!tutorials.isEmpty()) {
	        	tutorials.forEach(t-> pw.print("<h5><a href='tutorialDetails?tutorialName="+t+"'>"+t+"</a></h5>"));
	        }else {
	        	pw.print("<h2> No Tutorial Found Please See other topics !!!</h2>");
	        }
	    }catch(Exception e) {
	    throw new ServletException("Error occured in doGet of class :"+getClass(),e);
	    }
	   
	}



}
