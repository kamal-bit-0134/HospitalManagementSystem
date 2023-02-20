package com.admin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.SpecialistDao;
import com.db.DBConnect;
import com.entity.User;

/**
 * Servlet implementation class AddSpecialist
 */
@WebServlet("/addSpecialist")
public class AddSpecialist extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String specNameString = request.getParameter("SpecName");
		SpecialistDao dao = new SpecialistDao(DBConnect.getConn());
		boolean f = dao.addSpecialist(specNameString);
		HttpSession session= request.getSession();
		
		
		if(f==true) {
			session.setAttribute("succMsgSpecialist","Specialist Added");
			response.sendRedirect("admin/index.jsp");
		}else {
			session.setAttribute("errorMsgSpecialist", "Something went wrong");
			response.sendRedirect("admin/index.jsp");
			
		}
	}

}
