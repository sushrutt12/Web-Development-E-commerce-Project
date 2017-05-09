<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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


<title>Cart</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<nav class="navbar navbar-default" style="background-color:#0F1B4F;">
<a href="${contextPath}/public/viewproducts.htm" class="btn btn-success btn-lg">Products</a> &nbsp;&nbsp;&nbsp;
<c:if test="${sessionScope.user.role=='Customer'}">
	<a href="${contextPath}/user/customer.htm" class="btn btn-success btn-lg">Home</a>
	</c:if>
</nav>
	
	
	<h1>Cart:${fn:length(cart.cartItems)}</h1>
	<c:choose>
		<c:when test="${fn:length(cart.cartItems)==0}">
 	Your Cart is empty
	</c:when>
		<c:otherwise>
		<input type="hidden" name="total" />
			<div style="float: left">
				<c:forEach var="ci" items="${cart.cartItems}">
					<div  class="tablerow" style="float: left">
							<table class="table table-striped table-bordered table-hover">
							<form action="${contextPath}/public/removefromcart" method="get">
								<tr>
								
									<td><a
										href="${contextPath}/public/viewproduct.htm?pid=${ci.product.id}"><img
											height="150" width="150" src="${ci.product.filename}" /></a></td>


									<td><a
										href="${contextPath}/public/viewproduct.htm?pid=${ci.product.id}">${ci.product.title}</a>
										<br />Description: ${ci.product.description}</td>
									<td><a
										href="${contextPath}/public/profile?user=${ci.product.retailer.username}">Posted
											By: ${ci.product.retailer.username}</a></td>

									<td>Available: ${ci.product.quantity}</td>
									<td>Quantity:<input class="quantity" type="number"
										name="quantity" min="1" max="5" value="${ci.quantity}"
										step="1" disabled/></td>

									<td>Price:<input type="text" class="price" disabled value="${ci.product.price}"/></td>
									<td><a
										href="${contextPath}/public/removefromcart.htm?ciid=${ci.id}">Remove</a></td>
								</tr>
							</form>
						</table>
					</div>

				</c:forEach>
				<br /> <br />

                </div>
                <div style="float: right">

					SubTotal&nbsp;&nbsp;(${fn:length(cart.cartItems)} Item/s) <input
						type="text" id="total" disabled="disabled">
					<form action="${contextPath}/user/customer/checkout.htm">
						<input type="submit" style="float: right"
							value="Order Now" class="btn btn-success"/>
					</form>

				</div>
                
		</c:otherwise>
	</c:choose>

<script>
$(document).ready(function() {
	var v=0;
	var count=1;
	$(".tablerow").each(function(i,element) {
		v=v+parseInt($(element).find(".quantity").val()) * parseInt($(element).find(".price").val());
		  
	    });
$("#total").val(v);
});</script>
</body>
</html>