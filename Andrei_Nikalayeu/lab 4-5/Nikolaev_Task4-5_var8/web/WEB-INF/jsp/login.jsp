<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="language"
    value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
    scope="session" />

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="property.messages" />

<fmt:message var="title" key="login.title" />

<u:page title="${title}">
    <table class="input-table" align="center">
        <tbody>
            <form>
                <tr>
                    <td class="input-table-0lax" colspan="3">
                        <label>
                            <fmt:message key="login.form.label.language" />:
                        </label>
                    </td>
                    <td class="input-table-0lax" colspan="3">
                        <select name="language" onchange="submit()">
                            <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
                            <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                        </select>
                    </td>
                </tr>
            </form>
            <c:url var="loginUrl" value="/login.html" />
            <form action="${loginUrl}" method="post">
                <tr>
                    <td class="input-table-0lax" colspan="3">
                        <label>
                            <fmt:message key="login.form.label.login" />:
                        </label>
                    </td>
                    <td class="input-table-0lax" colspan="3">
                        <input type="text" name="login">
                    </td>
                </tr>
                <tr>
                    <td class="input-table-0lax" colspan="3">
                        <label>
                            <fmt:message key="login.form.label.password" />:
                        </label>
                    </td>
                    <td class="input-table-0lax" colspan="3">
                        <input type="password" name="password">
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
                    <td class="input-table-0lax" colspan="6">
                        <button type="submit">
                            <fmt:message key="login.form.button.login" />
                        </button>
                    </td>
                </tr>
            </form>
        </tbody>
    </table>
</u:page>