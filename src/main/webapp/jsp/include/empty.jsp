<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 17.02.2019
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="content">
        <h2>Click anything from the menu</h2>
        <c:if test="${sessionScope.get('authorized') != null}">
            <p>Hello, you role is ${sessionScope.get("authorized")}</p>
        </c:if>
        <c:if test="${sessionScope.get('authorized') == null}">
            <p>Login: nikTestAuth, password: 12345 - admin account</p>
        </c:if>
    </div>
</div>
