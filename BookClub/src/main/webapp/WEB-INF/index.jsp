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
    <title>Register and Login</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/script.js"></script>
</head>
<body>
	<div class="container">
		<h1>Welcome!</h1>
		<p>Join our growing community.</p>
		<div id="boxes">
			<div>
				<h2>Register</h2>
				<form:form action="/register" method="post" modelAttribute="newUser">
					<p>
						<form:label path="name">User Name:</form:label>
						<form:errors path="name" class="text-danger"/>
						<form:input path="name" class="form-control" style="width: 300px;"/>
					</p>
					<p>
						<form:label path="email">Email:</form:label>
						<form:errors path="email" class="text-danger"/>
						<form:input path="email" class="form-control" style="width: 300px;"/>
					</p>
					<p>
						<form:label path="password">Password:</form:label>
						<form:errors path="password" class="text-danger"/>
						<form:input type="password" path="password" class="form-control" style="width: 300px;"/>
					</p>
					<p>
						<form:label path="confirm">Confirm PW:</form:label>
						<form:errors path="confirm" class="text-danger"/>
						<form:input type="password" path="confirm" class="form-control" style="width: 300px;"/>
					</p>
					<input type="submit" value="Submit" class="btn btn-success">
				</form:form>
			</div>
			<div>
				<c:out value="${error}"/>
				<h2>Login</h2>
				<form:form action="/login" method="post" modelAttribute="newLogin">
					<p>
						<form:label path="email">Email:</form:label>
						<form:errors path="email" class="text-danger"/>
						<form:input path="email" class="form-control" style="width: 300;"/>
					</p>
					<p>
						<form:label path="password">Password:</form:label>
						<form:errors path="password" class="text-danger"/>
						<form:input type="password" path="password" class="form-control" style="width: 300px;"/>
					</p>
					<input type="submit" value="Submit" class="btn btn-success">
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>