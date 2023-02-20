package com.doctor.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.stream.events.Comment;

import com.dao.AppointmentDao;
import com.db.DBConnect;

/**
 * Servlet implementation class UpdateStatus
 */
@WebServlet("/updateStatus")
public class UpdateStatus extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			int did = Integer.parseInt(request.getParameter("did"));
			String commString  = request.getParameter("comm");
			
			AppointmentDao dao = new AppointmentDao(DBConnect.getConn());
			HttpSession session = request.getSession();
			
			if(dao.updateCommentStatus(id, did, commString)) {
				session.setAttribute("succMsg", "Comment Updated");
				response.sendRedirect("doctor/patient.jsp");
			}else {
				session.setAttribute("errorMsg", "Something went wrong");
				response.sendRedirect("doctor/patient.jsp");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
