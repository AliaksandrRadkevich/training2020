<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User list</title>
    <c:url var="cssUrl" value="/css/login.css"/>
    <link rel="stylesheet" href="${cssUrl}">
</head>
<body bgcolor="#EFE">
    <div id="header">
        <h1>User list</h1>
        <u:logout/>
    </div>
    <div id="content">
	    <c:url var="userDeleteUrl" value="/user/delete.html"/>
	    <form action="${userDeleteUrl}" method="post">
		    <ul>
		       <c:forEach var="user" items="${users}">
		            <c:url var="userEditUrl" value="/user/edit.html">
		                <c:param name="id" value="${user.id}"/>
		            </c:url>
		            <li>
		               <input type="checkbox" name="id" value="${user.id}">
		               <a href="${userEditUrl}">${user.login}</a>
		            </li>
		            <li>${user}</li>
		       </c:forEach>
		    </ul>
		    <c:url var="userEditUrl" value="/user/edit.html"/>
		    <input value="Add new user" type="button" onclick="location.href='${userEditUrl}'">
		    <button class="button" type="submit">Delete</button>
		</form>
    </div>
</body>
</html>