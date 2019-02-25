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
<%@ page import="by.guzov.finaltask.util.ServletConst" %>

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
                            <input type="hidden" name="${ServletConst.COMMAND}" value="${CommandType.CHANGE_USER_ROLE}">
                            <input type="hidden" name="userId" value="${user.id}">
                            <c:if test="${user.role == 'admin'}">
                            <input class="button is-light" type="submit" value="change role to user">
                            </c:if>
                            <c:if test="${user.role == 'user'}">
                                <input class="button is-light" type="submit" value="change role to admin">
                            </c:if>
                        </form>
                    <form action="${pageContext.request.contextPath}/" method="post">
                        <input type="hidden" name="${ServletConst.COMMAND}" value="${CommandType.DELETE_USER}">
                        <input type="hidden" name="userId" value="${user.id}">
                        <input class="button is-danger" type="submit" value="delete">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
