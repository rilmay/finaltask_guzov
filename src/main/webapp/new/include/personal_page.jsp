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
                        <p><strong>Registration Date: </strong><c:out value="${user.registrationDate}"/></p>
                        <p><strong>E-mail: </strong><c:out value="${user.email}"/></p>
                    </div>
                    <br>
                    <form action="${pageContext.request.contextPath}/" method="post">
                        <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.DELETE_PERSONAL_PAGE}">
                        <input class="button is-block is-info is-normal is-fullwidth" type="submit" value="delete">
                    </form>
                </div>
        </article>
    </div>
</div>