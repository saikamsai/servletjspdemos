package com.samples.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateServlet")
public class UpdateServlet extends HttpServlet {

	Connection connection;

	@Override
	public void init() throws ServletException {

		try {
			System.out.println("UpdateUserSevlet.init() method. DB connection created");
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "23@Swetha");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		String email = request.getParameter("email");
		String password = request.getParameter("password");


		try (Statement statement = connection.createStatement();) {

			String query = "update user set password='" + password + "' where email = '" + email + "'";
			System.out.println("Query being executed: " + query);
			int rowsUpdated = statement.executeUpdate(query);
			System.out.println("Number of rows Update: " + rowsUpdated);

			PrintWriter pw = response.getWriter();
			pw.write("User updated successfully");
			pw.write("<p><a href=\"userhome.html\">Home</a></p>");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void destroy() {
		try {
			System.out.println("UpdateUserSevlet.destroy() method. DB connection closed");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}