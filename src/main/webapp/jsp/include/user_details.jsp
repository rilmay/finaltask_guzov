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
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<div class="column is-4 is-centered">
    <div class="box is-2">
        <article class="media">
            <div class="media-content">
                <p class="title is-4"><c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></p>
                <p class="subtitle is-6">@<c:out value="${user.login}"/></p>
                <div class="content">
                    <p><strong><i class="fas fa-envelope"></i> </strong><c:out value="${user.email}"/></p>
                    <p><strong>${text['field.registration_date']}: </strong><c:out value="${user.registrationDate}"/></p>
                    <p><strong>${text['field.role']}: </strong><my:lang key="role.${user.role}"/></p>
                </div>
                <br>
                <form action="${pageContext.request.contextPath}/" method="get">
                    <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.SHOW_REQUESTS_BY_USER}">
                    <input type="hidden" name=${AppConstants.ID} value="${user.id}">
                    <input class="button is-block is-light is-normal is-fullwidth" type="submit"
                           value="${text['links.requests']}">
                </form>
                <form action="${pageContext.request.contextPath}/" method="post">
                    <input type="hidden" name=${AppConstants.ID} value="${user.id}">
                    <my:loadingbutton label="${text['button.change_role']}" command="${CommandType.CHANGE_USER_ROLE}" type="is-success"/>
                </form>
                <form id="delete_form" action="${pageContext.request.contextPath}/" method="post">
                    <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.DELETE_USER}">
                    <input type="hidden" name=${AppConstants.ID} value="${user.id}">
                    <button class="button is-block is-info is-normal is-fullwidth" type="button"
                            onclick="ondeleteclick('delete_form')">
                        <i class="fas fa-trash-alt"></i> ${text['button.delete']}
                    </button>
                </form>
            </div>
        </article>
    </div>
</div>
