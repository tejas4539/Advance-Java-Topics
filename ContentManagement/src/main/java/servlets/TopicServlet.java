package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pojo.Topic;
import java.io.PrintWriter;
import java.util.List;

import dao.TopicDaoImpl;

@WebServlet(value="/topics",loadOnStartup = 1)
public class TopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException {
		response.setContentType("text/html");
		
		try(PrintWriter pw=response.getWriter()){
	        HttpSession hs=request.getSession();
	        TopicDaoImpl topicDao=(TopicDaoImpl)hs.getAttribute("topic_dao");
	        System.out.println(topicDao);
	        List<Topic> topics=topicDao.getAllTopics();
	        pw.print("<h5>Welcome to topics page</h5>");
	       if(topics.size()>0) {
	    	   pw.print("<form action='tutorials'>");
		        topics.forEach(t->pw.print(" <h5><input type='radio' name='topicId' value='"+t.getId()+"'/>"+t.getName()+"</h5>"));
		        pw.print("<input type='submit' value='Choose Topic'/>");
		        pw.print("</form>");
	       }else {
	    	   pw.print("<h5>No Topics Found</h5>");
	       }
	        
			
			
		}catch(Exception e) {
			throw new ServletException("Error in doGet of class :"+getClass(),e);
		}
		 
			
	}
	
}
