<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 05.03.2019
  Time: 3:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.guzov.finaltask.command.CommandType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.guzov.finaltask.util.AppConstants" %>

<div class="column is-3 ">
    <aside class="menu is-hidden-mobile">
        <c:if test="${not empty sessionScope.get(AppConstants.SESSION_USER).role}">
            <p class="menu-label">User</p>
            <ul class="menu-list">
                <li><a href="?${AppConstants.COMMAND}=${CommandType.SHOW_REQUEST_FORM}">Make a request</a></li>
                <li><a href="?${AppConstants.COMMAND}=${CommandType.SHOW_MY_REQUESTS}">My requests</a></li>
            </ul>
        </c:if>
        <c:if test="${sessionScope.get(AppConstants.SESSION_USER).role == AppConstants.ADMIN}">
            <p class="menu-label">Administration</p>
            <ul class="menu-list">
                <li><a href="?${AppConstants.COMMAND}=${CommandType.SHOW_USER_LIST}">User List</a></li>
                <li><a href="?${AppConstants.COMMAND}=${CommandType.SHOW_PENDING_REQUEST_LIST}">Pending requests</a></li>
                <li><a href="?${AppConstants.COMMAND}=${CommandType.SHOW_PENDING_PEOPLE}">Pending people</a> </li>
            </ul>
        </c:if>
        <p class="menu-label">
            General
        </p>
        <ul class="menu-list">
            <li><a>About us</a></li>
            <li><a>How to apply request</a></li>
        </ul>
    </aside>
</div>
