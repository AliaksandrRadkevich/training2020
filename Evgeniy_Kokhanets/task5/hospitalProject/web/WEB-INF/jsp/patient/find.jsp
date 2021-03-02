<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Find patient</title>
	<c:url var="cssUrl" value="/css/main.css"/>
	<link rel="stylesheet" href="${cssUrl}">
</head>
<body>
	<div id="header">
	   <h1>Find patient</h1>
	   <p id="login">${login}</p>
	   <u:logout/>
	</div>
	<div id="sidebar">
	   <c:set var="userRole" value="${userRole}"/>
	   <c:set var="physician" value="${roles[1]}"/>
	   <c:set var="nurse" value="${roles[3]}"/>
	   <c:choose>
		   <c:when test="${userRole == physician}">
		      <u:physicianSidebar/>
		   </c:when>
		   <c:when test="${userRole == nurse}">
		      <u:nurseSidebar/>
		   </c:when>
	   </c:choose>
	</div>
	<div id="content">
	   <div id="search">
	   <c:url var="findPatientUrl" value="/patient/get.html"/>
		   <form action="${findPatientUrl}" method="get">
			   Find patient:
			   <input type="search" name="patientName">
			   <button class="button" type="submit">Search</button>
		   </form>
	   </div>
	   <div id="searchResult">
	       <c:forEach var="patient" items="${patients}">
	           <ul>
	               <c:url var="patientListUrl" value="/patient/list.html">
                        <c:param name="patientId" value="${patient.id}"/>
                   </c:url>
	               <li><a href="${patientListUrl}">${patient.name}</a></li>
	           </ul>
	       </c:forEach>
	       <p style="color: red">${param.message}</p>
	   </div>
	</div>
</body>
</html>