package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pojo.User;
import utils.DBUtils;

import java.io.PrintWriter;

import dao.TopicDaoImpl;
import dao.TutorialDaoImpl;
import dao.UserDaoImpl;

//make it as eager loading.
@WebServlet(value = "/login", loadOnStartup = 1) // class level annotation
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;
	private TopicDaoImpl topicdao;
	private TutorialDaoImpl tutorialDao;

	@Override
	// overide method so we cannot add new checked exception.
	public void init() throws ServletException {
		// user,topic,tutorial dao instance
		try {
			userDao = new UserDaoImpl();
			topicdao=new TopicDaoImpl();
			tutorialDao=new TutorialDaoImpl();
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
			userDao.cleanUp();
			topicdao.cleanUp();
			tutorialDao.cleanUp();
			DBUtils.closeConnection();
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
			User user = userDao.authincateUser(email, pass);
			// check null ==> if null invalid login.
			// not null ==>successful login : send validate user details to client.
			if (user == null) {
				pw.print("<h1>Invalid Login Please <a href='login.html'>Retry</a></h1>");
			} else {
				//1.get jakarta.servlet.http.HttpSession(i/f) object from the WC.
				//Method of HttpServletRequest:HttpSession getSession()
				HttpSession session=request.getSession();
				System.out.println("from login page :"+session.isNew());
				System.out.println("jsessionId from login :"+session.getId());
				
				//2.save validated user details under sesssion scope.
				//Method of HttpSession:public void setAttribute(String attributeName,Object attributeValue);
				session.setAttribute("user", user);
				
				//save topic and tutorial dao instances in the session scope.
				session.setAttribute("topicDao", topicdao);
				session.setAttribute("tutorialDao", tutorialDao);
				
				//redirect the user to topics page.
				response.sendRedirect("topics");
				
			}
		} catch (Exception e) {
			// re throw the exception to caller
			throw new ServletException("Error in doPost of " + getClass(), e);

		}

	}

}
