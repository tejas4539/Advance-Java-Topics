package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/test", loadOnStartup = 1) // by using loadOnStartup we are making servlet eager to load in place
												// of lazy loading which is default
public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doget invoked by :" + Thread.currentThread());// name | priority | ThreadGroup
		// set response content type.
		// Method of HttpServletResponse :public void setContentType(String type);
		resp.setContentType("text/html");// response packet header.
		// to send response from server --->client(i.e to response body) get writter
		// instance from Http response.
		try (PrintWriter pw = resp.getWriter()) {
			pw.print("Welcome to servlet... @" + LocalDateTime.now());// response body
		} // pw.close();--WC sends PW's buffer contents to web server---->response
			// packet(SC:200| Headers|body);
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		System.out.println("destroy invoked by :" + Thread.currentThread());
	}

	@Override
	public void init() throws ServletException {
		System.out.println("init invoked by :" + Thread.currentThread());
	}

}
