<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="property.messages" />

<fmt:message var="titleEdit" key="order.edit.title.edit" />
<fmt:message var="titleNew" key="order.edit.title.new" />

<c:choose>
    <c:when test="${not empty order}">
        <c:set var="title" value="${titleEdit} &#8470;${order.id}" />
    </c:when>
    <c:otherwise>
        <c:set var="title" value="${titleNew}" />
    </c:otherwise>
</c:choose>

<u:page title="${title}">
    <c:url var="orderSaveUrl" value="/order/save.html" />

    <table class="input-table" align="center">
        <tbody>
            <form action="${orderSaveUrl}" method="post">
                <c:if test="${not empty order}">
                    <input type="hidden" name="id" value="${order.id}">
                </c:if>

                <c:set var="orderUser" value="${order.user.name} ${order.user.surname}" />
                <c:set var="sessionUser" value="${session_user.name} ${session_user.surname}" />

                <tr>
                    <td class="input-table-0lax" colspan="3">
                        <label>
                            <strong><fmt:message key="order.edit.form.label.customer" />:</strong>
                        </label>
                    </td>
                    <td class="input-table-0lax" colspan="3">
                        ${not empty order.user ? orderUser : sessionUser}
                    </td>
                </tr>
                <tr>
                    <td class="input-table-0lax" colspan="3">
                        <label>
                            <strong><fmt:message key="order.edit.form.label.room.parameters" />:</strong>
                        </label>
                    </td>
                    <td class="input-table-0lax" colspan="3"></td>
                </tr>
                <tr>
                    <td class="input-table-0lax" colspan="3">
                        <label>
                            <fmt:message key="order.edit.form.label.room.seats" />:
                            <span class="text-red">*</span>
                        </label>
                    </td>
                    <td class="input-table-0lax" colspan="3">
                        <input type="number" name="seats" min="1" value="${order.roomSeats}">
                    </td>
                </tr>
                <tr>
                    <td class="input-table-0lax" colspan="3">
                        <label>
                            <fmt:message key="order.edit.form.label.room.type" />:
                            <span class="text-red">*</span>
                        </label>
                    </td>
                    <td class="input-table-0lax" colspan="3">
                        <select name="roomType">
                            <c:forEach var="type" items="${roomTypes}">
                                <option ${type.value eq order.roomType.value ? 'selected' : ''} value="${type.id}">
                                    ${type.value}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <c:if test="${adminDataAccess}">
                    <tr>
                        <td class="input-table-0lax" colspan="3">
                            <label>
                                <strong><fmt:message key="order.edit.form.label.room.confirm" />:</strong>
                            </label>
                        </td>
                        <td class="input-table-0lax" colspan="3"></td>
                    </tr>
                    <tr>
                        <td class="input-table-0lax" colspan="3">
                            <label>
                                <fmt:message key="order.edit.form.label.room" />:
                                <span class="text-red">*</span>
                            </label>
                        </td>
                        <td class="input-table-0lax" colspan="3">
                            <select name="room">
                                <option value="">
                                    <fmt:message key="order.edit.form.select.room.appoint" />
                                </option>
                                <c:forEach var="room" items="${rooms}">
                                    <option ${room eq order.room ? 'selected' : ''} 
                                        value="${room.id}" ${(room.seats < order.roomSeats) ? 'disabled': ''}>

                                        <fmt:message key="order.edit.form.select.room.number" />:
                                        ${room.roomNumber},
                                        <fmt:message key="order.edit.form.select.room.type" />:
                                        ${room.roomType.value},
                                        <fmt:message key="order.edit.form.select.room.seats.amount" />:
                                        ${room.seats}
                                    </option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td class="input-table-0lax" colspan="3">
                        <label>
                            <strong><fmt:message key="order.edit.form.label.dates.rent" />:</strong>
                        </label>
                    </td>
                    <td class="input-table-0lax" colspan="3"></td>
                </tr>
                <tr>
                    <td class="input-table-0lax" colspan="3">
                        <label>
                            <fmt:message key="order.edit.form.label.dates.rent.start" />:
                            <span class="text-red">*</span>
                        </label>
                    </td>
                    <td class="input-table-0lax" colspan="3">
                        <input type="date" name="startDate" value="${order.startDate}">
                    </td>
                </tr>
                <tr>
                    <td class="input-table-0lax" colspan="3">
                        <label>
                            <fmt:message key="order.edit.form.label.dates.rent.end" />:
                            <span class="text-red">*</span>
                        </label>
                    </td>
                    <td class="input-table-0lax" colspan="3">
                        <input type="date" name="endDate" value="${order.endDate}">
                    </td>
                </tr>
                <tr>
                    <td class="input-table-0lax" colspan="6">
                        <c:if test="${not empty param.message}">
                            <p class="text-red">
                                <fmt:message key="${param.message}" />
                            </p>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td class="input-table-0lax" colspan="2">
                        <button type="submit">
                            <fmt:message key="order.edit.button.save" />
                        </button>
                    </td>
            </form>
                    <td class="input-table-0lax" colspan="2">
                        <c:if test="${not empty order}">
                            <c:url var="orderDeleteUrl" value="/order/delete.html" />
                            <form action="${orderDeleteUrl}" method="post">
                                <input type="hidden" name="id" value="${order.id}">
                                <button type="submit">
                                    <fmt:message key="order.edit.button.delete" />
                                </button>
                            </form>
                        </c:if>
                    </td>
                    <td class="input-table-0lax" colspan="2">
                        <c:url var="indexUrl" value="/order/list.html" />
                        <form action="${indexUrl}">
                            <button type="submit">
                                <fmt:message key="order.edit.button.back" />
                            </button>
                        </form>
                    </td>
                </tr>
        </tbody>
    </table>


</u:page>