<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 17.03.2019
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.guzov.finaltask.command.CommandType" %>
<%@ page import="by.guzov.finaltask.util.AppConstants" %>

<div class="column is-6">
    <div class="card">
        <div class="card-image">
            <figure class="image is-fullwidth">
                <my:image variable="${record.photo}"/>
            </figure>
            <c:if test="${record.recordStatus eq 'expired'}">
                <p class="button is-fullwidth is-small is-warning">${text['status.expired']}</p>
            </c:if>
        </div>
        <div class="card-content">
            <div class="content">
                <h1>${record.name}</h1>
                <div class="rating" style="color: orange; width: 150px; font-size: 30px; height: 40px;">
                    <c:forTokens items="1,2,3,4,5" delims="," var="star">
                        <c:if test="${star <= record.rating}">
                            ☆
                        </c:if>
                    </c:forTokens>
                </div>
                <br>
                <p><strong>${text['field.place']}: </strong>${record.place}</p>
                <p><strong>${text['field.date']}: </strong>${record.date}</p>
                <p><i>${record.description}</i></p>
                <c:if test="${sessionScope.get(AppConstants.SESSION_USER).role eq AppConstants.ADMIN}">
                    <c:if test="${record.recordStatus eq 'relevant'}">
                    <form action="${pageContext.request.contextPath}/" method="post">
                        <input type="hidden" name="${AppConstants.COMMAND}"
                               value="${CommandType.SET_EXPIRED_RECORD}">
                        <input type="hidden" name="${AppConstants.ID}" value="${record.id}">
                        <input class="button is-block is-success is-normal is-fullwidth" type="submit"
                               value="${text['button.set_expired']}">
                    </form>
                    </c:if>
                    <form id="delete_form" action="${pageContext.request.contextPath}/" method="post">
                        <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.DELETE_RECORD}">
                        <input type="hidden" name="${AppConstants.ID}" value="${record.id}">
                        <input class="button is-block is-info is-normal is-fullwidth" type="button" value="${text['button.delete']}"
                               onclick="ondeleteclick('delete_form')">
                    </form>
                </c:if>
            </div>
        </div>
    </div>
</div>

