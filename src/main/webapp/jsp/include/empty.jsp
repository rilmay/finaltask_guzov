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
            <p>Hello, your role is ${(sessionScope.get('session_user') == null)? 'anon' :(sessionScope.get('session_user')).role}</p>
        <c:if test="${sessionScope.get('session_user') == null}">
            <p>Login: nikTestAuth, password: 12345 - admin account</p>
        </c:if>
    </div>
</div>
