<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 11.03.2019
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.guzov.finaltask.command.CommandType" %>
<%@ page import="by.guzov.finaltask.util.AppConstants" %>

<div class="column is-11">
    <div class="tile is-ancestor" style="flex-wrap: wrap;">
<c:forEach items="${recordList}" var="record">
    <div class="tile is-parent is-5">
        <div class="tile is-child box">
            <article class="media">
                <div class="media-left">
                    <figure class="image is-96x96">
                        <my:image variable="${record.photo}"/>
                    </figure>
                    <br>
                    <br>
                </div>
                <div class="media-content">
                    <div class="content">
                        <h3>
                            <a href="?${AppConstants.COMMAND}=${CommandType.SHOW_RECORD_DETAILS}&${AppConstants.ID}=${record.id}">${record.name}</a>
                        </h3>
                        <div class="rating" style="color: orange; font-size: 30px;">
                            <c:forTokens items="1,2,3,4,5" delims="," var="star">
                                <c:if test="${star <= record.rating}">
                                    ☆
                                </c:if>
                            </c:forTokens>
                        </div>
                        <p><strong><i class="fas fa-location-arrow"></i> </strong>${record.place}</p>
                        <p><strong><i class="fas fa-calendar-alt"></i> </strong>${record.date}</p>
                    </div>
                </div>
            </article>
        </div>
    </div>
</c:forEach>
    </div>
</div>
<my:emptylist size="${recordList.size()}"/>
