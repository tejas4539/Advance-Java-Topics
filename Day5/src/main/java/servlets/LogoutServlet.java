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

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//set content type
		response.setContentType("text/html");
       try(PrintWriter pw=response.getWriter()) {
    	   pw.write("logout");
    	   //1.get HttpSession from WC.
    	   HttpSession hs=request.getSession();
    	   System.out.println("from logout :"+hs.isNew());
    	   System.out.println("from logout :"+hs.getId());
    	   //2.get user details from the session scope.
    	   User user=(User)hs.getAttribute("user");
    	   if(user !=null) {
    		  pw.print("<h5> Hello ," +user.getUname()+"</h5>"); 
    		  pw.print("<h5>You have logged out.....<h5>");
    	   }else {
		    	 pw.print("<h5>Session Tracking based upon HttpSession failed: No Cookies!!!!</h5>");
		    }
    	  //Discard or remove or invalidate  the session.
    	   hs.invalidate();
    	   //send visit again link to client.
    	   pw.print("<h5><a href='login.html'>Visit Again</a><h5>");
    	   
       }catch(Exception e) {
    	   e.printStackTrace();
       }
	}

}
