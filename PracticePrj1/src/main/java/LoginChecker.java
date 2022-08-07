import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/LoginChecker")
public class LoginChecker extends HttpServlet {

	public LoginChecker() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uname = request.getParameter("txtUname");
		String pword = request.getParameter("txtPassword");
		PrintWriter out = response.getWriter();
		if (uname.equalsIgnoreCase("saikam@gmail.com") && pword.equals("sai123")) {
			out.println("You are Welcomed");
		} else
			out.println("Invalid Username or Password");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String uname = request.getParameter("txtUname");
		String pword = request.getParameter("txtPassword");
		PrintWriter out = response.getWriter();
		RequestDispatcher rd;
		if (uname.equalsIgnoreCase("saikam@gmail.com") && pword.equals("sai123")) {
			// session object is created by web conatiner and generate sessionid
			HttpSession session = request.getSession(true);// create a new object and store its reference in session
			session.setAttribute("username", uname);
			rd = request.getRequestDispatcher("Welcome");
			rd.forward(request, response);
		} else {
			out.println("Invalid UserID or Password");
			rd = request.getRequestDispatcher("LoginForm.html");
			rd.include(request, response);
		}
	}
}