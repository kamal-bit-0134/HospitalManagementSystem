package com.admin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DoctorDao;
import com.db.DBConnect;
import com.entity.Doctor;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/updateDoctor")
public class UpdateServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		try {
			
			String fullname = request.getParameter("fullname");
			String dob = request.getParameter("dob");
			String qualification = request.getParameter("qualification");
			String spec = request.getParameter("spec");
			String email = request.getParameter("email");
			String mobno = request.getParameter("mobno");
			String password = request.getParameter("password");
			int id = Integer.parseInt(request.getParameter("id"));
			
			Doctor d = new Doctor(id,fullname, dob, qualification, spec, email, mobno, password);
			DoctorDao dao = new DoctorDao(DBConnect.getConn());
			HttpSession session = request.getSession();
			
			if(dao.updateDoctor(d)) {
				session.setAttribute("succMsg", "Doctor Update Successfully");
				response.sendRedirect("admin/view_doctor.jsp");
			}else {
				session.setAttribute("errorMsg", "Something went wrong");
				response.sendRedirect("admin/view_doctor.jsp");
			}

			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
