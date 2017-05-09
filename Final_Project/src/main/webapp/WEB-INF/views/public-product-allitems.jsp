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
<a href="${contextPath}/public/viewcart.htm" class="btn btn-success btn-lg">View Cart</a> &nbsp;&nbsp;&nbsp;&nbsp;
<c:if test="${sessionScope.user.role=='Customer'}">
	<a href="${contextPath}/user/customer.htm" class="btn btn-success btn-lg">Home</a>
	</c:if>
</nav>

		
	
	<br />
	<form action="${contextPath}/public/viewproducts.htm" method="get">
		<b>Search</b> <select name="search_cat">
			<option value="0" selected="selected">all</option>
			<c:forEach var="c" items="${categories}">
				<option value="${c.categoryId}">${c.title}</option>
			</c:forEach>
		</select> <input type="text"  placeholder="Search Title or Description" id="search" size="40"  name="search_query" /> <input
			type="submit" value="Go" class="btn btn-success "/>
	</form>
	<br/>
	<div id="data">
		<c:forEach var="p" items="${products}">
			<div style="float: left">
				<table class="table table-striped table-bordered table-hover">


					<tr>

						<form action="${contextPath}/public/addtocart.htm" method=get>
							<td><a href="${contextPath}/public/viewproduct.htm?pid=${p.id}"><img
									height="150" width="150" src="${p.filename}" /></a></td>
					</tr>
					<tr>
						<td><a
							href="${contextPath}/public/viewproduct.htm?pid=${p.id}">
								${p.title}</a></td>
					</tr>
					<tr>
						<td>Description: ${p.description}</td>
					</tr>
					<tr>
						<td><a
							href="${contextPath}/public/profile.htm?user=${p.retailer.username}">UserName:
								${p.retailer.username}</a></td>
					</tr>

					<tr>
						<input type="hidden" name="pid" value="${p.id}" />
						<td>Quantity:<input id="quantity" type="number"
							name="quantity" min="1" max="5" value="1" step="1" required
							size="7" /></td>


					</tr>
					<tr>
						<td>Price: ${p.price}</td>
					</tr>
					<tr>
						<td><input type="submit" value="Add to cart" /></td>
					</tr>
					</form>
				</table>
			</div>
		</c:forEach>
	</div>

<script>
$( document ).ready(function() {
   

$("#search").keyup(function(e){
	var search=this.value;
	if(search.length>2){
	var temp =  $("select").val();
	
	var val = {catid:temp,search:search};
	
	$.ajax({
		type: "POST",
		headers: { 
		    Accept : "application/json"
		},
		contentType : "application/json; charset=UTF-8",
	     url : "${contextPath}/public/ajax/products",
	     dataType : 'json',        
	     data : JSON.stringify(val),
	     success: function(result){
	    	 
	    	 
	    	 if( result.length>0){
	    		 var tags="";
	    		 for(var i in result){
		    
	    			 var tag1="<div style='float: left'>"+
	    			 "<form action='${contextPath}/public/addtocart.htm' method=get>"+
	    			 "<table border='1' cellpadding='5' cellspacing='5'>"+
		    		 "<tr><td>"+
		    		 "<a href='${contextPath}/public/viewproduct.htm?pid="+result[i].id+"'><img height='150' width='150' src="+result[i].path+" /></a></td>"+
		    		 "</tr><tr>"+
		    		 "<td><a href='${contextPath}/public/viewproduct.htm?pid="+result[i].id+"'>"+result[i].title+"</a></td>"+
		    		 "</tr><tr><td>Description:" +result[i].desc+"</td></tr><tr>"+
		    		 "<td><a href='${contextPath}/public/profile.htm?user="+result[i].userName+"'>UserName:"+result[i].userName+"</a></td>"+
		    		 "</tr><tr><input type='hidden' name='pid' value='"+result[i].id+"' />"+
		    		 "<td>Quantity:<input id='quantity' type='number' name='quantity' min='1' max='5' value='1' step='1' required size='7' /></td>"+
		    		 "</tr><tr><td>Price: "+result[i].price+"</td>"+
		    		 "</tr><tr><td><input type='submit' value='Add to cart' /></td></tr></table>"+
		    		 "</form></div>"
		    		 
		    		 
		    		 tags=tags+tag1;
	    	 }
		    		 	    	 
	    	 $("#data").html(tags);

	    	 }
	    	 else{
	    		 $("#data").html("No products match your search criteria");
	    	 }
	    	 
	    		 
	     }
		
	});
	}
});



});
</script>
	
</body>
</html>