<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 17.02.2019
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.guzov.finaltask.command.CommandType" %>
<%@ page import="by.guzov.finaltask.util.AppConstants" %>

<div class="column is-4 is-centered">
    <div class="box is-2">
        <article class="media">
            <div class="media-content">
                <p class="title is-4"><c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></p>
                <p class="subtitle is-6">@<c:out value="${user.login}"/></p>
                <div class="content">
                    <p><strong>${text['field.role']}: </strong><c:out value="${user.role}"/></p>
                    <p><strong>${text['field.registration_date']}: </strong><c:out value="${user.registrationDate}"/></p>
                    <p><strong>${text['field.email']}: </strong><c:out value="${user.email}"/></p>
                </div>
                <br>
                <form action="${pageContext.request.contextPath}/" method="post">
                    <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.SHOW_REQUESTS_BY_USER}">
                    <input type="hidden" name=${AppConstants.ID} value="${user.id}">
                    <input class="button is-block is-light is-normal is-fullwidth" type="submit"
                           value="${text['links.requests']}">
                </form>
                <form action="${pageContext.request.contextPath}/" method="post">
                    <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.CHANGE_USER_ROLE}">
                    <input type="hidden" name=${AppConstants.ID} value="${user.id}">
                    <input class="button is-block is-success is-normal is-fullwidth" type="submit"
                           value="${text['button.change_role']}">
                </form>
                <form id="delete_form" action="${pageContext.request.contextPath}/" method="post">
                    <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.DELETE_USER}">
                    <input type="hidden" name=${AppConstants.ID} value="${user.id}">
                    <input class="button is-block is-info is-normal is-fullwidth" type="button" value="${text['button.delete']}"
                           onclick="ondeleteclick('delete_form')">
                </form>
            </div>
        </article>
    </div>
</div>
