<%@page import="com.db.DBConnect"%>
<%@page import="com.dao.DoctorDao"%>
<%@page import="com.entity.Doctor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctor Index Page</title>




<%@include file="../component/allcss.jsp" %>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>

</head>
<body>
<%@include file="doctor_navbar.jsp" %>

<%
response.setHeader("Cache-Control","no-chache,no-store,must-revalidate");
%>
<c:if test="${empty doctorobj }">
<c:redirect url="../doctor_login.jsp"></c:redirect>
</c:if>

<p class="text-center fs-3">Doctor Dashboard</p>

	<%
	Doctor d = (Doctor) session.getAttribute("doctorobj");
	DoctorDao dao = new DoctorDao(DBConnect.getConn());
	%>
	<div class="container p-5">
		<div class="row">
			<div class="col-md-4 offset-md-2">
				<div class="card paint-card">
					<div class="card-body text-center text-success">
						<i class="fas fa-user-md fa-3x"></i><br>
						<p class="fs-4 text-center">
							Doctor <br><%=dao.countDoctor()%>
						</p>
					</div>
				</div>
			</div>


			<div class="col-md-4">
				<div class="card paint-card">
					<div class="card-body text-center text-success">
						<i class="far fa-calendar-check fa-3x"></i><br>
						<p class="fs-4 text-center">
							Total Appointment <br>
							<%=dao.countAppointmentByDoctorId(d.getId())%>
							
						</p>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>