<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 17.02.2019
  Time: 2:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.guzov.finaltask.command.CommandType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.guzov.finaltask.util.AppConstants" %>
<aside class="menu">
    <p class="menu-label">
        Menu
    </p>
    <ul class="menu-list">
        <li><a href="?${AppConstants.COMMAND}=${CommandType.SHOW_WANTED_PEOPLE}">Wanted People</a></li>
        <li><a href="?${AppConstants.COMMAND}=${CommandType.SHOW_REQUEST_LIST}">Request list</a> </li>
        <c:if test="${sessionScope.get(AppConstants.SESSION_USER) == null}">
            <li><a href="?${AppConstants.COMMAND}=${CommandType.SHOW_REGISTRATION_PAGE}">Registration</a></li>
            <li><a href="?${AppConstants.COMMAND}=${CommandType.SHOW_AUTHENTICATION_PAGE}">Log In</a></li>
        </c:if>
        <c:if test="${sessionScope.get(AppConstants.SESSION_USER) != null}">
            <li><a href="?${AppConstants.COMMAND}=${CommandType.SHOW_USER_LIST}">User List</a></li>
            <li><a href="?${AppConstants.COMMAND}=${CommandType.SHOW_PERSONAL_PAGE}">Personal page</a> </li>
        </c:if>
    </ul>
</aside>
