<%@page import="com.entity.Specialist"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.SpecialistDao"%>
<%@page import="com.entity.Doctor"%>
<%@page import="com.db.DBConnect"%>
<%@page import="com.dao.DoctorDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file="../component/allcss.jsp"%>

<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
	<%@include file="doctor_navbar.jsp"%>

<div class="container-fluid p-3">
		<div class="row">

			<div class="col-md-4 offset-md-4">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-3 text-center">Edit Doctor Details</p>
						<c:if test="${not empty errorMsgd}">
							<p class="fs-3 text-center text-danger">${errorMsgd}</p>
							<c:remove var="errorMsgd" scope="session" />
						</c:if>
						<c:if test="${not empty succMsgd}">
							<div class="fs-3 text-center text-success" role="alert">${succMsgd}</div>
							<c:remove var="succMsgd" scope="session" />
						</c:if>

						<%
						Doctor temp = (Doctor) session.getAttribute("doctorobj");
						int id = (temp.getId());
						DoctorDao dao2 = new DoctorDao(DBConnect.getConn());
						Doctor d = dao2.getDoctorById(id);
						%>


						<form action="../doctorUpdateProfile" method="post">
							<div class="mb-3">
								<label class="form-label">Full Name</label> <input type="text"
									required name="fullname" class="form-control"
									value="<%=d.getFullName()%>">
							</div>

							<div class="mb-3">
								<label class="form-label">DOB</label> <input type="date"
									value="<%=d.getDob()%>" required name="dob"
									class="form-control">
							</div>

							<div class="mb-3">
								<label class="form-label">Qualification</label> <input required
									value="<%=d.getQualification()%>" name="qualification"
									type="text" class="form-control">
							</div>
							<div class="mb-3">
								<label class="form-label">Specialist</label> <select name="spec"
									required class="form-control">
									<option><%=d.getSpecialist()%></option>

									<%
									SpecialistDao dao = new SpecialistDao(DBConnect.getConn());
									List<Specialist> list = dao.getAllSpecialist();
									for (Specialist s : list) {
									%>
									<option><%=s.getSpecialistName()%></option>
									<%
									}
									%>


								</select>
							</div>

							<div class="mb-3">
								<label class="form-label">Email</label> <input type="text"
									value="<%=d.getEmail()%>" required name="email"
									class="form-control">
							</div>

							<div class="mb-3">
								<label class="form-label">Mob No</label> <input type="text"
									value="<%=d.getMobNo()%>" required name="mobno"
									class="form-control">
							</div>

							<div class="mb-3">
								<label class="form-label">Password</label> <input required
									value="<%=d.getPassword()%>" name="password" type="text"
									class="form-control">
							</div>
							<input type="hidden" name="id" value="<%=d.getId()%>">

							<button type="submit" class="btn btn-primary col-md-12">Update</button>
						</form>
					</div>
				</div>
			</div>




		</div>
	</div>
</body>
</html>