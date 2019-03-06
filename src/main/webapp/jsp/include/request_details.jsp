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
                        <c:set value="${request.request}" var="current"/>
                        <c:set value="${sessionScope.get(AppConstants.SESSION_USER).role}" var="role"/>
                        <p><strong>Reward: </strong><c:out value="${current.reward}"/> USD</p>
                        <p><strong>Search period: </strong><c:out value="${current.applicationDate}"/> -
                            <c:out value="${request.request.leadDate}"/></p>
                        <p><strong>Status: </strong><c:out value="${current.requestStatus}"/></p>
                        <p><strong>Wanted person: </strong><a
                                href="/?${AppConstants.COMMAND}=${CommandType.SHOW_PERSON_DETAILS}&${AppConstants.ID}=${request.request.wantedPersonId}">
                            <my:display variable="${request.personFirstName}"/>
                            <my:display variable="${request.personLastName}"/></a></p>
                        <p><strong>User login: </strong>
                            <c:if test="${role == AppConstants.ADMIN}">
                                <a href="/?${AppConstants.COMMAND}=${CommandType.SHOW_USER_DETAILS}&${AppConstants.ID}=${request.request.userId}">
                                <c:out value="${request.userLogin}"/></a>
                            </c:if>
                            <c:if test="${role != AppConstants.ADMIN}">
                                <c:out value="${request.userLogin}"/>
                            </c:if>
                        </p>
                    </div>
                    <c:if test="${role == AppConstants.ADMIN}">
                        <c:if test="${current.requestStatus == 'pending'}">
                        <form action="${pageContext.request.contextPath}/" method="post">
                            <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.APPROVE_REQUEST}">
                            <input type="hidden" name=${AppConstants.ID} value="${current.id}">
                            <input class="button is-light" type="submit" value="approve">
                            </form>
                            <form action="${pageContext.request.contextPath}/" method="post">
                                <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.CANCEL_REQUEST}">
                                <input type="hidden" name=${AppConstants.ID} value="${current.id}">
                                <input class="button is-light" type="submit" value="cancel">
                            </form>
                        </c:if>
                        <c:if test="${current.requestStatus == 'approved'}">
                            <form action="${pageContext.request.contextPath}/" method="post">
                                <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.SET_COMPLETED_REQUEST}">
                                <input type="hidden" name=${AppConstants.ID} value="${current.id}">
                                <input class="button is-light" type="submit" value="set completed">
                            </form>
                        </c:if>
                        <form action="${pageContext.request.contextPath}/" method="post">
                            <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.DELETE_REQUEST}">
                            <input type="hidden" name="${AppConstants.ID}" value="${current.id}">
                            <input class="button is-danger" type="submit" value="delete">
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
