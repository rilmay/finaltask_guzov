<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 24.02.2019
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.guzov.finaltask.command.CommandType" %>
<%@ page import="by.guzov.finaltask.util.AppConstants" %>

<div class="container">
    <div class="content">
        <table class="table is-striped is-hoverable is-narrow is-fullwidth">
            <thead>
            <th>Photo</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Status</th>
            <th></th>
            </thead>
            <tbody>
            <c:forEach items="${peopleList}" var="person">
                <tr>
                    <td><my:image variable="${person.photo}"/></td>
                    <td><my:display variable="${person.firstName}"/></td>
                    <td><my:display variable="${person.lastName}"/></td>
                    <td><my:display variable="${person.personStatus}"/></td>
                    <td>
                        <a href="?${AppConstants.COMMAND}=${CommandType.SHOW_PERSON_DETAILS}&${AppConstants.ID}=${person.id}">
                            more...
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
