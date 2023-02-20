package com.user.servlet;

import java.io.IOException;
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
 * Servlet implementation class UserRegister
 */

@WebServlet("/user_register")
public class UserRegister extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String fullName = request.getParameter("fullname");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			User u = new User(fullName,email,password);
			
			UserDao dao = new UserDao(DBConnect.getConn());
			
			HttpSession session = request.getSession();
			
			
			boolean f = dao.userRegiter(u);
			if(f) {
				session.setAttribute("sucMsg", "Succesful");
//				System.out.println("Registered Succesful");
				response.sendRedirect("signup.jsp");
			}else {
				session.setAttribute("errMsg", "Something went wrong");

				response.sendRedirect("signup.jsp");

//				System.out.println("Something went wrong");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
