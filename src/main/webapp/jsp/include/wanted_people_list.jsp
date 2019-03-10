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

<c:forEach items="${peopleList}" var="person">
    <div class="column is-8">
<div class="box is-2">
    <article class="media">
        <div class="media-left">
            <figure class="image is-64x64">
                <my:image rounded="rounded" variable="${person.photo}"/>
            </figure>
            <br>
        </div>
        <div class="media-content">
            <div class="content">
                <p>
                    <strong>
                        <a href="?${AppConstants.COMMAND}=${CommandType.SHOW_PERSON_DETAILS}&${AppConstants.ID}=${person.id}">
                                <my:display variable="${person.firstName}"/> <my:display variable="${person.lastName}"/>
                                    </a>
                    </strong><i><my:display variable="${person.personStatus}"/></i></p>
                <p><my:display variable="${person.description}"/></p>
            </div>
        </div>
    </article>
</div>
    </div>
</c:forEach>

