<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 18.02.2019
  Time: 10:39
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
                <p class="title is-4">Registration</p>
                <p class="subtitle is-6">Please fill in the fields</p>
                <div class="content">
                    <form action="${pageContext.request.contextPath}/" method="post">
                        <my:inputfield label="Login" name="${FieldNames.LOGIN}"/>

                        <my:inputfield label="Password" name="${FieldNames.PASSWORD}" type="password"/>

                        <my:inputfield label="E-mail" name="${FieldNames.EMAIL}"/>

                        <my:inputfield label="First name" name="${FieldNames.FIRST_NAME}"/>

                        <my:inputfield label="Last name" name="${FieldNames.LAST_NAME}"/>

                        <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.REGISTER_USER}">
                        <input class="button is-block is-info is-normal is-fullwidth" type="submit" value="register">
                    </form>
                </div>
                <p class="has-text-info"><c:out value="${requirements_message}"/></p>
            </div>
        </article>
    </div>
</div>