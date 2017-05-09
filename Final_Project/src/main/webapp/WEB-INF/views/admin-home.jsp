<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Home</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<nav class="navbar navbar-default" style="background-color:#0F1B4F;">
	<a href="${contextPath}/user/logout" class="btn btn-success btn-lg">logout</a><br/>
</nav>


	
	<h1>Hi, ${user.firstName}</h1>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<form action="${contextPath}/user/admin/updateusers" method="post">
		
		<table class="table table-striped table-bordered table-hover">
			<tr>
				<td><b>Id</b></td>
				<td><b>First Name</b></td>
				<td><b>Last Name</b></td>
				<td><b>Username</b></td>
				<td><b>Email</b></td>
				<td><b>Status</b></td>
				
			</tr>
			<c:forEach var="u" items="${userList}">
			
			
				<tr>
					<td><input type="hidden" name="id" value="${u.personID}" />${u.personID}</td>
					<td>${u.firstName}</td>
					<td>${u.lastName}</td>
					<td>${u.username}</td>
					<td>${u.email.emailAddress}</td>
					<td>${u.status}</td>
			
				</c:forEach>
		</table>


		
		
		
	</form>


</body>
</html>