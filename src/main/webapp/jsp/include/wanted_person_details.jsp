<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 24.02.2019
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ page import="by.guzov.finaltask.command.CommandType" %>
<%@ page import="by.guzov.finaltask.util.AppConstants" %>

<div class="column is-7 is-centered">
    <div class="box is-2">
        <article class="media">
            <div class="media-left">
                <figure class="image is-128x128">
                    <my:image variable="${person.photo}" type="person"/>
                </figure>
            </div>
            <div class="media-content">
                <div class="content">
                    <strong class="title is-5"><my:display variable="${person.firstName}"/> <my:display variable="${person.lastName}"/>
                    </strong>
                    <div class="rating" style="color: orange; font-size: 25px;">
                        <c:forTokens items="1,2,3,4,5" delims="," var="star">
                            <c:if test="${star <= person.rating}">
                                ☆
                            </c:if>
                        </c:forTokens>
                    </div>
                    <c:if test="${person.pending}">
                        <p class="button is-small is-warning">${text['status.pending']}</p>
                    </c:if>
                    <p><strong>${text['field.status']}: </strong><i class="has-text-primary"><my:lang key="status.${person.personStatus}"/></i></p>
                    <p><strong>${text['title.born']}: </strong>
                    <my:display variable="${person.birthPlace}"/>, <my:display variable="${person.birthDate}"/>
                    </p>
                    <p><strong>${text['field.special_signs']}: </strong><my:display variable="${person.specialSigns}"/></p>
                    <p><strong>${text['field.search_area']}: </strong><my:display variable="${person.searchArea}"/></p>

                    <p><strong>${text['field.description']}: </strong><my:display variable="${person.description}"/></p>
                    <form action="${pageContext.request.contextPath}/" method="get">
                        <input type="hidden" name="${AppConstants.COMMAND}"
                               value="${CommandType.SHOW_REQUESTS_BY_WANTED_PERSON}">
                        <input type="hidden" name="${AppConstants.ID}" value="${person.id}">
                        <input class="button is-block is-light is-normal is-fullwidth" type="submit"
                               value="${text['links.requests']}">
                    </form>
                    <c:if test="${not empty sessionScope.get(AppConstants.SESSION_USER) and not person.pending}">
                        <c:if test="${person.personStatus eq 'missing' or person.personStatus eq 'wanted'}">
                            <form action="${pageContext.request.contextPath}/" method="get">
                                <input type="hidden" name="${AppConstants.COMMAND}"
                                   value="${CommandType.SHOW_REQUEST_FORM}">
                                <input type="hidden" name="${AppConstants.ID}" value="${person.id}">
                                <input class="button is-block is-success is-normal is-fullwidth" type="submit"
                                    value="${text['links.make_request']}">
                            </form>
                        </c:if>
                    </c:if>
                    <c:if test="${sessionScope.get(AppConstants.SESSION_USER).role eq AppConstants.ADMIN and empty person.photo}">
                        <form action="${pageContext.request.contextPath}/" method="get">
                            <input type="hidden" name="${AppConstants.COMMAND}"
                                   value="${CommandType.SHOW_UPLOAD_PHOTO_FORM}">
                            <input type="hidden" name="${AppConstants.ID}" value="${person.id}">
                            <input class="button is-block is-success is-normal is-fullwidth" type="submit" value="${text['button.upload_photo']}">
                        </form>
                    </c:if>
                    <c:if test="${sessionScope.get(AppConstants.SESSION_USER).role eq AppConstants.ADMIN}">
                        <form id="delete_form" action="${pageContext.request.contextPath}/" method="post">
                            <input type="hidden" name="${AppConstants.COMMAND}"
                                   value="${CommandType.DELETE_WANTED_PERSON}">
                            <input type="hidden" name="${AppConstants.ID}" value="${person.id}">
                            <button class="button is-block is-info is-normal is-fullwidth" type="button"
                                    onclick="ondeleteclick('delete_form')">
                                <i class="fas fa-trash-alt"></i> ${text['button.delete']}
                            </button>
                        </form>
                    </c:if>
                </div>
            </div>
            <br>
        </article>
    </div>
</div>