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

<div class="container">
    <div class="columns">
        <div class="is-one-third">
            <div class="card">
                <div class="card-content">
                    <div class="media-content">
                        <p class="title is-4"><c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></p>
                        <p class="subtitle is-6">@<c:out value="${user.login}"/></p>
                    </div>
                    <div class="content">
                        <p><strong>Role: </strong><c:out value="${user.role}"/></p>
                        <p><strong>Registration Date: </strong><c:out value="${user.registrationDate}"/></p>
                        <p><strong>E-mail: </strong><c:out value="${user.email}"/></p>
                    </div>
                    <form action="${pageContext.request.contextPath}/" method="post">
                        <input type="hidden" name="command" value="${CommandType.LOG_OUT_USER}">
                        <input class="button is-light" type="submit" value="Log out">
                    </form>
                    <form action="${pageContext.request.contextPath}/" method="post">
                        <input type="hidden" name="command" value="${CommandType.DELETE_PERSONAL_PAGE}">
                        <input type="hidden" name="userId" value="${user.id}">
                        <input class="button is-danger" type="submit" value="delete">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>