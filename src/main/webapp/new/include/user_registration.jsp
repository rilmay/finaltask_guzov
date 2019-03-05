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

<div class="column is-6 is-centered">
    <div class="box is-2">
        <article class="media">
            <div class="media-content">
                <p class="title is-4">Registration</p>
                <p class="subtitle is-6">Please fill in the fields</p>
                <p class="has-text-info"><c:out value="${error_message}"/></p>
                <div class="content">
                    <form action="${pageContext.request.contextPath}/" method="post">
                        <label for="login" class="label">Login</label>
                        <div class="field">
                            <div class="control">
                                <input type="text" id="login" class="input is-normal" name="login" required="required"
                                       placeholder="login"/>
                            </div>
                        </div>

                        <label for="password" class="label">Password</label>
                        <div class="field">
                            <div class="control">
                                <input type="password" id="password" class="input is-normal" name="password"
                                       required="required"
                                       placeholder="password"/>

                            </div>
                        </div>

                        <label for="email" class="label">E-mail</label>
                        <div class="field">
                            <div class="control">
                                <input type="text" id="email" class="input is-normal" name="email" required="required"
                                       placeholder="e-mail"/>

                            </div>
                        </div>

                        <label for="first_name" class="label">First name</label>
                        <div class="field">
                            <div class="control">
                                <input type="text" id="first_name" class="input is-normal" name="first_name"
                                       required="required"
                                       placeholder="first name"/>

                            </div>
                        </div>

                        <label for="last_name" class="label">Last name</label>
                        <div class="field">
                            <div class="control">
                                <input type="text" id="last_name" class="input is-normal" name="last_name"
                                       required="required"
                                       placeholder="last name"/>
                            </div>
                        </div>

                        <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.REGISTER_USER}">
                        <input class="button is-block is-info is-normal is-fullwidth" type="submit" value="register">
                    </form>
                </div>
                <p class="has-text-info"><c:out value="${requirements_message}"/></p>
            </div>
        </article>
    </div>
</div>