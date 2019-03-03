<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 03.03.2019
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.guzov.finaltask.command.CommandType" %>
<%@ page import="by.guzov.finaltask.util.AppConstants" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<div class="container">
    <div class="content">
        <table class="table is-striped is-hoverable is-narrow is-fullwidth">
            <thead>
            <th></th>
            <th>Wanted person</th>
            <th>Reward</th>
            <th>Search period</th>
            <th>Status</th>
            </thead>
            <tbody>
            <c:forEach items="${requestList}" var="request_w_p">
                <tr>
                    <td><a href="/?${AppConstants.COMMAND}=${CommandType.SHOW_REQUEST_DETAILS}&${AppConstants.ID}=${request_w_p.request.id}"/>full info</td>
                    <td>
                        <a href="?${AppConstants.COMMAND}=${CommandType.SHOW_PERSON_DETAILS}&${AppConstants.ID}=${request_w_p.wantedPerson.id}">
                            <my:display variable="${request_w_p.wantedPerson.firstName}"/> <my:display variable="${request_w_p.wantedPerson.lastName}"/>
                        </a>
                    </td>
                    <td><c:out value="${request_w_p.request.reward}"/></td>
                    <td><c:out value="${request_w_p.request.applicationDate}"/> - <c:out value="${request_w_p.request.leadDate}"/></td>
                    <td><c:out value="${request_w_p.request.requestStatus}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
