<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<title>Display</title>
</head>
<body>
	<div class="container">
		<h2><c:out value="${product.name}"/> </h2>
		<div class="container">
  			<div class="row">
    			<div class="col-sm">
      				<h5>Categories</h5>
      				<ul>
      					<c:forEach items="${productCategories}" var="pC">
      						<li> <c:out value="${pC.name}"/>  </li>
      					</c:forEach>
      				</ul>
    			</div>
    			<div class="col-sm">
      				
   	 			</div>
    			<div class="col-sm">
      				<form:form action="/products/${product.id}" method="POST" modelAttribute="categoryProductObj">
						<p>
							<form:label path="category">Add Category</form:label>
							<form:errors path="category"/>
							<form:select path="category">
							<%-- <form:input type="hidden" path="category" value="${p.id}"/> --%>
							<c:forEach items="${allCategories}" var="c">
								<option value="${c.id}"> <c:out value="${c.name}"/> </option>
							</c:forEach> 
							</form:select>
						</p>
    					<input type="submit" value="Add"/>
					</form:form>  
    			</div>
  			</div>
		</div>
	</div>
	
	
</body>
</html>