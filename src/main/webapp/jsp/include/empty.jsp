<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 17.02.2019
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.guzov.finaltask.util.AppConstants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="content">
        <c:if test="${empty sessionScope.get(AppConstants.SESSION_USER)}">
            <p>Please log in</p>
        </c:if>
        <div class="box">
            <p class="title is-3">About us</p>
            <p>People search service</p>
        </div>
    </div>
</div>
