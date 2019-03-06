<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 06.03.2019
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.guzov.finaltask.util.AppConstants" %>
<html>
<head>
    <title>Redirect</title>
</head>
<body>
    <c:redirect url="?${AppConstants.COMMAND}=${redirect_command}"/>
</body>
</html>
