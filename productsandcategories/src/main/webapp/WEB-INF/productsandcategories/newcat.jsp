<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>New Category</title>
</head>
<body>
	<h3>New Category</h3>
	<form:form action="/categories/new" method="POST" modelAttribute="categoryObj">
			<p>
				<form:label path="name">Name</form:label>
				<form:errors path="name"/>
				<form:input path="name"/>
			</p> 
			
    		<input type="submit" value="Create"/>
		</form:form>  
</body>
</html>