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
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ page import="by.guzov.finaltask.util.FieldNames" %>

<div class="column is-6 is-centered">
    <div class="box is-2">
        <article class="media">
            <div class="media-content">
                <p class="title is-4">${text['title.password_recovery']}</p>
                <div class="content">
                    <br>
                    <c:if test="${empty sessionScope.get('recovery')}">
                        <form action="${pageContext.request.contextPath}/" method="post">
                            <my:inputfield label="${text['field.login']}" name="${FieldNames.LOGIN}"/>
                            <my:loadingbutton label="${text['button.send_message']}" command="${CommandType.SHOW_RECOVERY_PAGE}" id="b5"/>
                        </form>
                    </c:if>
                    <c:if test="${not empty sessionScope.get('recovery')}">
                        ${text['title.recovery_info']}
                        <form action="${pageContext.request.contextPath}/" method="post">
                            <my:inputfield label="${text['field.secret_code']}" name="code"/>
                            <my:inputfield label="${text['field.new_password']}" name="new_password" type="password"/>
                            <my:loadingbutton label="${text['button.confirm']}" command="${CommandType.RECOVER_PASSWORD}" id="b6"/>
                        </form>
                    </c:if>
                </div>
            </div>
        </article>
    </div>
</div>
