<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="property.messages" />

<fmt:message var="title" key="room.delete.list.title" />

<u:page title="${title}">
    <c:url var="roomDeleteUrl" value="/room/delete.html" />
    <form action="${roomDeleteUrl}" method="post">
        <table class="list-table" width="100%">
            <col width="7%" valign="top">
            <col width="23.25%" valign="top">
            <col width="23.25%" valign="top">
            <col width="23.25%" valign="top">
            <col width="23.25%" valign="top">

            <fmt:message var="tdNumber" key="room.list.table.number" />
            <fmt:message var="tdType" key="room.list.table.type" />
            <fmt:message var="tdSeats" key="room.list.table.seats" />
            <fmt:message var="tdPrice" key="room.list.table.price" />

            <thead>
                <tr>
                    <td>
                        <button type="submit">
                            <fmt:message key="user.list.button.delete" />
                        </button>
                    </td>
                    <td>${tdNumber}</td>
                    <td>${tdType}</td>
                    <td>${tdSeats}</td>
                    <td>${tdPrice}$</td>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="room" items="${rooms}">
                    <c:url var="roomEditUrl" value="/room/edit.html">
                        <c:param name="id" value="${room.id}" />
                    </c:url>
                    <tr>
                        <td>
                            <input type="checkbox" name="id" value="${room.id}" checked>
                        </td>
                        <td>
                            <a href="${roomEditUrl}">${room.roomNumber}</a>
                        </td>
                        <td>${room.roomType.value}</td>
                        <td>${room.seats}</td>
                        <td>${room.price}</td>
                    </tr>
                </c:forEach>
            </tbody>

            <tfoot>
                <tr>
                    <td>
                        <button type="submit">
                            <fmt:message key="user.list.button.delete" />
                        </button>
                    </td>
                    <td>${tdNumber}</td>
                    <td>${tdType}</td>
                    <td>${tdSeats}</td>
                    <td>${tdPrice}$</td>
                </tr>
            </tfoot>
        </table>
    </form>

    <c:url var="indexUrl" value="/room/list.html" />
    <form action="${indexUrl}">
        <button type="submit">
            <fmt:message key="room.delete.list.button.back" />
        </button>
    </form>
</u:page>
