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

<c:forEach items="${recordList}" var="record">
    <div class="column is-8">
        <div class="box is-2">
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
                        <p><strong>Place: </strong>${record.place}</p>
                        <p><strong>Date: </strong>${record.date}</p>
                    </div>
                </div>
                <div class="media-right">
                    <div class="rating" style="color: orange; width: 150px; font-size: 30px; height: 40px;">
                        <c:forTokens items="1,2,3,4,5" delims="," var="star">
                            <c:if test="${star <= record.rating}">
                                ☆
                            </c:if>
                        </c:forTokens>
                    </div>
                </div>
            </article>
        </div>
    </div>
</c:forEach>
<c:if test="${recordList.size() eq 0}">
    <div class="has-text-centered is-light">
        <br>
        <br>
        <p class="subtitle">Nothing to show...</p>
        <br>
        <br>
    </div>
</c:if>
