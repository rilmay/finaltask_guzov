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

<div class="column is-8 is-centered">
    <div class="box is-2">
        <article class="media">
            <div class="media-left">
                <figure class="image is-70x70">
                    <my:image variable="${person.photo}"/>
                </figure>
            </div>
            <div class="media-content">
                <div class="content">
                    <p><strong><my:display variable="${person.firstName}"/> <my:display variable="${person.lastName}"/>
                        </strong><i><my:display variable="${person.personStatus}"/></i></p>
                    <c:if test="${person.pending}">
                        <p class="button is-small is-warning">pending</p>
                    </c:if>
                    <p><strong>Description: </strong><my:display variable="${person.description}"/></p>
                    <p><strong>Born: </strong>
                        <my:display variable="${person.birthPlace}"/>, <my:display variable="${person.birthDate}"/>
                    </p>
                    <p><strong>Special signs: </strong><my:display variable="${person.specialSigns}"/></p>
                    <p><strong>Search area:</strong><my:display variable="${person.searchArea}"/></p>
                    <form action="${pageContext.request.contextPath}/" method="get">
                        <input type="hidden" name="${AppConstants.COMMAND}"
                               value="${CommandType.SHOW_REQUESTS_BY_WANTED_PERSON}">
                        <input type="hidden" name="${AppConstants.ID}" value="${person.id}">
                        <input class="button is-block is-light is-normal is-fullwidth" type="submit"
                               value="available requests">
                    </form>
                    <c:if test="${not empty sessionScope.get(AppConstants.SESSION_USER) and not person.pending
                    and person.personStatus eq 'missing' and person.personStatus eq 'wanted'}">
                        <form action="${pageContext.request.contextPath}/" method="get">
                            <input type="hidden" name="${AppConstants.COMMAND}"
                                   value="${CommandType.SHOW_REQUEST_FORM}">
                            <input type="hidden" name="${AppConstants.ID}" value="${person.id}">
                            <input class="button is-block is-success is-normal is-fullwidth" type="submit"
                                   value="make a request">
                        </form>
                    </c:if>
                    <c:if test="${sessionScope.get(AppConstants.SESSION_USER).role eq AppConstants.ADMIN}">
                        <form action="${pageContext.request.contextPath}/" method="post">
                            <input type="hidden" name="${AppConstants.COMMAND}"
                                   value="${CommandType.DELETE_WANTED_PERSON}">
                            <input type="hidden" name="${AppConstants.ID}" value="${person.id}">
                            <input class="button is-block is-info is-normal is-fullwidth" type="submit" value="delete">
                        </form>
                    </c:if>
                </div>
            </div>
        </article>
    </div>
</div>