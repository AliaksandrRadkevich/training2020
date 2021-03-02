<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Patient list</title>
	<c:url var="cssUrl" value="/css/main.css"/>
	<link rel="stylesheet" href="${cssUrl}">
</head>
<body>
    <div id="header">
        <h1>Patient list</h1>
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
        <h1>${patient.name}</h1>
        <c:if test="${userRole == physician}">
        <table border="1">
            <caption>Sickness record list</caption>
            <tr>
                <th>Physician</th>
                <th>Medical Assessment</th>
                <th>Hospitalization Date</th>
                <th>Discharge Date</th>
            </tr>
           <c:forEach var="entry" items="${records}">
               <tr>
	               <td>${entry.value.name}</td>
	               <td>${entry.key.medicalAssessment}</td>
	               <td>${entry.key.hospitalizationDate}</td>
	               <td>${entry.key.dischargeDate}</td>
               </tr>
           </c:forEach>
        </table>
        <br>
        <br>
        </c:if>
        <table border="1">
            <caption>Prescription list</caption>
            <tr>
	            <th>Physician</th>
	            <th>Drugs</th>
	            <th>Procedure</th>
	            <th>Surgery</th>
	            <th>Date</th>
	            <th> </th>
            </tr>
		    <c:forEach var="entry" items="${prescriptions}">
		        <c:url var="prescriptionEditUrl" value="/prescription/edit.html">
                    <c:param name="id" value="${entry.key.id}"/>
                </c:url>
			    <tr>
				    <td>${entry.value.name}</td>
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
    </div>
</body>
</html>