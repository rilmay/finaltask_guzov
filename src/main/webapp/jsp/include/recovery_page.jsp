<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 26.02.2019
  Time: 10:47
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
                        <p class="title is-4">Password recovery</p>
                    </div>
                    <div class="content">
                        <br>
                        <c:if test="${empty sessionScope.get('recovery')}">
                        <form action="${pageContext.request.contextPath}/" method="post">
                            <p>
                                <label for="login" class="field-label"> Your login: </label>
                                <input type="text" id="login" class="text-field is-right" name="login"
                                       required="required"
                                       placeholder="login"/>
                            </p>
                            <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.SHOW_RECOVERY_PAGE}">
                                <input class="button is-danger" type="submit" value="send e-mail">
                        </form>
                        </c:if>
                        <c:if test="${not empty sessionScope.get('recovery')}">
                            <p>We have sent you email, please enter secret code and new password</p>
                            <form action="${pageContext.request.contextPath}/" method="post">
                                <p>
                                    <label for="code" class="field-label"> Secret code: </label>
                                    <input type="text" id="code" class="text-field is-right" name="code"
                                           required="required"
                                           placeholder="code"/>
                                </p>
                                <p>
                                    <label for="new_password" class="field-label"> New password: </label>
                                    <input type="password" id="new_password" class="password-field" name="new_password"
                                           required="required"
                                           placeholder="password"/>
                                </p>
                                <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.RECOVER_PASSWORD}">
                                <input class="button is-danger" type="submit" value="confirm">
                            </form>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
