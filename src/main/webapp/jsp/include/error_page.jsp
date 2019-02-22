<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 22.02.2019
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="content">
        <h3 class="has-text-danger">Error occurs, cause: ${error_message}</h3>
        <h3>${sessionScope.get("authorized")}</h3>
    </div>
</div>
