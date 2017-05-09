<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Application </title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
	

</style>
	
	
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<nav class="navbar navbar-default" style="background-color:#0F1B4F;">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a  href="${contextPath}" class="btn btn-success btn-lg" >Shoppers Paradise</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
	  
        <a href="mailto:sushrutt.12@gmail.com"  class="btn btn-success btn-lg" >Support</a>
        
      </ul>
    </div>
  </div>
</nav>
<div class="container" style="background-color:#4BC6FE">
<center>

	<a class="btn btn-success" href="public/registercustomer.htm">Register a new User</a><br/>

	<h2>Welcome to Shoppers Paradise</h2><br/>
	<form action="user/login.htm" method="post">
	
		<table>
		<tr>
		    <td><b>User Name:</b></td>
		    <td><input name="username" size="30" required="required" /></td>
		</tr>
		<tr><td><br/></td></tr>
		<tr>
		    <td><b>Password:</b></td>
		    <td><input type="password" name="password" size="30" required="required"/></td>
		</tr>
		<tr><td><br/></td></tr>
		<tr>
		    <td colspan="2"><input type="submit" style="float:right" value="Login" class="btn btn-primary" /></td>
		</tr>
				
		</table>
<center>
	</form>
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
<a class="btn btn-success" href="${contextPath}/public/registerretailer.htm">Register a new Retailer</a><br/>
<br/>
</div>
</body>
</html>

