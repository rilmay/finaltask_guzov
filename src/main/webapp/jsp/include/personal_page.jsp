<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 23.02.2019
  Time: 18:01
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
                        <p><strong><i class="fas fa-envelope"></i> </strong><c:out value="${user.email}"/></p>
                        <p><strong>${text['field.registration_date']}: </strong><c:out value="${user.registrationDate}"/></p>
                    </div>
                    <br>
                    <form id="" action="${pageContext.request.contextPath}/" method="post">
                        <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.DELETE_PERSONAL_PAGE}">
                        <button class="button is-block is-info is-normal is-fullwidth" type="button"
                                onclick="ondeleteclick('delete_form')">
                            <i class="fas fa-trash-alt"></i> ${text['button.delete']}
                        </button>
                    </form>
                </div>
        </article>
    </div>
</div>