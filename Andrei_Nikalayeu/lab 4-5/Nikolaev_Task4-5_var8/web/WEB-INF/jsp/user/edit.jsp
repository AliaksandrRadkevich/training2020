<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="property.messages" />

<fmt:message var="titleEdit" key="user.edit.title.edit" />
<fmt:message var="titleNew" key="user.edit.title.new" />
<c:choose>
    <c:when test="${not empty user}">
        <c:set var="title" value="${titleEdit} &#34;${user.login}&#34;" />
    </c:when>
    <c:otherwise>
        <c:set var="title" value="${titleNew}" />
    </c:otherwise>
</c:choose>

<u:page title="${title}">
    <c:url var="userSaveUrl" value="/user/save.html" />

    <table class="input-table" align="center">
        <tbody>
            <form action="${userSaveUrl}" method="post">
                <c:if test="${not empty user}">
                    <input type="hidden" name="id" value="${user.id}">
                </c:if>
                <tr>
                    <td class="input-table-0lax" colspan="3">
                        <label>
                            <fmt:message key="user.edit.form.label.name" />:
                            <span class="text-red">*</span>
                        </label>
                    </td>
                    <td class="input-table-0lax" colspan="3">
                        <input type="text" name="name" value="${user.name}">
                    </td>
                </tr>
                <tr>
                    <td class="input-table-0lax" colspan="3">
                        <label>
                            <fmt:message key="user.edit.form.label.surname" />:
                            <span class="text-red">*</span>
                        </label>
                    </td>
                    <td class="input-table-0lax" colspan="3">
                        <input type="text" name="surname" value="${user.surname}">
                    </td>
                </tr>
                <tr>
                    <td class="input-table-0lax" colspan="3">
                        <label>
                            <fmt:message key="user.edit.form.label.mail" />:
                        </label>
                    </td>
                    <td class="input-table-0lax" colspan="3">
                        <input type="text" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" 
                            value="${user.email}">
                    </td>
                </tr>
                <tr>
                    <td class="input-table-0lax" colspan="3">
                        </label>
                            <fmt:message key="user.edit.form.label.login" />:
                            <span class="text-red">*</span>
                        </label>
                    </td>
                    <td class="input-table-0lax" colspan="3">
                        <input type="text" name="login" value="${user.login}">
                    </td>
                </tr>
                <tr>
                    <td class="input-table-0lax" colspan="3">
                        <label>
                            <fmt:message key="user.edit.form.label.password" />:
                            <span class="text-red">*</span>
                        </label>
                    </td>
                    <td class="input-table-0lax" colspan="3">
                        <input type="text" name="password" value="${user.password}">
                    </td>
                </tr>
                <tr>
                    <td class="input-table-0lax" colspan="3">
                        <label>
                            <fmt:message key="user.edit.form.label.role" />:
                            <span class="text-red">*</span>
                        </label>
                    </td>
                    <td class="input-table-0lax" colspan="3">
                        <select name="role">
                            <c:forEach var="role" items="${roles}">
                                <option ${role.value eq user.role.value ? 'selected' : ''} value="${role.id}">
                                    ${role.value}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="input-table-0lax" colspan="6">
                        <c:if test="${not empty param.message}">
                            <p class="text-red"><fmt:message key="${param.message}" /></p>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td class="input-table-0lax" colspan="2">
                        <button type="submit">
                            <fmt:message key="user.edit.button.save" />
                        </button>
                    </td>
            </form>
                    <td class="input-table-0lax" colspan="2">
                        <c:if test="${not empty user}">
                            <c:url var="userDeleteUrl" value="/user/delete.html" />

                            <form action="${userDeleteUrl}" method="post">
                                <input type="hidden" name="id" value="${user.id}">
                                <button type="submit">
                                    <fmt:message key="user.edit.button.delete" />
                                </button>
                            </form>
                        </c:if>
                    </td>
                    <td class="input-table-0lax" colspan="2">
                        <c:url var="indexUrl" value="/user/list.html" />

                        <form action="${indexUrl}">
                            <button type="submit">
                                <fmt:message key="user.edit.button.back" />
                            </button>
                        </form>
                    </td>
                </tr>
        </tbody>
    </table>
</u:page>