<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="property.messages" />

<fmt:message var="title" key="order.list.title" />

<u:page title="${title}">
    <c:if test="${not empty param.message}">
        <p class="text-red">
            <fmt:message key="${param.message}" />
        </p>
    </c:if>

    <c:url var="orderDeleteUrl" value="/order/delete_list.html" />
    <form action="${orderDeleteUrl}" method="post">

        <table class="list-table" width="100%">
            <col width="7%" valign="top">
            <col width="3%" valign="top">
            <col width="16%" valign="top">
            <col width="16%" valign="top">
            <col width="3%" valign="top">
            <col width="16%" valign="top">
            <col width="16%" valign="top">
            <col width="7%" valign="top">
            <col width="9%" valign="top">
            <col width="7%" valign="top">

            <fmt:message var="tdID" key="order.list.table.id.order" />
            <fmt:message var="tdCreationDate" key="order.list.table.date.creation" />
            <fmt:message var="tdUserId" key="order.list.table.id.user" />
            <fmt:message var="tdSeats" key="order.list.table.seats" />
            <fmt:message var="tdType" key="order.list.table.type" />
            <fmt:message var="tdStartDate" key="order.list.table.date.start" />
            <fmt:message var="tdEndDate" key="order.list.table.date.end" />
            <fmt:message var="tdTotalNights" key="order.list.table.total.nights" />
            <fmt:message var="tdRoom" key="order.list.table.id.room" />
            <fmt:message var="tdTotalPrice" key="order.list.table.total.price" />

            <thead>
                <tr>
                    <td>
                        <button type="submit">
                            <fmt:message key="order.list.button.delete" />
                        </button>
                    </td>
                    <td>${tdID}</td>
                    <td>${tdCreationDate}</td>
                    <td>${tdUserId}</td>
                    <td>${tdSeats}</td>
                    <td>${tdType}</td>
                    <td>${tdStartDate}</td>
                    <td>${tdEndDate}</td>
                    <td>${tdTotalNights}</td>
                    <td>${tdRoom}</td>
                    <td>${tdTotalPrice}$</td>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="order" items="${orders}">
                    <c:url var="orderEditUrl" value="/order/edit.html">
                        <c:param name="id" value="${order.id}" />
                    </c:url>

                    <c:choose>
                        <c:when test="${adminDataAccess ? adminDataAccess : (order.user.id eq session_user.id)}">
                            <tr>
                                <td>
                                    <input type="checkbox" name="id" value="${order.id}">
                                </td>
                                <td>
                                    <a href="${orderEditUrl}">${order.id}</a>
                                </td>
                                <td>${order.creationDate}</td>
                                <td>${order.user.name} ${order.user.surname}</td>
                                <td>${order.roomSeats}</td>
                                <td>${order.roomType.value}</td>
                                <td>${order.startDate}</td>
                                <td>${order.endDate}</td>
                                <td>${order.totalNights}</td>
                                <td>${order.room.roomNumber}</td>
                                <td>
                                    <c:if test="${not empty order.room.id}">
                                        ${order.totalPrice}
                                    </c:if>
                                </td>
                            </tr>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <td>
                        <button type="submit">
                            <fmt:message key="order.list.button.delete" />
                        </button>
                    </td>
                    <td>${tdID}</td>
                    <td>${tdCreationDate}</td>
                    <td>${tdUserId}</td>
                    <td>${tdSeats}</td>
                    <td>${tdType}</td>
                    <td>${tdStartDate}</td>
                    <td>${tdEndDate}</td>
                    <td>${tdTotalNights}</td>
                    <td>${tdRoom}</td>
                    <td>${tdTotalPrice}$</td>
                </tr>
            </tfoot>
        </table>
    </form>

    <c:url var="orderEditUrl" value="/order/edit.html" />
    <form action="${orderEditUrl}">
        <button type="submit">
            <fmt:message key="order.list.button.create" />
        </button>
    </form>
</u:page>
