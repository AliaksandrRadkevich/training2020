<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ attribute name="title" required="false" rtexprvalue="true" type="java.lang.String"%>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="property.messages" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>
        <fmt:message key="top.menu.title"/>
        <c:if test="${not empty title}"> :: ${title}</c:if>
    </title>

    <c:url var="urlCss" value="/style.css"/>
    <link href="${urlCss}" rel="stylesheet">
</head>
<body>
    <header>
        <nav>
            <ul class="topmenu">
                <c:choose>
                    <c:when test="${not empty session_user}">
                        <c:url var="urlLogin" value="/login.html"/>
                        <c:url var="urlLogout" value="/logout.html"/>
                        <c:url var="usersUrl" value="/user/list.html"/>
                        <c:url var="roomsUrl" value="/room/list.html"/>
                        <c:url var="ordersUrl" value="/order/list.html"/>
                        <c:url var="orderEditUrl" value="/order/edit.html"/>
                        <c:url var="urlInfo" value="/info.html"/>

                        <li>
                            <a href="" class="session_user">${session_user.name} ${session_user.surname}</a>
                            <ul class="submenu">
                                <li>
                                    <p>
                                        <fmt:message key="top.menu.session.user.role"/>: ${session_user.role.value}
                                    </p>
                                </li>
                                <li>
                                    <p>
                                        <fmt:message key="top.menu.session.user.mail"/>: ${session_user.email}
                                    </p>
                                </li>
                                <li>
                                    <a href="${urlLogout}">
                                        <fmt:message key="top.menu.session.user.logout"/>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        
                        <c:choose>
                            <c:when test="${adminDataAccess}">
                                <li>
                                    <a class="${userListItem ? 'active' : ''}" href="${usersUrl}">
                                        <fmt:message key="top.menu.item.users"/>
                                    </a>
                                </li>
                                <li>
                                    <a class="${roomListItem ? 'active' : ''}" href="${roomsUrl}">
                                        <fmt:message key="top.menu.item.rooms"/>
                                    </a>
                                </li>
                                <li>
                                    <a class="${orderListItem ? 'active' : ''}" href="${ordersUrl}">
                                        <fmt:message key="top.menu.item.orders"/>
                                    </a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a class="${orderEditItem ? 'active' : ''}" href="${orderEditUrl}">
                                        <fmt:message key="top.menu.item.order.create"/>
                                    </a>
                                </li>
                                <li>
                                    <a class="${orderListItem ? 'active' : ''}" href="${ordersUrl}">
                                        <fmt:message key="top.menu.item.order.my"/>
                                    </a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                        
                        <li>
                            <a class="${info ? 'active' : ''}" href="${urlInfo}">
                                <fmt:message key="top.menu.item.info"/>
                            </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a class="${login ? 'active' : ''}" href="${urlLogin}">
                                <fmt:message key="top.menu.item.login"/>
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose> 
            </ul>
        </nav>
        
        <c:if test="${not empty title}">
            <h1 class="text-center">${title}</h1>
        </c:if>
    </header>

    <div class="container">
        <jsp:doBody/>
    </div>
</body>
</html>