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

<div class="column is-6 is-centered">
    <div class="box is-2">
        <article class="media">
            <div class="media-content">
                <p class="title is-4">Authentication</p>
                <p class="subtitle is-6">Please fill in the fields</p>
                <p class="has-text-info"><c:out value="${error_message}"/></p>
                <div class="content">
                    <form action="${pageContext.request.contextPath}/" method="post">
                        <label for="login" class="label">Login</label>
                        <div class="field">
                            <div class="control">
                                <input type="text" id="login" class="input is-normal" name="login" required="required"
                                       placeholder="login" value="nikTestAuth"/>
                            </div>
                        </div>

                        <label for="password" class="label">Password</label>
                        <div class="field">
                            <div class="control">
                                <input type="password" id="password" class="input is-normal" name="password"
                                       required="required"
                                       placeholder="password" value="12345"/>

                            </div>
                        </div>
                        <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.AUTHENTICATE_USER}">
                        <input class="button is-block is-info is-normal is-fullwidth" type="submit" value="Log in">
                    </form>
                    <a href="?${AppConstants.COMMAND}=${CommandType.SHOW_RECOVERY_PAGE}">forgot your password?</a>
                </div>
            </div>
        </article>
    </div>
</div>
