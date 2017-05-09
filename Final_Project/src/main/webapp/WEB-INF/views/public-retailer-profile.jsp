<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Retailer Profile</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<nav class="navbar navbar-default" style="background-color:#0F1B4F;">
	<a href="${contextPath}/public/viewproducts.htm/" class="btn btn-success btn-lg">Products Page</a>

</nav>


	 <h3>Products posted by: ${user.firstName} ${user.lastName}</h3></br>


<div>
<c:forEach var="p" items="${products}">
			<div style="float: left">
				
		<table class="table table-striped table-bordered table-hover">
		
				<tr>
					<td><a href="${contextPath}/public/viewproduct.htm?pid=${p.id}"><img height="150" width="150" src="${p.filename}" /></a></td>
				</tr>
				<tr>
					<td>${p.title}</td>
				</tr>
				<tr>
					<td>Description: ${p.description}</td>
				</tr>
				<tr>
					<td>UserName: ${p.retailer.username}</td>
				</tr>
            
			</table>
			</div>
		
	</c:forEach>
	</div>
</body>
</html>