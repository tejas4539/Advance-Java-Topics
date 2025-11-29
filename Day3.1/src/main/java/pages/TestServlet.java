package pages;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		System.out.println("in in-it");
	}

	
	public void destroy() {
	System.out.println("in destroy");	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in doGet");
		//1.set content type://header
		response.setContentType("text/html");
		//pw:chat buffered o/p stream connected from servlet --->client
		try(PrintWriter pw=response.getWriter()){
			 pw.print("<h1>Hello world<h1> "+new java.util.Date());//body
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
