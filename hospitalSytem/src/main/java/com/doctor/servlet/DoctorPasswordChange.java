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

/**
 * Servlet implementation class DoctorPasswordChange
 */
@WebServlet("/doctChangePassword")
public class DoctorPasswordChange extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int uid = Integer.parseInt(req.getParameter("uid"));
		String oldPassword = req.getParameter("oldPassword");
		String newPassword = req.getParameter("newPassword");

		DoctorDao dao = new DoctorDao(DBConnect.getConn());
		HttpSession session = req.getSession();
		

		if (dao.checkOldPassword(uid, oldPassword)) {

			if (dao.changePassword(uid, newPassword)) {
				session.setAttribute("succMsg", "Password Change Sucessfully");
				res.sendRedirect("../hospitalSytem/doctor_login.jsp");

			} else {
				session.setAttribute("errorMsg", "Something wrong on server");
				res.sendRedirect("..//hospitalSytem/doctor_login.jsp");
			}

		} else {
			session.setAttribute("errorMsg", "Old Password doesn't match");
			res.sendRedirect("change_password.jsp");
		}
	}

}
