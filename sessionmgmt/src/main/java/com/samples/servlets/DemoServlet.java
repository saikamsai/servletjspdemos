package com.samples.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/demoServlet")
public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		/*String username=(String) session.getAttribute("user");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<h1>user name is:"+username );
	*/
		Cookie[] cookies=request.getCookies();
		Cookie mycookie=cookies[1];
		for(int i=0;i<cookies.length;i++) {
			String name=cookies[i].getName();
			String value=cookies[i].getValue();
		}
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<h1>user name is:"+mycookie.getValue() );
		
		
	}

}
