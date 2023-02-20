package com.doctor.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DoctorDao;
import com.dao.UserDao;
import com.db.DBConnect;
import com.entity.Doctor;
import com.entity.User;

/**
 * Servlet implementation class DoctorLogin
 */

@WebServlet("/doctorLogin")
public class DoctorLogin extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailString = request.getParameter("email");
		String paaString = request.getParameter("password");
		
		HttpSession session = request.getSession();
		
		DoctorDao doctorDao = new DoctorDao(DBConnect.getConn());
		
		Doctor doctor = doctorDao.login(emailString, paaString);
		
		if(doctor!=null) {
			session.setAttribute("doctorobj", doctor);
			response.sendRedirect("doctor/doctor_index.jsp");
		}else {
			session.setAttribute("errorMsg", "Invalid email or password");
			response.sendRedirect("doctor_login.jsp");
		}
	}

}
