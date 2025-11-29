package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pojo.User;

import java.io.PrintWriter;

import dao.TopicDaoImpl;
import dao.TutorialDaoImpl;
import dao.UserDaoImpl;

@WebServlet("/login") // class level annotation
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDaoImpl userDao;
	TopicDaoImpl topicDao;
	TutorialDaoImpl tutorialDao;

	public void init() throws ServletException {
		try {
			userDao=new UserDaoImpl();
			topicDao=new TopicDaoImpl();
			tutorialDao=new TutorialDaoImpl();
			
		}catch(Exception e) {
			throw new ServletException("Error occur in init of class :"+getClass(),e);
		}
	}

	public void destroy() {
		try {
			userDao.cleanUp();
			topicDao.cleanUp();
			tutorialDao.cleanUp();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException {
		
		response.setContentType("text/html");
		
		try(PrintWriter pw=response.getWriter()){
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			User user=userDao.authincateUser(email, password);
			if(user!=null) {
				 HttpSession session=request.getSession();
				 session.setAttribute("user", user);
				 session.setAttribute("topic_dao", topicDao);
				 session.setAttribute("tutorial_Dao", tutorialDao);
				 
				 response.sendRedirect("topics");
			}else {
			    pw.print("<h5>Wrong userName and Password please try again :<a href='index.html'>Retry</a></h5>");
			}
			
		}catch(Exception e) {
			throw new ServletException("Error occured in doGet of class :"+getClass(),e);
		}

	}

}
