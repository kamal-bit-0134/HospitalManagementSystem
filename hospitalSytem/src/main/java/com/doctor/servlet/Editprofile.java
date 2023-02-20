package com.doctor.servlet;

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
 * Servlet implementation class Editprofile
 */

@WebServlet("/doctorUpdateProfile")
public class Editprofile extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
			
			String fullname = request.getParameter("fullname");
			String dob = request.getParameter("dob");
			String qualification = request.getParameter("qualification");
			String spec = request.getParameter("spec");
			String email = request.getParameter("email");
			String mobno = request.getParameter("mobno");
			int id = Integer.parseInt(request.getParameter("id"));
			
			Doctor d = new Doctor(id,fullname, dob, qualification, spec, email, mobno, "");
			DoctorDao dao = new DoctorDao(DBConnect.getConn());
			HttpSession session = request.getSession();
			
			if(dao.editDoctorProfile(d)) {
				session.setAttribute("succMsgd", "Doctor Update Successfully");
				response.sendRedirect("doctor/edit_profile.jsp");
			}else {
				session.setAttribute("errorMsgd", "Something went wrong");
				response.sendRedirect("doctor/edit_profile.jsp");
			}

			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
