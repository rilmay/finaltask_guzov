<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 21.02.2019
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.guzov.finaltask.command.CommandType" %>
<%@ page import="by.guzov.finaltask.util.AppConstants" %>
<%@ page import="by.guzov.finaltask.util.FieldNames" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<div class="column is-6 is-centered">
    <div class="box is-2">
        <article class="media">
            <div class="media-content">
                <p class="title is-4">${text['title.authentication']}</p>
                <p class="subtitle is-6">${text['title.fill_in']}</p>
                <div class="content">
                    <form action="${pageContext.request.contextPath}/" method="post">
                        <my:inputfield label="${text['field.login']}" name="${FieldNames.LOGIN}"/>
                        <my:inputfield label="${text['field.password']}" name="${FieldNames.PASSWORD}" type="password"/>
                        <my:loadingbutton command="${CommandType.AUTHENTICATE_USER}" label="${text['links.log_in']}" />
                    </form>
                    <a href="?${AppConstants.COMMAND}=${CommandType.SHOW_RECOVERY_PAGE}">${text['links.forgot_password']}</a>
                </div>
            </div>
        </article>
    </div>
</div>
