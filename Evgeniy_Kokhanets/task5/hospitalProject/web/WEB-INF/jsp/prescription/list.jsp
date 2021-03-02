<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My prescriptions</title>
	<c:url var="cssUrl" value="/css/main.css"/>
	<link rel="stylesheet" href="${cssUrl}">
</head>
<body>
    <div id="header">
	    <h1>My prescriptions</h1>
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
    <div id="content">
    <c:if test="${not empty param.message}">
        <p style="color: #228B22">${param.message}</p>
    </c:if>
    <c:choose>
	    <c:when test="${userRole == physician}">
		    <c:url var="prescriptionDeleteUrl" value="/prescription/delete.html"/>
		    <form action="${prescriptionDeleteUrl}" method="post">
		        <table border="1">
		            <caption>Prescription list</caption>
		            <tr>
		                <th> </th>
		                <th>Patient</th>
		                <th>Drugs</th>
		                <th>Procedure</th>
		                <th>Surgery</th>
		                <th>Date</th>
		                <th> </th>
		            </tr>
		            <c:forEach var="entry" items="${map}">
		                <c:url var="prescriptionEditUrl" value="/prescription/edit.html">
		                    <c:param name="id" value="${entry.key.id}"/>
		                </c:url>
		                <c:url var="patientListUrl" value="/patient/list.html">
		                    <c:param name="patientId" value="${entry.key.patientId}"/>
		                </c:url>
			            <tr>
				               <td><input type="checkbox" name="id" value="${entry.key.id}"></td>
				               <td><a href="${patientListUrl}">${entry.value.name}</a></td>
				               <td>${entry.key.drugs}</td>
				               <td>${entry.key.procedure}</td>
				               <td>${entry.key.surgery}</td>
				               <td>${entry.key.date}</td>
				               <td>
					               <input class="button" value="Edit" type="button" 
					               onclick="location.href='${prescriptionEditUrl}'">
				               </td>
			            </tr>
		            </c:forEach>
		        </table>
		        <br>
		        <c:url var="prescriptionEditUrl" value="/prescription/edit.html"/>
		        <input class="button" value="Add new prescription" type="button"
		         onclick="location.href='${prescriptionEditUrl}'">
		        <button class="button" type="submit">Delete</button>
		    </form>
	    </c:when>
	    <c:when test="${userRole == patient}">
	       <table border="1">
                <caption>Prescription list</caption>
                <tr>
                    <th>Physician</th>
                    <th>Drugs</th>
                    <th>Procedure</th>
                    <th>Surgery</th>
                    <th>Date</th>
                </tr>
                <c:forEach var="entry" items="${map}">
                    <tr>
                        <td>${entry.value.name}</td>
                        <td>${entry.key.drugs}</td>
                        <td>${entry.key.procedure}</td>
                        <td>${entry.key.surgery}</td>
                        <td>${entry.key.date}</td>
                    </tr>
                </c:forEach>
            </table>
	    </c:when>
    </c:choose>
    </div>
</body>
</html>