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
    <link rel="stylesheet" href="static/rating.css">
    <link rel="stylesheet" type="text/css" href="static/loading.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <script src="js/sweetalert.min.js"></script>
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/custom.js"></script>
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
            <section class="hero is-warning welcome is-small">
                <div class="hero-body">
                    <div class="container">
                        <h1 class="title">
                            <i class="fas fa-bell"></i> ${text['title.hello']}
                        </h1>
                        <h2 class="subtitle">
                                ${text['title.log_in_register']}
                        </h2>
                    </div>
                </div>
            </section>
            </c:if>
            <br>
            <section>
                <jsp:include page="include/${view_name}.jsp"/>
            </section>
        </div>
    </div>
</div>
<footer class="footer">
    <div class="content has-text-centered">
        <p>
            Nikita Guzov final project <i class="fas fa-copyright"></i>
        </p>
    </div>
</footer>
    <script>window.onload = function () {
        <c:if test="${not empty error_message}">error('${error_message}')</c:if>
        <c:if test="${success}">successWindow('${text['title.success']}')</c:if>
        document.getElementById('${text.getLocaleTag()}').setAttribute("disabled","disabled")}

        function loading(id) {
            var element = document.getElementById(id);
            element.classList.add("ld");
            element.classList.add("ld-ring");
            element.classList.add("ld-spin");
        }
    </script>
</body>
</html>