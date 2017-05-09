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
<title>Add Product Form</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<nav class="navbar navbar-default" style="background-color:#0F1B4F;">
<a href="${contextPath}/user/retailer.htm" class="btn btn-success btn-lg">Home</a><br/>
</nav>

<div style="background-color:#4BC6FE" class="container">
<center>
	<h2>Posting a New product</h2>
	

	<form:form action="${contextPath}/user/retailer/product/add.htm" method="post" enctype="multipart/form-data"
		commandName="product">

		<table>
			<tr>
				<td>Posted By</td>
				<td>${sessionScope.user.username}</td>
				<td><form:hidden path="postedBy"
						value="${sessionScope.user.personID}" /></td>
			</tr>
<tr><td><br/></td></tr>
			<tr>
				<td>Category:</td>
				<td><form:select path="categories" items="${categories}"
						multiple="true" required="required" /></td>
			</tr>
<tr><td><br/></td></tr>
			<tr>
				<td>Product Title:</td>
				<td><form:input type="text" path="title" size="30" required="required"/></td>
			</tr>
<tr><td><br/></td></tr>
			<tr>
				<td>Product Price:</td>
				<td><form:input type="double" path="price" size="30" required="required"/></td>
			</tr>
			<tr><td><br/></td></tr>
			<tr>
				<td>Quantity:</td>
				<td><form:input type="number" path="quantity" size="30" min="1" max="50" step="1" required="required"/></td>
			</tr>
			<tr><td><br/></td></tr>
			<tr>
				<td>Description:</td>
				<td><form:input type="text" path="description" size="30" required="required"/></td>
			</tr>
			<tr><td><br/></td></tr>
			<tr>
				<td>Select photo:</td>
	 <td><input type="file" name="photo" /><br/></td>
	 </tr>
	 <tr><td><br/></td></tr>
			<tr>
				<td colspan="2"><input type="submit" value="Post Product" class="btn btn-success btn-lg"/></td>
			</tr>
		</table>

	</form:form>
</center>
</div>
</body>
</html>