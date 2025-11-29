package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pojo.User;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/topics")
public class TopicsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void init()throws ServletException {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			pw.print("<h5>Login sucessful from topics page....</h5>");
			//1.get HttpSession object from WC(existing object,provided cookies are enabled!).
			HttpSession hs=request.getSession();
			System.out.println("from topics page session is new :"+hs.isNew());
			System.out.println("jsessionId from topics :"+hs.getId());//will display same jsession id for same client.
			//2.Retrieve user details from the session scope.
			//HttpSession API :public object getAttribute(String attributeName);
		     User user=(User) hs.getAttribute("user");
		     if(user!=null) {
		    	 pw.print("<h5>Retrived user details from HttpSession "+user+"</h5>");
		     }else {
		    	 pw.print("<h5>Session Tracking based upon HttpSession failed: No Cookies!!!!</h5>");
		     }
		     //send logout link to client.
		     pw.print("<h5><a href='logout'>Logout</a></h5>");
			
			//
			

		} catch (Exception e) {
			throw new ServletException("Error in doPost of " + getClass(), e);
		}
	}
	@Override
	public void destroy() {
		
	}

}
