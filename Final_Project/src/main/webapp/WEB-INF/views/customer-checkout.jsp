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

<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Home</title>
</head>
<body>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}/public/viewproducts.htm">Home</a>
	<h1> Your Cards:</h1>
	<c:choose>
	<c:when test="${fn:length(cardList)==0}">
 	You have no cards saved
 	<a href="${contextPath}/user/customer/addcard.htm">Add Card</a>
	</c:when>
	<c:otherwise>
	<div>
		<c:forEach var="c" items="${cardList}">
			<div style="float: left">
				<table border="1" cellpadding="5" cellspacing="5">
					<form action="${contextPath}/user/customer/removecard.htm" method="get">
					<tr>
						<td><a
							href="${contextPath}/user/customer/viewcard.htm?cid=${c.cardID}">${c.nameOnCard}</a></td>
						<td>Card No.:${c.cardNumber}	 </td>
						<td>Expiration Month: ${c.expirationMonth}</td>
					<td>Expiration Month: ${c.expirationYear}</td>
						<td><a
							href="${contextPath}/user/customer/viewcard.htm?cid=${c.cardID}">Remove</a></td>
					</tr>
					</form>
				</table>
			</div>

		</c:forEach>
		
<br/>
<br/>
<form action="${contextPath}/user/customer/payment.htm" method="post">
	<input type="submit" style="float: right" value="Proceed with payment"/>
</form>

</div>
	</div>
	</c:otherwise>
</c:choose>

</body>

</html>