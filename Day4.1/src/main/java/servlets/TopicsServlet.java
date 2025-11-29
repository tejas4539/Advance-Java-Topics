package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/topics")
public class TopicsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			pw.print("<h5>Login sucessful from topics page....</h5>");
			// pw.print("<h5>User Email : "+ request.getParameter("email") + "</h5>");
			// the above line will give null output because this is http is stateless
			// protocol.
			// it cannot remember one request data for another request.

			// using cookie to get the data from the browser.
			// how to retrieve user details from the browser?
			// API of HttpServletRequest.
			// Method Cookie[] getCookies().
			Cookie[] cookies = request.getCookies();
			// null check if cookies or null or not.
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("userName")) {
						pw.print("<h5>Validate user details retrived from cookies : " + c.getValue() + "</h5>");
						break;
					}
				}
			} else {
				pw.print("<h5>Session tracking failed :No Cookies!!!!!!!!</h5>");
			}

		} catch (Exception e) {
			throw new ServletException("Error in doPost of " + getClass(), e);
		}
	}

}
