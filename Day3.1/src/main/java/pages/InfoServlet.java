package pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


//https://host:port/Day3.1/info:URL
//URI:/Day3.1/info
//URL Pattern:/info
//WC :create an empty map at the web app deployment time.
//populate Key:info
//value:pages.InfoServlet


@WebServlet("/info")
public class InfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw=response.getWriter()){
			pw.print("<h2> Name of User : "+request.getParameter("name")+"</h2>");
			pw.print("<h2> Favourite colour :"+Arrays.toString(request.getParameterValues("clr"))+"</h2>");
			pw.print("<h2> Gender :"+request.getParameter("gen")+"</h2>");
			pw.print("<h2> Description :"+request.getParameter("about")+"</h2>");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
