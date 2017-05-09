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
<title>List Products</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<ul>
	<li>	<a href="${contextPath}/user/retailer">Home</a></li>
	<li><a href="${contextPath}/public/viewcart">View Cart</a></li>
	</ul>
	<br />
	<c:forEach var="p" items="${products}">
		<div style="float: left">
			<table border="1" cellpadding="5" cellspacing="5">


				<tr>

					<form action="${contextPath}/public/addtocart" method=get>
					<td><a href="${contextPath}/public/viewproduct?pid=${p.id}"><img height="150" width="150" src="${p.filename}" /></a></td>

				</tr>
				<tr>
					<td><a href="${contextPath}/public/viewproduct?pid=${p.id}"> ${p.title}</a></td>
				</tr>
				<tr>
					<td>Message: ${p.message}</td>
				</tr>
				<tr>
					<td><a href="${contextPath}/user/profile?user=${p.user.username}">UserName:
							${p.user.username}</a></td>
				</tr>
				<tr>
					<input type="hidden" name="pid" value="${p.id}"/>
					<td>Quantity:<input id="quantity" type="number" name="quantity" min="1" max="5" value="1" step="1" required size="7"/></td>
					
					
				</tr>
				<tr>
					<td>Price: ${p.price}</td>
				</tr>
				<tr>
					<td><input type="submit" value="Add to cart"/></td>
				</tr>
</form>
			</table>
		</div>
	</c:forEach>
	
	
</body>
</html>