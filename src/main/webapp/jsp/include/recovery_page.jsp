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

<div class="column is-6 is-centered">
    <div class="box is-2">
        <article class="media">
            <div class="media-content">
                <p class="title is-4">Password recovery</p>
                <div class="content">
                    <br>
                    <c:if test="${empty sessionScope.get('recovery')}">
                        <form action="${pageContext.request.contextPath}/" method="post">
                            <label for="login" class="label">Login</label>
                            <div class="field">
                                <div class="control">
                                    <input type="text" id="login" class="input is-normal is-focused" name="login"
                                           required="required"
                                           placeholder="login"/>
                                </div>
                            </div>
                            <input type="hidden" name="${AppConstants.COMMAND}"
                                   value="${CommandType.SHOW_RECOVERY_PAGE}">
                            <input class="button is-block is-info is-normal is-fullwidth" type="submit" value="send e-mail">
                        </form>
                    </c:if>
                    <c:if test="${not empty sessionScope.get('recovery')}">
                        We have sent you email, please enter secret code and new password
                        <form action="${pageContext.request.contextPath}/" method="post">

                            <label for="code" class="label">Secret code</label>

                            <div class="field">
                                <div class="control">
                                    <input type="text" id="code" class="input is-normal" name="code"
                                           required="required"
                                           placeholder="code"/>
                                </div>
                            </div>

                            <label for="new_password" class="label">New password</label>

                            <div class="field">
                                <div class="control">
                                    <input type="password" id="new_password" class="input is-normal" name="new_password"
                                           required="required"
                                           placeholder="password"/>
                                </div>
                            </div>
                            <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.RECOVER_PASSWORD}">
                            <input class="button is-block is-info is-normal is-fullwidth" type="submit" value="confirm">
                        </form>
                    </c:if>
                </div>
            </div>
        </article>
    </div>
</div>
