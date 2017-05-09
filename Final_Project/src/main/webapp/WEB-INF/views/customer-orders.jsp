<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Orders</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<nav class="navbar navbar-default" style="background-color:#0F1B4F;">
<a href="${contextPath}/public/viewproducts.htm"  class="btn btn-success btn-lg">Home</a><br/>
</nav>

<h1>Orders:</h1>
	<c:choose>
		<c:when test="${fn:length(orderList)==0}">
 	You don't have any orders yet
	</c:when>
		<c:otherwise>

<c:forEach var ="o" items="${orderList}">
<h3>Order Number: ${o.orderId}</h3>
<c:forEach var ="oi" items="${o.orderItemList}">
<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
	
	<h3>Title: ${oi.product.title}</h3>
	<h3>Description: ${oi.product.description}</h3>
	<h3>Price: ${oi.product.price}</h3>
	<h3>Quantity: ${oi.quantity}</h3>
	

			
			
		
			<table class="table table-striped table-bordered table-hover">
			<tr><td>
			Enter Review
			</td></tr>
			<tr><td>
			<form action="${contextPath}/user/customer/addrating.htm">
			<select  name="rating">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			</select> &nbsp;&nbsp;&nbsp;
			<input type="hidden" name="pid" value="${oi.product.id}"/>
				Review Title<input type="text" name="reviewTitle"/></td></tr>
				<tr><td>Review Description<textarea rows="2" cols="30" name="reviewDesc"></textarea></td></tr>
				<tr><td><input type="submit" value="submit" class="btn btn-success btn-sm"></td></tr>
			
			</table>
			
			
		
			</form>
			</div>
			<div class="col-sm-2"></div>
			</div>
				
</c:forEach>
</c:forEach>

	</c:otherwise>
	</c:choose>
			



</body>
</html>