<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
    <c:url var="cssUrl" value="/css/login.css"/>
    <link rel="stylesheet" href="${cssUrl}">
</head>
<body>
    <div id="header">
        <h1>Login</h1>
    </div>
    <div id="content">
        <c:if test="${not empty param.message}">
        <p style="color: red">${param.message}</p>
        </c:if>
        <c:url var="loginUrl" value="/login.html"/>
        <c:url var="signUpUrl" value="/signup.html"/>
	    <form action="${loginUrl}" method="post">
	        Login:
	        <input type="text" name="login">
	        <br>
	        <br>
	        Password:
	        <input type="password" name="password">
	        <br>
	        <br>
	        <button class="button" type="submit">Login</button>
	        <input value="Sign up" type="button" onclick="location.href='${signUpUrl}'">
	    </form>
    </div>
</body>
</html>