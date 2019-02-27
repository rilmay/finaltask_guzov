
<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 27.02.2019
  Time: 2:06
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.guzov.finaltask.util.ServletConst" %>
<div class="container">
    <div class="content">
        <h2 class="text is-light is-">Success</h2>
        <p><c:if test="${(sessionScope.get(ServletConst.SESSION_USER) == null)}">
            Please log in
        </c:if></p>
    </div>
</div>

