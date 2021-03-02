<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.messages" />

<fmt:message var="title" key="user.delete.list.title"/>
<u:page title="${title}">
    <c:url var="userDeleteUrl" value="/user/delete.html"/>
    
    <form action="${userDeleteUrl}" method="post">
        <table class="list-table" width="100%">
            <col width="7%" valign="top">
            <col width="18.6%" valign="top">
            <col width="18.6%" valign="top">
            <col width="18.6%" valign="top">
            <col width="18.6%" valign="top">
            <col width="18.6%" valign="top">
            
            <fmt:message var="tdLogin" key="user.list.table.login"/>
            <fmt:message var="tdName" key="user.list.table.name"/>
            <fmt:message var="tdSurname" key="user.list.table.surname"/>
            <fmt:message var="tdEmail" key="user.list.table.mail"/>
            <fmt:message var="tdRole" key="user.list.table.role"/>
        
            <thead>
                <tr>
                    <td>
                        <button type="submit">
                            <fmt:message key="user.list.button.delete"/>
                        </button>
                    </td>
                    <td>${tdLogin}</td>
                    <td>${tdName}</td>
                    <td>${tdSurname}</td>
                    <td>${tdEmail}</td>
                    <td>${tdRole}</td>
                </tr>
            </thead>
        
            <tbody>
                <c:forEach var="user" items="${users}">
                    <c:url var="userEditUrl" value="/user/edit.html">
                        <c:param name="id" value="${user.id}"/>
                    </c:url>
                    <tr>
                        <td>
                            <input type="checkbox" name="id" value="${user.id}" checked>
                        </td>
                        <td>
                            <a href="${userEditUrl}">${user.login}</a>
                        </td>
                        <td>${user.name}</td>
                        <td>${user.surname}</td>
                        <td>${user.email}</td>
                        <td>${user.role.value}</td>
                    </tr>
                </c:forEach> 
            </tbody>
            
            <tfoot>
                <tr>
                    <td>
                        <button type="submit">
                            <fmt:message key="user.list.button.delete"/>
                        </button>
                    </td>
                    <td>${tdLogin}</td>
                    <td>${tdName}</td>
                    <td>${tdSurname}</td>
                    <td>${tdEmail}</td>
                    <td>${tdRole}</td>
                </tr>
            </tfoot>
        </table>
    </form>
    
    <c:url var="indexUrl" value="/user/list.html"/>
    <form action="${indexUrl}">
        <button type="submit">
            <fmt:message key="user.delete.list.button.back"/>
        </button>
    </form>
</u:page>
