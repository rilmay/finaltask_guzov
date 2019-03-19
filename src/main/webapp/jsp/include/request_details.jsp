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

<div class="column is-5 is-centered">
    <div class="box is-2">
        <article class="media">
            <div class="media-content">
                <div class="content">
                    <c:set value="${request.request}" var="current"/>
                    <c:set value="${sessionScope.get(AppConstants.SESSION_USER).role}" var="role"/>
                    <p><strong>${text['title.wanted_person']}: </strong><a
                            href="?${AppConstants.COMMAND}=${CommandType.SHOW_PERSON_DETAILS}&${AppConstants.ID}=${request.request.wantedPersonId}">
                        <my:display variable="${request.personFirstName}"/>
                        <my:display variable="${request.personLastName}"/></a></p>
                    <p><strong>${text['field.reward']}: </strong><c:out value="${current.reward}"/> USD</p>
                    <p><strong>${text['title.boundary_dates']}: </strong><c:out value="${current.applicationDate}"/> -
                        <c:out value="${request.request.leadDate}"/></p>
                    <p><strong>${text['field.status']}: </strong>

                    <c:if test="${current.requestStatus eq 'completed'}">
                        <a class="button is-small is-success">
                            <my:lang key="status.${current.requestStatus}"/>
                        </a>
                    </c:if>

                    <c:if test="${current.requestStatus eq 'pending'}">
                        <a class="button is-small is-warning">
                            <my:lang key="status.${current.requestStatus}"/>
                        </a>
                    </c:if>


                    <c:if test="${current.requestStatus eq 'approved'}">
                        <a class="button is-small is-info">
                            <my:lang key="status.${current.requestStatus}"/>
                        </a>
                    </c:if>

                    <c:if test="${current.requestStatus eq 'expired' or current.requestStatus eq 'cancelled'}">
                        <a class="button is-small is-primary">
                            <my:lang key="status.${current.requestStatus}"/>
                        </a>
                    </c:if>
                    </p>
                    <p><strong>${text['title.user']}: </strong>
                        <c:if test="${role == AppConstants.ADMIN}">
                            <a href="?${AppConstants.COMMAND}=${CommandType.SHOW_USER_DETAILS}&${AppConstants.ID}=${current.userId}">
                                <c:out value="${request.userLogin}"/></a>
                        </c:if>
                        <c:if test="${role != AppConstants.ADMIN}">
                            <c:out value="${request.userLogin}"/>
                        </c:if>
                    </p>
                    <c:if test="${role == AppConstants.ADMIN}">
                        <c:if test="${current.requestStatus == 'pending'}">
                            <form action="${pageContext.request.contextPath}/" method="post">
                                <input type="hidden" name="${AppConstants.COMMAND}"
                                       value="${CommandType.APPROVE_REQUEST}">
                                <input type="hidden" name=${AppConstants.ID} value="${current.id}">
                                <input class="button is-block is-success is-normal is-fullwidth" type="submit"
                                       value="${text['button.approve']}">
                            </form>
                            <form action="${pageContext.request.contextPath}/" method="post">
                                <input type="hidden" name="${AppConstants.COMMAND}"
                                       value="${CommandType.CANCEL_REQUEST}">
                                <input type="hidden" name=${AppConstants.ID} value="${current.id}">
                                <input class="button is-block is-warning is-normal is-fullwidth" type="submit"
                                       value="${text['button.cancel']}">
                            </form>
                        </c:if>
                        <c:if test="${current.requestStatus == 'approved'}">
                            <form action="${pageContext.request.contextPath}/" method="post">
                                <input type="hidden" name="${AppConstants.COMMAND}"
                                       value="${CommandType.SET_COMPLETED_REQUEST}">
                                <input type="hidden" name=${AppConstants.ID} value="${current.id}">
                                <input class="button is-block is-primary is-normal is-fullwidth" type="submit"
                                       value="${text['button.set_completed']}">
                            </form>
                        </c:if>
                    </c:if>
                    <c:if test="${role == AppConstants.ADMIN or sessionScope.get(AppConstants.SESSION_USER).id == current.userId}">
                        <form id="delete_form" action="${pageContext.request.contextPath}/" method="post">
                            <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.DELETE_REQUEST}">
                            <input type="hidden" name="${AppConstants.ID}" value="${current.id}">
                            <input class="button is-block is-info is-normal is-fullwidth" type="button" value="${text['button.delete']}"
                                   onclick="ondeleteclick('delete_form')">
                        </form>
                    </c:if>
                </div>
            </div>
        </article>
    </div>
</div>

