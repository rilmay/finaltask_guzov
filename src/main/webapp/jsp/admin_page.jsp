<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 17.02.2019
  Time: 2:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin page</title>
    <link rel="stylesheet" href="decor/bulma.css">
</head>
<body>
<section class="hero is-dark">
    <div class="hero-head">
    </div>
    <div class="hero-body">
        <div class="container">
            <h1 class="title">
                User list
            </h1>
            <h2 class="subtitle">
                admin only
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
            <jsp:include page="include/${viewName}.jsp"/>
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