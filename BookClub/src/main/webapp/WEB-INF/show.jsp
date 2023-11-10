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
    <title>Read About a Book</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/script.js"></script>
</head>
<body>
	<div class="container">
		<div class="top_bar">
			<h1><c:out value="${book.title}"/></h1>
			<a href="/books">back to the shelves</a>
		</div>
		<div>
			<c:if test = "${book.user.id == loggedIn}">
				<p>You read <em><c:out value="${book.title}"/></em> by <c:out value="${book.author}"/></p>
				<p>Here are your thoughts:</p>
			</c:if>
			<c:if test = "${book.user.id != loggedIn}">
				<p><c:out value="${book.user.name}"/> read <em><c:out value="${book.title}"/></em> by <c:out value="${book.author}"/></p>
				<p>Here are <c:out value="${book.user.name}"/>'s thoughts:</p>
			</c:if>
		</div>
		<div id="review">
			<p><c:out value="${book.thoughts}"/></p>
		</div>
		<c:if test = "${book.user.id == loggedIn}">
			<div id="action">
				<a href="/books/${book.id}/edit" class="btn btn-warning">edit</a>
				<form action="/books/${book.id}/delete" method="post">
					<input type="hidden" name="_method" value="delete">
					<input type="submit" value="Delete" class="btn btn-danger">
				</form>
			</div>
		</c:if>
	</div>
</body>
</html>