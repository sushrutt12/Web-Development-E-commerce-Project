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
<title>Add Category Form</title>
</head>
<body>


	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<nav class="navbar navbar-default" style="background-color:#0F1B4F;">
<a href="${contextPath}/user/retailer.htm" class="btn btn-success btn-lg">Home</a><br/>
</nav>
<div style="background-color:#4BC6FE" class="container">
<center>
	<h2>Add a New Category</h2>


	<form:form action="${contextPath}/user/retailer/category/add.htm" method="post" commandName="category">

		<table>
			<tr>
				<td>Category Title:</td>
				<td><form:input path="title" size="30" required="required" /> <font color="red"><form:errors
							path="title" /></font></td>
			</tr>
			<tr><td><br/></td></tr>
			
			<tr><td></td>
				<td style="float:right"><input  class="btn btn-success" type="submit" value="Create Category" /></td>
			</tr>
		</table>

	</form:form>
</center>
</div>
</body>
</html>