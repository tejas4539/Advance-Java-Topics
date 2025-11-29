package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pogo.User;

import java.io.PrintWriter;

import dao.UserDaoImpl;

//make it as eager loading.
@WebServlet(value = "/login", loadOnStartup = 1) // class level annotation
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl auth;

	@Override
	// overide method so we cannot add new checked exception.
	public void init() throws ServletException {
		// user dao instance
		try {
			auth = new UserDaoImpl();
		} catch (Exception e) {
			// Inform the WC init fail. we do not want to continue remaining life cycle of
			// servlet and WC does not notice the init fail
			// because web container does not no init fail because we have handled it.
			// so we have to throw error that web container can understand or notice i.e
			// servlet exception.

			// re throw the exception wrapped in servletException.
			// servletException(String message,throwable root).
			throw new ServletException("err in init " + getClass().getName(), e);
		}
	}

	@Override
	public void destroy() {
		try {
			auth.cleanUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// set content type.
		response.setContentType("text/html");
		// get pw to send response.
		try (PrintWriter pw = response.getWriter()) {
			// read req parameters :email and password;
			String email = request.getParameter("email");
			String pass = request.getParameter("password");

			// LoginServlet invokes dao's CRUD method.
			User user = auth.authincateUser(email, pass);
			// check null ==> if null invalid login.
			// not null ==>successful login : send validate user details to client.
			if (user == null) {
				pw.print("<h1>Invalid Login Please <a href='login.html'>Retry</a></h1>");
			} else {
				// pw.print("<h2>Login Successful ,User Details :"+user+"</h2>");
//				pw.flush();//explicitly committing the response.//uncomment this line to understand IllegalStateException.
				
				
				//1.Create a cookie to store ,validate user details.
				//jakarta.servlet.http.Cookie(String name,String value).
				Cookie c1=new Cookie("userName",user.getUname());
				//cookie accepts only strings so we have to convert object toString.
				
				//send cookie from server to client in the response header.
				//use Method of HttpServletResponse
				//public void addCookie(Cookie c).
				response.addCookie(c1);
				
				//to automatically redirect client to the topic page.
				// API of HttpServletResponse.
				// Method:public void sendRedirect(String redirectLocation)throws IOException.
				
				response.sendRedirect("topics");
				// if we add /topics then context root path will ignored means it will
				// look for http://host:port/topics and ignore Day4.1
				// WC:sends temporary redirect response.
				// response : status code:302| header:location=topics,set-cookie=userDetails:User.toString()| body:empty.
				// web browser: send a new request .
				// URL:http://host:port/Day4.1/topics.
				// HTTP Method:GET
				//request header:cookie=userDetails:user.toString().
				// add a TopicsServlet: with /topics
			}
		} catch (Exception e) {
			// re throw the exception to caller
			throw new ServletException("Error in doPost of " + getClass(), e);

		}

	}

}
