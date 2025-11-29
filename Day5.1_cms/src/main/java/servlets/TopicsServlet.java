package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pojo.Topic;
import pojo.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dao.TopicDaoImpl;

@WebServlet("/topics")
public class TopicsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			pw.print("<h5>Login sucessful from topics page....</h5>");
			// 1.get HttpSession object from WC(existing object,provided cookies are
			// enabled!).
			HttpSession hs = request.getSession();
			System.out.println("from topics page session is new :" + hs.isNew());
			System.out.println("jsessionId from topics :" + hs.getId());// will display same jsession id for same
																		// client.
			// 2.Retrieve user details from the session scope.
			// HttpSession API :public object getAttribute(String attributeName);
			User user = (User) hs.getAttribute("user");
			
			if (user != null) {
				//==> session tracking working fine!.
				pw.print("<h5>Hello" + user.getUname() + "</h5>");
				pw.print("<h5 align='center'> All Available Topics</h5>");
				//get topic dao instance from session scope.
				TopicDaoImpl topicDao=(TopicDaoImpl) hs.getAttribute("topicDao");
				//call the getAllTopics method from TopicDaoImpl to fetch all topics list.
				List<Topic> allTopics= topicDao.getAllTopics();
				
				//generate  a form dynamically n send it to client.
				pw.print("<form action='tutorials' method='get'>");
				for(Topic t:allTopics) {
					pw.print("<h5><input type='radio' name='topic_id' value='"+t.getId() +"'/>"+ t.getName()+"</h5>");
				}
				pw.print("<input type='submit' value='Choose Topic' />");
				pw.print("</form>");
				
				
			} else {
				pw.print("<h5>Session Tracking based upon HttpSession failed: No Cookies!!!!</h5>");
			}
			// send logout link to client.
			pw.print("<h5><a href='logout'>Logout</a></h5>");

			//

		} catch (Exception e) {
			//re throw the exception to WC,by wrapping it ServletException(message,rootCause). 
			throw new ServletException("Error in doPost of " + getClass(), e);
		}
	}

}
