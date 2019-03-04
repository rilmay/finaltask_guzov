<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 03.03.2019
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ page import="by.guzov.finaltask.command.CommandType" %>
<%@ page import="by.guzov.finaltask.util.AppConstants" %>
<nav class="navbar">
    <div class="navbar-menu">
        <div class="navbar-end">
            <span class="navbar-item">
                <a class="button is-light is-inverted" href="?change_lang=en">
                    <span><my:lang key="en"/></span>
                </a>
            </span>
            <span class="navbar-item">
                <a class="button is-light is-inverted" href="?change_lang=ru">
                    <span><my:lang key="ru"/></span>
                </a>
            </span>
        </div>
    </div>
    <div class="navbar-tabs">
        <c:if test="${empty sessionScope.get(AppConstants.SESSION_USER)}">
            <span class="navbar-item">
                <a href="?${AppConstants.COMMAND}=${CommandType.SHOW_REGISTRATION_PAGE}">Registration</a>
            </span>
            <span class="navbar-item">
                <a href="?${AppConstants.COMMAND}=${CommandType.SHOW_AUTHENTICATION_PAGE}">Log In</a>
            </span>
        </c:if>
        <c:if test="${not empty sessionScope.get(AppConstants.SESSION_USER)}">
            <span class="navbar-item">
                <a href="?${AppConstants.COMMAND}=${CommandType.SHOW_PERSONAL_PAGE}">
                    <c:out value="${sessionScope.get(AppConstants.SESSION_USER).login}"/>
                </a>
            </span>
        </c:if>
    </div>
</nav>
