<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 17.02.2019
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.guzov.finaltask.command.CommandType" %>
<%@ page import="by.guzov.finaltask.util.AppConstants" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<div class="columns">
    <div class="column is-9">
        <div class="card events-card">
            <div class="card-table">
                <div class="content">
                    <table class="table is-fullwidth is-striped">
                        <thead>
                        <th>Login</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th><i class="fas fa-envelope"></i> Email</th>
                        </thead>
                        <tbody>
                        <c:forEach items="${userList}" var="user">
                            <tr>
                                <td width="5%">
                                    <a href="?${AppConstants.COMMAND}=${CommandType.SHOW_USER_DETAILS}&${AppConstants.ID}=${user.id}">
                                        <c:out value="${user.login}"/>
                                    </a>
                                </td>
                                <td><c:out value="${user.firstName}"/></td>
                                <td><c:out value="${user.lastName}"/></td>
                                <td><c:out value="${user.email}"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <my:emptylist size="${userList.size()}"/>
        <my:pagination/>
    </div>
</div>
