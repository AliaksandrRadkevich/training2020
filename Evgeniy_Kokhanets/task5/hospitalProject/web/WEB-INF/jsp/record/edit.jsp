<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:choose>
    <c:when test="${not empty record}">
        <c:set var="title" value="Update record"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="New record"/>
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
        <u:physicianSidebar/>
    </div>
    <div id="content">
        <c:url var="recordSaveUrl" value="/record/save.html"/>
        <form action="${recordSaveUrl}" method="post">
            <c:if test="${not empty record}">
                <input type="hidden" name="id" value="${record.id}">
            </c:if>
            Patient:
            <select name="patient">
                <c:forEach var="patient" items="${patients}">
                    <option value="${patient.id}">${patient.name}</option>
                </c:forEach>
            </select>
            <br>
            <br>
            Medical Assessment:
            <br>
            <input type="text" name="medicalAssessment" value="${record.medicalAssessment}">
            <br>
            <br>
            Hospitalization date:
            <input type="date" name="hospitalizationDate" value="${record.hospitalizationDate}">
            <br>
            <br>
            Discharge date:
            <input type="date" name="dischargeDate" value="${record.dischargeDate}">
            <br>
            <br>
            <button class="button" type="submit">Save</button>
            <c:url var="indexUrl" value="/index.html"/>
            <input class="button" type="button" value="Back" onclick="location.href='${indexUrl}'">
        </form>
    </div>
</body>
</html>