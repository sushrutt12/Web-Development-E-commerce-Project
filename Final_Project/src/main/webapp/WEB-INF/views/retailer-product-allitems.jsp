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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Products</title>

</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<nav class="navbar navbar-default" style="background-color:#0F1B4F;">
<a href="${contextPath}/user/retailer.htm" class="btn btn-success btn-lg">Home</a><br/>
</nav>
	<br />
	
	<c:forEach var="p" items="${products}">
		
			<div style="float: left">
				<table class="table table-striped table-bordered table-hover">


					<tr>
						<c:if test="${p.status!='Offline'}">
						<form action="${contextPath}/user/retailer/product/remove.htm"
							method=get>
					</c:if>	
					<c:if test="${p.status!='Active'}">
						<form action="${contextPath}/user/retailer/product/activate.htm"
							method=get>
					</c:if>
<%-- 						<form action="${contextPath}/user/retailer/product/remove.htm" --%>
<%-- 							method=get> --%>
							<td><a
								href="${contextPath}/public/viewproduct.htm?pid=${p.id}"><img
									height="150" width="150" src="${p.filename}" /></a></td>
					</tr>
					<tr>
						<td class="breakWord"><a
							href="${contextPath}/public/viewproduct.htm?pid=${p.id}">
								${p.title}</a></td>
					</tr>
					<tr>
						<td class="breakWord">Description: ${p.description}</td>
					</tr>
					
					<tr>
						<input type="hidden" name="pid" value="${p.id}" />
						<td>Quantity:<input id="quantity" type="number"
							name="quantity" min="1" max="30" value="${p.quantity}" step="1" required
							size="7" /></td>


					</tr>
					<tr>
						<td>Price: ${p.price}</td>
					</tr>
					<tr>
						<td>Status: ${p.status}</td>
					</tr>
					<tr>
					<c:if test="${p.status!='Offline'}">
						<td><input type="submit" value="Set Offline" /></td>
					</c:if>	
					<c:if test="${p.status!='Active'}">
						<td><input type="submit" value="Set Online" /></td>
					</c:if>
					</tr>
					</form>
				</table>
			</div>
		
	</c:forEach>


</body>
</html>