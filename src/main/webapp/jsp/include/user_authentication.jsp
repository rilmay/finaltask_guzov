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

<div class="container">
    <div class="columns">
        <div class="is-one-third">
            <div class="card">
                <div class="card-content">
                    <div class="media-content">
                        <p class="title is-4">Authentication</p>
                        <p class="subtitle is-6">Please fill in the fields</p>
                    </div>
                    <p class="has-text-danger"><c:out value="${error_message}"/></p>
                    <div class="content">
                        <br>
                        <form action="${pageContext.request.contextPath}/" method="post">
                            <p>
                                <label for="login" class="field-label"> Your login: </label>
                                <input type="text" id="login" class="text-field is-right" name="login" required="required"
                                       placeholder="login" value="nikTestAuth"/>
                            </p>

                            <p>
                                <label for="password" class="field-label"> Your password: </label>
                                <input type="password" id="password" class="password-field" name="password"
                                       required="required"
                                       placeholder="password" value="12345"/>
                            </p>
                            <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.AUTHENTICATE_USER}">
                            <input class="button is-danger" type="submit" value="log in">
                        </form>
                        <a href="?${AppConstants.COMMAND}=${CommandType.SHOW_RECOVERY_PAGE}">forgot your password?</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
