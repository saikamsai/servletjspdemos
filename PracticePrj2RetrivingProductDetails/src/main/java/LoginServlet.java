import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	Connection connection;

	@Override
	public void init(ServletConfig config) throws ServletException {

		try {
			ServletContext context = config.getServletContext();
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(context.getInitParameter("dburl"),
					context.getInitParameter("dbuser"), context.getInitParameter("dbpassword"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		PrintWriter out = response.getWriter();
		try (PreparedStatement statement = connection.prepareStatement("select * from Product where PID = ?")) {
			statement.setInt(1, id);

			ResultSet results = statement.executeQuery();
			if (results.next()) {
				// PrintWriter out2 = response.getWriter();
				out.print("<table width=50% border=1>");
				out.print("<caption>Result:</caption>");

				ResultSetMetaData rsmd = (ResultSetMetaData) results.getMetaData();
				int total = rsmd.getColumnCount();
				out.print("<tr>");
				for (int i = 1; i <= total; i++) {
					out.print("<th>" + rsmd.getColumnName(i) + "</th>");
				}

				out.print("</tr>");
				out.print("<tr><td>" + results.getInt(1) + "</td><td>" + results.getString(2) + "</td><td>"
						+ results.getInt(3) + "</td><td>" + results.getDouble(4) + "</td></tr>");

			} else {
				PrintWriter out1 = response.getWriter();
				response.setContentType("text/html");
				out.println("Entered product Id is not available in the table");
				RequestDispatcher rd = request.getRequestDispatcher("Login.html");
				rd.include(request, response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		try {
			System.out.println("AddUserSevlet.destroy() method. DB connection closed");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}