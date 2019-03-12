<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 05.03.2019
  Time: 3:46
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.guzov.finaltask.util.AppConstants" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Interpol</title>
    <link rel="stylesheet" href="static/bulma.css">

    <link rel="stylesheet" href="css/dropdown_menu.css">
    <script src="/js/sweetalert.min.js"></script>
    <script src="/js/jquery-3.3.1.min.js"></script>
    <script src="/js/custom.js"></script>
</head>
<body>


<!-- START NAV -->
<jsp:include page="header.jsp"/>
<!-- END NAV -->
<div class="container">
    <div class="columns">
        <jsp:include page="menu.jsp"/>
        <div class="column is-9">
            <c:if test="${empty sessionScope.get(AppConstants.SESSION_USER)}">
            <section class="hero is-info welcome is-small">
                <div class="hero-body">
                    <div class="container">
                        <h1 class="title">
                            Hello
                        </h1>
                        <h2 class="subtitle">
                            please log in or register
                        </h2>
                    </div>
                </div>
            </section>
            </c:if>
            <br>
            <c:if test="${success}">
                <script>window.onload = function () { successWindow('${text['success_title']}') }</script>
            </c:if>
            <section>
                <jsp:include page="include/${view_name}.jsp"/>
            </section>
        </div>
    </div>
</div>
<footer class="footer">
    <div class="content has-text-centered">
        <p>
            Nikita Guzov final project
        </p>
    </div>
</footer>
<script async type="text/javascript" src="../js/bulma.js"></script>
</body>