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
<aside class="menu">
    <p class="menu-label">
        Menu
    </p>
    <ul class="menu-list">
        <li><a href="?command=${CommandType.SHOW_USER_LIST}">User List</a></li>
        <c:if test="${sessionScope.get('authorized') == null}">
            <li><a href="?command=${CommandType.SHOW_REGISTRATION_PAGE}">Registration(temporary)</a></li>
            <li><a href="?command=${CommandType.SHOW_AUTHENTICATION_PAGE}">Log In</a></li>
        </c:if>
        <c:if test="${sessionScope.get('authorized') != null}">
            <li><a href="?command=${CommandType.LOG_OUT_USER}">Log out</a></li>
        </c:if>
    </ul>
</aside>
