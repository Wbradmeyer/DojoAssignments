<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>New Book</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/script.js"></script>
</head>
<body>
	<div class="container">
		<div class="top_bar">
			<h1>Change Your Entry</h1>
			<a href="/books">back to the shelves</a>
		</div>
		<div class="form">
			<form:form action="/books/edit" method="put" modelAttribute="book">
				<form:hidden path="user" value="${loggedIn}"/>
				<form:hidden path="id" value="${book.id}"/>
				<p>
					<form:label path="title">Title:</form:label>
					<form:errors path="title" class="text-danger"/>
					<form:input path="title" class="form-control" style="width: 400px;"/>
				</p>
				<p>
					<form:label path="author">Author:</form:label>
					<form:errors path="author" class="text-danger"/>
					<form:input path="author" class="form-control" style="width: 400px;"/>
				</p>
				<p>
					<form:label path="thoughts">My thoughts:</form:label>
					<form:errors path="thoughts" class="text-danger"/>
					<form:textarea path="thoughts" class="form-control" rows="4"/>
				</p>
				<input type="submit" value="Submit" class="btn btn-primary"/>
			</form:form>
		</div>
	</div>
</body>
</html>