<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 03.03.2019
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<nav class="navbar">
    <div class="navbar-menu">
        <div class="navbar-end">
            <span class="navbar-item">
                <a class="button is-light is-inverted" href="?change_lang=en">
                    <span>${text['en']}</span>
                </a>
            </span>
            <span class="navbar-item">
                <a class="button is-light is-inverted" href="?change_lang=ru">
                    <span>${text['ru']}</span>
                </a>
            </span>
        </div>
    </div>
</nav>
