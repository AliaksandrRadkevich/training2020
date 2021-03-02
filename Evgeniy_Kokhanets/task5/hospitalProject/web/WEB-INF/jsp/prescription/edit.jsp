<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:choose>
    <c:when test="${not empty prescription}">
        <c:set var="title" value="Update prescription"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="New prescription"/>
    </c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
	<c:url var="cssUrl" value="/css/main.css"/>
	<link rel="stylesheet" href="${cssUrl}">
</head>
<body>
    <div id="header">
        <h1>${title}</h1>
        <p id="login">${login}</p>
        <ul>
            <c:url var="logoutUrl" value="/logout.html"/>
            <li>
                <input id="logout" class="button" value="Logout" type="button" onclick="location.href='${logoutUrl}'">
            </li>
        </ul>
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
	    <c:url var="prescriptionSaveUrl" value="/prescription/save.html"/>
	    <form action="${prescriptionSaveUrl}" method="post">
	        <c:if test="${not empty prescription}">
	            <input type="hidden" name="id" value="${prescription.id}">
	        </c:if>
	        Patient:
	        <select name="patient">
	            <c:forEach var="patient" items="${patients}">
	                <option value="${patient.id}">${patient.name}</option>
	            </c:forEach>
	        </select>
	        <br>
	        <br>
	        <c:if test="${userRole == nurse}">
                Physician:
                <select name="physician">
                <c:forEach var="physician" items="${physicians}">
                    <option value="${physician.id}">${physician.name}</option>
                </c:forEach>
                </select>
                <br>
                <br>
            </c:if>
	        Drugs:
	        <input type="text" name="drugs" value="${prescription.drugs}">
	        <br>
	        <br>
	        Procedure:
	        <input type="text" name="procedure" value="${prescription.procedure}">
	        <br>
	        <br>
	        <c:if test="${userRole == physician}">
		        Surgery:
		        <input type="text" name="surgery" value="${prescription.surgery}">
		        <br>
		        <br>
	        </c:if>
	        <button class="button" type="submit">Save</button>
	        <c:url var="indexUrl" value="/index.html"/>
	        <input class="button" type="button" value="Back" onclick="location.href='${indexUrl}'">
	    </form>
    </div>
</body>
</html>