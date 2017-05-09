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
<title>View Product</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<input type="button" value="Back" onclick="window.history.go(-1)" /> 
	
	
		

			<table border="1" cellpadding="5" cellspacing="5">
				<tr>
					<td rowspan="4"><img height="200" width="200" src="${p.filename}" /></td></tr>
							</tr>
							<tr><td>${p.title}</td>		</tr>
							<tr><td>Message: ${p.description}</td>		</tr>
							<tr><td>UserName: ${p.retailer.username}</td>		</tr>
				

			</table>
		

	</body>
</html>