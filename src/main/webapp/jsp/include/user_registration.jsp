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

<div class="container">
    <div class="columns">
        <div class="is-one-third">
            <div class="card">
                <div class="card-content">
                    <div class="media-content">
                        <p class="title is-4">Registration</p>
                        <p class="subtitle is-6">Please fill in the fields</p>
                    </div>
                    <p class="has-text-danger"><c:out value="${error_message}"/></p>
                    <div class="content">
                        <br>
                        <form action="${pageContext.request.contextPath}/" method="post">
                            <p>
                                <label for="login" class="field-label"> Your login: </label>
                                <input type="text" id="login" class="text-field is-right" name="login" required="required"
                                       placeholder="login"/>
                            </p>

                            <p>
                                <label for="password" class="field-label"> Your password: </label>
                                <input type="password" id="password" class="password-field" name="password"
                                       required="required"
                                       placeholder="password"/>
                            </p>
                            <p>
                                <label for="email" class="field-label"> Your e-mail: </label>
                                <input type="text" id="email" class="text-field" name="email" required="required"
                                       placeholder="e-mail"/>
                            </p>
                            <p>
                                <label for="first_name" class="field-label"> Your first name: </label>
                                <input type="text" id="first_name" class="text-field" name="first_name"
                                       required="required"
                                       placeholder="first name"/>
                            </p>
                            <p>
                                <label for="last_name" class="field-label"> Your last name </label>
                                <input type="text" id="last_name" class="text-field" name="last_name"
                                       required="required"
                                       placeholder="last name"/>
                            </p>
                            <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.REGISTER_USER}">
                            <input class="button is-danger" type="submit" value="register">
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <p class="has-text-danger"><c:out value="${requirements_message}"/></p>
</div>