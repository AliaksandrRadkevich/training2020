<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Settings</title>
	<c:url var="cssUrl" value="/css/main.css"/>
	<link rel="stylesheet" href="${cssUrl}">
</head>
<body bgcolor="#EFE">
    <div id="header">
        <h1>Settings</h1>
        <p id="login">${login}</p>
        <u:logout/>
    </div>
    <div id="sidebar">
        <c:set var="userRole" value="${userRole}"/>
        <c:set var="physician" value="${roles[1]}"/>
        <c:set var="patient" value="${roles[2]}"/>
        <c:set var="nurse" value="${roles[3]}"/>
        <c:choose>
            <c:when test="${userRole == physician}">
                <u:physicianSidebar/>
            </c:when>
            <c:when test="${userRole == patient}">
                <u:patientSidebar/>
            </c:when>
            <c:when test="${userRole == nurse}">
                <u:nurseSidebar/>
            </c:when>
        </c:choose>
    </div>
    <div id="content" >
        <c:url var="saveSettingsUrl" value="/settings/save.html"/>
        <form action="${saveSettingsUrl}" method="post">
            Name:
            <input type="text" name="name" value="${user.name}">
            <br>
            <br>
            Password:
            <input type="text" name="password" value="${user.password}">
            <br>
            <br>
            <button class="button" type="submit">Save</button>
        </form>
        <p color="red">${param.message}</p>
    </div>
</body>
</html>