<%@tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<ul>
    <c:url var="logoutUrl" value="/logout.html"/>
    <li><input id="logout" class="button" value="Logout" type="button" onclick="location.href='${logoutUrl}'"></li>
</ul>