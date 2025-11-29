package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dao.TutorialDaoImpl;

@WebServlet("/tutorials")
public class TutorialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// set content type
		response.setContentType("text/html");

		// get print writer to send response.
		try (PrintWriter pw = response.getWriter()) {
			// get topic id from request parameter
			int topicId = Integer.parseInt(request.getParameter("topic_id"));

			// print the title with id
			pw.print("<h5 align='center'> Tutorials Published under Topic Id : " + topicId
					+ "</h5>");

			// get session from request
			HttpSession hs = request.getSession();

			// getting tutorialDao instance from the session.
			TutorialDaoImpl tutorialDao = (TutorialDaoImpl) hs.getAttribute("tutorialDao");

			// get list of the tutorial using tutorialDao instance.
			List<String> tutorials = tutorialDao.getTutorialsByTopicId(topicId);

			// iterating over  tutorial list
			for (String tutorial : tutorials) {
				pw.print("<h5><a href='tutorialDetails?tutorial_name="+tutorial+"'>" + tutorial + "<a></h5>");
			}

		} catch (Exception e) {
			//re throw the exception to WC by wrapping it in servletException.
			throw new ServletException("Exception in do-get of "+getClass(),e);
		}
	}

}
