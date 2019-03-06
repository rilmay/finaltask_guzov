<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 05.03.2019
  Time: 3:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ page import="by.guzov.finaltask.command.CommandType" %>
<%@ page import="by.guzov.finaltask.util.AppConstants" %>


<nav class="navbar is-light">
    <div class="container">
        <div class="navbar-brand">
            <a class="navbar-item brand-text" href="?${AppConstants.COMMAND}=${CommandType.SHOW_EMPTY_PAGE}">
                <my:lang key="interpol"/>
            </a>
            <div class="navbar-burger burger" data-target="navMenu">
                <span></span>
                <span></span>
                <span></span>
            </div>
        </div>
        <div id="navMenu" class="navbar-menu">
            <div class="navbar-start">
                <a class="navbar-item" href="?${AppConstants.COMMAND}=${CommandType.SHOW_EMPTY_PAGE}">
                    Home
                </a>
                <a class="navbar-item" href="?${AppConstants.COMMAND}=${CommandType.SHOW_WANTED_PEOPLE}">
                    Wanted people
                </a>
                <a class="navbar-item" href="?${AppConstants.COMMAND}=${CommandType.SHOW_REQUEST_LIST}">
                    Requests
                </a>
            </div>

        </div>

        <div class="navbar-menu">
            <div class="navbar-end">
				<span class="navbar-item">
                    <input class="button is-light" type="button" value="<my:lang key="en"/>"
                           onClick="window.location.href=window.location.href+'&change_lang=en'">
                </span>
                <span class="navbar-item">
                    <input class="button is-light" type="button" value="<my:lang key="ru"/>"
                           onClick="window.location.href=window.location.href+'&change_lang=ru'">
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
                <span class="navbar-item">
                <a href="?${AppConstants.COMMAND}=${CommandType.LOG_OUT_USER}">
                    Log out
                </a>
            </span>
            </c:if>
        </div>
    </div>
</nav>
