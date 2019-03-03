<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 17.02.2019
  Time: 2:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="by.guzov.finaltask.command.CommandType" %>
<%@ page import="by.guzov.finaltask.util.AppConstants" %>

<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Interpol</title>
    <link rel="stylesheet" href="static/bulma.css">
</head>
<body>
<section class="hero is-dark">
    <div class="hero-head">
        <jsp:include page="include/header.jsp"/>
    </div>
    <div class="hero-body">
        <div class="container">
            <h1 class="title">
                <a href="?${AppConstants.COMMAND}=${CommandType.SHOW_EMPTY_PAGE}">Interpol page</a>
            </h1>
            <h2 class="subtitle"><c:out value="${text['hello']}"/> here you can apply requests
            </h2>
        </div>
    </div>
</section>
<section class="is-fullheight is-medium">
    <div class="columns">
        <div class="column is-one-quarter">
            <jsp:include page="navigation.jsp"/>
        </div>
        <div class="column is-three-quarters">
            <jsp:include page="include/${view_name}.jsp"/>
        </div>
    </div>
</section>
<section class="footer">
    <div class="content has-text-centered">
        <p>
            Nikita Guzov final project
        </p>
    </div>
</section>
</body>
</html>
