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
<div class="container">
    <div class="content">
        <table class="table is-striped is-hoverable is-narrow is-fullwidth">
            <thead>
            <th>Login</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Email</th>
            </thead>
            <tbody>
            <c:forEach items="${userList}" var="user">
                <tr>
                    <td>
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
