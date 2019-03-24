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

<div class="column is-11">
    <div class="tile is-ancestor" style="flex-wrap: wrap;">
<c:forEach items="${peopleList}" var="person">
    <div class="tile is-parent is-4">
        <div class="tile is-child box">
            <div class="card-image">
                <figure class="image is-fullwidth">
                <my:image variable="${person.photo}" type="person"/>
                </figure>
            </div>
            <div class="card-content">
                <div class="media">
                    <div class="media-content">
                        <a class="title is-5" href="?${AppConstants.COMMAND}=${CommandType.SHOW_PERSON_DETAILS}&${AppConstants.ID}=${person.id}">
                                <my:display variable="${person.firstName}"/> <my:display variable="${person.lastName}"/>
                                    </a>
                    </div>
                </div>
                <p><i><my:lang key="status.${person.personStatus}"/></i></p>
                <div class="rating" style="color: orange; font-size: 25px;">
                    <c:forTokens items="1,2,3,4,5" delims="," var="star">
                        <c:if test="${star <= person.rating}">
                            ☆
                        </c:if>
                    </c:forTokens>
                </div>
                <br>
                <p><my:display variable="${person.description}"/></p>
            </div>
        </div>
    </div>
</c:forEach>
    </div>
</div>
<my:emptylist size="${peopleList.size()}"/>
<my:pagination/>

