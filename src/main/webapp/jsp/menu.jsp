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
                <p class="menu-label">${text['title.user']}</p>
                <ul class="menu-list">
                    <li><a href="?${AppConstants.COMMAND}=${CommandType.SHOW_REQUEST_FORM}">${text['links.make_request']}</a></li>
                    <li><a href="?${AppConstants.COMMAND}=${CommandType.SHOW_MY_REQUESTS}">${text['links.my_requests']}</a></li>
                </ul>
            </c:if>
            <c:if test="${sessionScope.get(AppConstants.SESSION_USER).role == AppConstants.ADMIN}">
                <p class="menu-label">${text['title.administration']}</p>
                <ul class="menu-list">
                    <li><a href="?${AppConstants.COMMAND}=${CommandType.SHOW_RECORD_FORM}">${text['links.make_record']}</a>
                    </li>
                    <li><a href="?${AppConstants.COMMAND}=${CommandType.SHOW_USER_LIST}">${text['links.user_list']}</a>
                    </li>
                    <li><a href="?${AppConstants.COMMAND}=${CommandType.SHOW_PENDING_REQUEST_LIST}">
                            ${text['links.pending_requests']}</a></li>
                    <li><a href="?${AppConstants.COMMAND}=${CommandType.SHOW_PENDING_PEOPLE}">
                            ${text['links.pending_people']}</a></li>
                </ul>
            </c:if>
            <p class="menu-label">
                ${text['title.general']}
            </p>
            <ul class="menu-list">
                <li><a>${text['links.about_us']}</a></li>
                <li><a>${text['links.how_apply_request']}</a></li>
            </ul>
        </div>
    </aside>
</div>
