<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Retailer Home</title>
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
<a href="${contextPath}/user/logout" class="btn btn-success btn-lg">logout</a>
</nav>
<div style="background-color:#4BC6FE" class="container">


<center>


<table>

<tr><td><h1 >Welcome, ${user.firstName}</h1></td></tr>
<tr><td><p>What would you like to do?</p></td></tr>
<tr><td><a href="${contextPath}/user/retailer/category/add.htm" class="btn btn-success btn-lg" >Add Category</a></td></tr> 
<tr><td><br/></td></tr> 
<tr><td><a href="${contextPath}/user/retailer/product/add.htm" class="btn btn-success btn-lg">Add Product</a></td></tr>
<tr><td><br/></td></tr>
<tr><td><a href="${contextPath}/user/retailer/product/list.htm" class="btn btn-success btn-lg">View all my products</a> </td></tr>
<tr><td><br/></td></tr>
<tr><td><a href="${contextPath}/user/retailer/file/productsreport.xls" class="btn btn-success btn-lg">Generate report of all my products and rating</a></td></tr>
<tr><td><br/></td></tr>
</table>
</center>
</div>
</body>
</html>