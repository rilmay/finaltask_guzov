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
                    <figure class="image is-64x64">
                        <my:image variable="${record.photo}"/>
                    </figure>
                    <br>
                </div>
                <div class="media-content">
                    <div class="content">
                        <p>
                            <strong>
                                <a href="">
                                    ${record.name}
                                </a>
                            </strong></p>
                        <p><my:display variable="${record.description}"/></p>
                    </div>
                </div>
            </article>
        </div>
    </div>
</c:forEach>
<c:if test="${peopleList.size() eq 0}">
    <div class="has-text-centered is-light">
        <br>
        <br>
        <p class="subtitle">Nothing to show...</p>
        <br>
        <br>
    </div>
</c:if>
