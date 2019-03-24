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

<div class="columns">
    <div class="column is-10">
        <div class="card events-card">
            <div class="card-table">
                <div class="content">
                    <table class="table is-fullwidth is-striped">
                        <thead>
                        <th><i class="fas fa-walking"></i> ${text['title.wanted_person']}</th>
                        <th><i class="fas fa-money-check-alt"></i> ${text['field.reward']}</th>
                        <th><i class="fas fa-calendar-alt"></i> ${text['title.boundary_dates']}</th>
                        <th><i class="far fa-check-square"></i> ${text['field.status']}</th>
                        <th></th>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestList}" var="request_w_p">
                            <tr>
                                <td>
                                    <a href="?${AppConstants.COMMAND}=${CommandType.SHOW_PERSON_DETAILS}&${AppConstants.ID}=${request_w_p.request.wantedPersonId}">
                                        <my:display variable="${request_w_p.personFirstName}"/> <my:display variable="${request_w_p.personLastName}"/>
                                    </a>
                                </td>
                                <td><c:out value="${request_w_p.request.reward}"/></td>
                                <td><c:out value="${request_w_p.request.applicationDate}"/> - <c:out value="${request_w_p.request.leadDate}"/></td>
                                <td>
                                    <c:if test="${request_w_p.request.requestStatus eq 'completed'}">
                                        <a class="button is-small is-success">
                                            <my:lang key="status.${request_w_p.request.requestStatus}"/>
                                        </a>
                                    </c:if>

                                    <c:if test="${request_w_p.request.requestStatus eq 'pending'}">
                                        <a class="button is-small is-warning">
                                            <my:lang key="status.${request_w_p.request.requestStatus}"/>
                                        </a>
                                    </c:if>

                                    <c:if test="${request_w_p.request.requestStatus eq 'approved'}">
                                        <a class="button is-small is-info">
                                            <my:lang key="status.${request_w_p.request.requestStatus}"/>
                                        </a>
                                    </c:if>

                                    <c:if test="${request_w_p.request.requestStatus eq 'expired' or request_w_p.request.requestStatus eq 'cancelled'}">
                                        <a class="button is-small is-primary">
                                            <my:lang key="status.${request_w_p.request.requestStatus}"/>
                                        </a>
                                    </c:if>
                                </td>
                                <td><a class="button is-small is-primary"
                                       href="?${AppConstants.COMMAND}=${CommandType.SHOW_REQUEST_DETAILS}&${AppConstants.ID}=${request_w_p.request.id}"><i class="fas fa-info"></i></a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <my:emptylist size="${requestList.size()}"/>
        <my:pagination/>
    </div>
</div>
