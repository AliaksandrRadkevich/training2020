<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:choose>
    <c:when test="${not empty user}">
        <c:set var="title" value="Update user ${user.login}"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="New user"/>
    </c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
    <c:url var="cssUrl" value="/css/login.css"/>
    <link rel="stylesheet" href="${cssUrl}">
</head>
<body>
    <div id="header">
        <h1>${title}</h1>
        <u:logout/>
    </div>
    <div id="content">
	    <c:url var="userSaveUrl" value="/user/save.html"/>
	    <form action="${userSaveUrl}" method="post">
	        <c:if test="${not empty user}">
	            <input type="hidden" name="id" value="${user.id}">
	        </c:if>
	        Login:
	        <input type="text" name="login" value="${user.login}">
	        <br>
	        <br>
	        Password:
	        <input type="text" name="password" value="${user.password}">
	        <br>
	        <br>
	        Name:
	        <input type="text" name="name" value="${user.name}">
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
	        <button class="button" type="submit">Save</button>
	    </form>
    </div>
</body>
</html>