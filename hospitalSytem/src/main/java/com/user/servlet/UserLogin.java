package com.user.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;

import com.db.DBConnect;
import com.entity.User;

/**
 * Servlet implementation class UserLogin
 */

@WebServlet("/userLogin")
public class UserLogin extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String emailString = request.getParameter("email");
		String paaString = request.getParameter("password");
		
		HttpSession session = request.getSession();
		
		UserDao userDao = new UserDao(DBConnect.getConn());
		
		User user = userDao.login(emailString, paaString);
		
		if(user!=null) {
			session.setAttribute("userObj", user);
			response.sendRedirect("index.jsp");
		}else {
			session.setAttribute("errorMsg", "Invalid email or password");
			response.sendRedirect("user_login.jsp");
		}
		
		
		
		
	}



}
