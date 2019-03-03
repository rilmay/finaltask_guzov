<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 04.03.2019
  Time: 0:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.guzov.finaltask.command.CommandType" %>
<%@ page import="by.guzov.finaltask.util.AppConstants" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<div class="container">
    <div class="columns">
        <div class="is-one-third">
            <div class="card">
                <div class="card-content">
                    <div class="content">
                        <p><strong>Reward: </strong><c:out value="${request.request.reward}"/> USD</p>
                        <p><strong>Search period: </strong><c:out value="${request.request.applicationDate}"/> -
                            <c:out value="${request.request.leadDate}"/></p>
                        <p><strong>Status: </strong><c:out value="${request.request.requestStatus}"/></p>
                        <p><strong>Wanted person: </strong><a
                                href="/?${AppConstants.COMMAND}=${CommandType.SHOW_PERSON_DETAILS}&${AppConstants.ID}=${request.wantedPerson.id}">
                            <my:display variable="${request.wantedPerson.firstName}"/>
                            <my:display variable="${request.wantedPerson.lastName}"/></a></p>
                        <p><strong>User login: </strong>
                            <c:if test="${sessionScope.get(AppConstants.SESSION_USER).role == 'admin'}">
                                <a href="/?${AppConstants.COMMAND}=${CommandType.SHOW_USER_DETAILS}&${AppConstants.ID}=${request.user.id}">
                                <c:out value="${request.user.login}"/></a>
                            </c:if>
                            <c:if test="${sessionScope.get(AppConstants.SESSION_USER).role != 'admin'}">
                                <c:out value="${request.user.login}"/>
                            </c:if>
                        </p>
                    </div>
                    <%--dodelat--%>
                    <c:if test="${sessionScope.get(AppConstants.SESSION_USER).role == 'admin'}">
                        <form action="${pageContext.request.contextPath}/" method="post">
                            <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.CHANGE_USER_ROLE}">
                            <input type="hidden" name=${AppConstants.ID} value="${user.id}">
                            <input class="button is-light" type="submit"
                                value="approve">
                        </form>
                    </c:if>
                    <form action="${pageContext.request.contextPath}/" method="post">
                        <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.DELETE_USER}">
                        <input type="hidden" name="${AppConstants.ID}" value="${user.id}">
                        <input class="button is-danger" type="submit" value="delete">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

