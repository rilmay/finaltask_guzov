<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 17.02.2019
  Time: 2:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.guzov.finaltask.command.CommandType" %>
<aside class="menu">
    <p class="menu-label">
        Menu
    </p>
    <ul class="menu-list">
        <li><a href="?command=${CommandType.SHOW_USER_LIST}">User List</a></li>
        <li><a href="?command=${CommandType.SHOW_REGISTRATION_PAGE}">Registration(temporary)</a></li>
    </ul>
</aside>
