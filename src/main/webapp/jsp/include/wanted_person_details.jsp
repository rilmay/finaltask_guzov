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
<%@ page import="by.guzov.finaltask.util.ServletConst" %>

<div class="container">
    <div class="columns">
        <div class="is-one-third">
            <div class="card">
                <div class="card-content">
                    <div class="media-content">
                        <p class="title is-4"><my:display variable="${person.firstName}"/>
                            <my:display variable="${person.lastName}"/></p>
                    </div>
                    <div class="content">
                        <my:image variable="${person.photo}"/>
                        <p><strong>Status: </strong><c:out value="${person.personStatus}"/></p>
                        <p><strong>Description: </strong><my:display variable="${person.description}"/></p>
                        <p><strong>Born: </strong>
                            <my:display variable="${person.birthPlace}"/>, <my:display variable="${person.birthDate}"/>
                        </p>
                        <p><strong>Special signs: </strong><my:display variable="${person.specialSigns}"/></p>
                        <p><strong>Search area:</strong><my:display variable="${person.searchArea}"/></p>
                    </div>
                    <form action="${pageContext.request.contextPath}/" method="get">
                        <input type="hidden" name="${ServletConst.COMMAND}" value="${CommandType.SHOW_REQUEST_FORM}">
                        <input type="hidden" name="wp_id" value="${person.id}">
                        <input class="button is-light" type="submit" value="make a request">
                    </form>
                        <form action="${pageContext.request.contextPath}/" method="post">
                            <input type="hidden" name="${ServletConst.COMMAND}" value="${CommandType.DELETE_WANTED_PERSON}">
                            <input type="hidden" name="personId" value="${person.id}">
                            <input class="button is-danger" type="submit" value="delete">
                        </form>
                </div>
            </div>
        </div>
    </div>
</div>
