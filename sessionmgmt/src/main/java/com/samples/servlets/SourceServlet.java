package com.samples.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sourceServlet")
public class SourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
	/*	HttpSession session=request.getSession();
		session.setAttribute("user", username);
		session.setAttribute("age", 35);*/
		
		Cookie[] cookies=request.getCookies();
		Cookie cookie=new Cookie("username",username);
		response.addCookie(cookie);
	}

}
