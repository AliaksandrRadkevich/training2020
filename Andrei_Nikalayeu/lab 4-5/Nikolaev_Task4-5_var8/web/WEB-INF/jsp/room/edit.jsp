<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="property.messages" />

<fmt:message var="titleEdit" key="room.edit.title.edit" />
<fmt:message var="titleNew" key="room.edit.title.new" />

<c:choose>
    <c:when test="${not empty room}">
        <c:set var="title"
            value="${titleEdit} &#8470;${room.roomNumber}" />
    </c:when>
    <c:otherwise>
        <c:set var="title" value="${titleNew}" />
    </c:otherwise>
</c:choose>

<u:page title="${title}">
    <c:url var="roomSaveUrl" value="/room/save.html" />

    <table class="input-table" align="center">
        <tbody>
            <form action="${roomSaveUrl}" method="post">
                <c:if test="${not empty room}">
                    <input type="hidden" name="id" value="${room.id}">
                </c:if>
                <tr>
                    <td class="input-table-0lax" colspan="3">
                        <label>
                            <fmt:message key="room.edit.form.label.number" />:
                            <span class="text-red">*</span>
                        </label>
                    </td>
                    <td class="input-table-0lax" colspan="3">
                        <input type="text" name="number" value="${room.roomNumber}">
                    </td>
                </tr>
                <tr>
                    <td class="input-table-0lax" colspan="3">
                        <label>
                            <fmt:message key="room.edit.form.label.seats" />:
                            <span class="text-red">*</span>
                        </label>
                    </td>
                    <td class="input-table-0lax" colspan="3">
                        <input type="number" name="seats" min="1" value="${room.seats}">
                    </td>
                </tr>
                <tr>
                    <td class="input-table-0lax" colspan="3">
                        <label>
                            <fmt:message key="room.edit.form.label.price" />:
                            <span class="text-red">*</span>
                        </label>
                    </td>
                    <td class="input-table-0lax" colspan="3">
                        <input type="text" name="price" pattern="\d+(\.\d{2})?" value="${room.price}">
                    </td>
                </tr>
                <tr>
                    <td class="input-table-0lax" colspan="3">
                        <label>
                            <fmt:message key="room.edit.form.label.type" />:
                                <span class="text-red">*</span>
                        </label>
                    </td>
                    <td class="input-table-0lax" colspan="3">
                        <select name="roomType">
                            <c:forEach var="type" items="${roomTypes}">
                                <option ${type.value eq room.roomType.value ? 'selected' : ''} value="${type.id}">
                                    ${type.value}
                                </option>
                            </c:forEach>
                        </select>
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
                            <fmt:message key="room.edit.button.save" />
                        </button>
                    </td>
            </form>
                    <td class="input-table-0lax" colspan="2">
                        <c:if test="${not empty room}">
                            <c:url var="roomDeleteUrl" value="/room/delete.html" />
                            <form action="${roomDeleteUrl}" method="post">
                                <input type="hidden" name="id" value="${room.id}">
                                <button type="submit">
                                    <fmt:message key="room.edit.button.delete" />
                                </button>
                            </form>
                        </c:if>
                    </td>
                    <td class="input-table-0lax" colspan="2">
                        <c:url var="indexUrl" value="/room/list.html" />
                        <form action="${indexUrl}">
                            <button type="submit">
                                <fmt:message key="room.edit.button.back" />
                            </button>
                        </form>
                    </td>
                </tr>
        </tbody>
    </table>
</u:page>