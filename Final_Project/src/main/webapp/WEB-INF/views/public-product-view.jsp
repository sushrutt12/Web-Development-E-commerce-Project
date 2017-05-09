<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Product</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<style>
	
	#left{
	text-align: left;
	}
	</style>
	
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<nav class="navbar navbar-default" style="background-color:#0F1B4F;">
<input type="button" value="Back" onclick="window.history.go(-1)" class="btn btn-success btn-lg" />
</nav>
	 
	
	
	
	
		

	<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
			<table class="table table-striped table-bordered">
				<tr>
					<td rowspan="4"><img height="200" width="200" src="${p.filename}" /></td></tr>
							</tr>
							<tr id="left"><td><h3>Title:${p.title}</h3></td></tr>
							<tr><td><h4>Description: ${p.description}</h4></td>		</tr>
							<tr><td><h4>Retailer: ${p.retailer.username}</h4></td>		</tr>
				

			</table>
			</div>
		<div class="col-sm-2"></div>
			</div>
			
			
			<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">
			
			    <h3>Review Lists here:</h3>
			
			
			 <div class="panel-group">
			 <c:forEach var="r" items="${p.ratingList}">
               <div class="panel panel-primary">
                 <div class="panel-heading">Title: ${r.title}</div>
                 <div class="panel-body">
                     Rating: ${r.score}</br>
			Description: ${r.description}</br>
			Posted by: ${r.postedBy}
	              </div>
                </div>
                </c:forEach>
            </div>
			
			</div>
		<div class="col-sm-3"></div>
		</div>	
				
			
			
				
			
			
			
		

	</body>
</html>