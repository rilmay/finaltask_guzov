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
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<div class="column is-3">
    <aside class="menu is-hidden-mobile">
        <div id="menu">
            <c:if test="${not empty sessionScope.get(AppConstants.SESSION_USER).role}">
                <p class="menu-label"><my:lang key="user"/></p>
                <ul class="menu-list">
                    <li><a href="?${AppConstants.COMMAND}=${CommandType.SHOW_REQUEST_FORM}"><my:lang
                            key="make_request"/></a></li>
                    <li><a href="?${AppConstants.COMMAND}=${CommandType.SHOW_MY_REQUESTS}"><my:lang
                            key="my_requests"/></a></li>
                </ul>
            </c:if>
            <c:if test="${sessionScope.get(AppConstants.SESSION_USER).role == AppConstants.ADMIN}">
                <p class="menu-label"><my:lang key="administration"/></p>
                <ul class="menu-list">
                    <li><a href="?${AppConstants.COMMAND}=${CommandType.SHOW_USER_LIST}"><my:lang key="user_list"/></a>
                    </li>
                    <li><a href="?${AppConstants.COMMAND}=${CommandType.SHOW_PENDING_REQUEST_LIST}">
                        <my:lang key="pending_requests"/></a></li>
                    <li><a href="?${AppConstants.COMMAND}=${CommandType.SHOW_PENDING_PEOPLE}">
                        <my:lang key="pending_people"/></a></li>
                </ul>
            </c:if>
            <p class="menu-label">
                <my:lang key="general"/>
            </p>
            <ul class="menu-list">
                <li><a><my:lang key="about_us"/></a></li>
                <li><a><my:lang key="How to apply request"/></a></li>
            </ul>
        </div>
    </aside>
</div>
