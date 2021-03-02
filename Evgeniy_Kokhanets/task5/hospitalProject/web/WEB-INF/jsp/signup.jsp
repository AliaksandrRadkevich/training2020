<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New user</title>
    <c:url var="cssUrl" value="/css/login.css"/>
    <link rel="stylesheet" href="${cssUrl}">
</head>
<body>
    <div id="header">
        <h1>New user</h1>
    </div>
    <div id="content">
	    <c:if test="${not empty param.message}">
	        <p style="color: red">${param.message}</p>
	    </c:if>
	    <c:url var="saveUrl" value="/save.html"/>
	    <form action="${saveUrl}" method="post">
	        Login:
	        <input type="text" name="login">
	        <br>
	        <br>
	        Password:
	        <input type="text" name="password">
	        <br>
	        <br>
	        Name:
	        <input type="text" name="name">
	        <br>
	        <br>
	        Role:
	        <select name="role">
	            <c:forEach var="role" items="${roles}">
	                <option value="${role}">${role}</option>
	            </c:forEach>
	        </select>
	        <br>
	        <br>
	        <button class="button" type="submit">Submit</button>
	        <c:url var="loginUrl" value="/login.html"/>
	        <input value="Back" type="button" onclick="location.href='${loginUrl}'">
	    </form>
    </div>
</body>
</html>