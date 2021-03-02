<%@tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url var="myPrescriptionsUrl" value="/prescription/list.html"/>
<c:url var="myRecordsUrl" value="/record/list.html"/>
<c:url var="findPatientUrl" value="/patient/find.html"/>
<c:url var="settingsUrl" value="/settings.html"/>

<div id="sidebarItems">
	<ul>
	    <li><a href="${myPrescriptionsUrl}">My prescriptions</a></li>
	    <li><a href="${myRecordsUrl}">My records</a></li>
	    <li><a href="${findPatientUrl}">Find patient</a></li>
	    <li><a href="${settingsUrl}">Settings</a></li>
	</ul>
</div>