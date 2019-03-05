<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 27.02.2019
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.guzov.finaltask.command.CommandType" %>
<%@ page import="by.guzov.finaltask.util.AppConstants" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="date" class="java.util.Date" />
<div class="container">
    <div class="columns">
        <div class="is-one-third">
            <div class="card">
                <div class="card-content">
                    <div class="media-content">
                        <p class="title is-4">Make a request</p>
                        <p class="subtitle is-6">Please fill in the fields</p>
                    </div>
                    <p class="has-text-danger"><c:out value="${error_message}"/></p>
                    <div class="content">
                        <br>
                        <form action="${pageContext.request.contextPath}/" method="post">
                            <p>
                                <label for="reward" class="field-label"> Reward: </label>
                                <input type="text" id="reward" class="text-field is-right" name="reward" required="required"
                                       placeholder="reward (USD)" pattern="[0-9]+"/>
                            </p>
                            <p>
                                <label for="application" class="field-label"> Application date: </label>
                                <input type="date" id="application" name="application_date"
                                       required="required" min="<fmt:formatDate value="${date}" pattern="yyyy-mm-dd" />"/>
                            </p>
                            <p>
                                <label for="lead" class="field-label"> Lead date: </label>
                                <input type="date" id="lead" name="lead_date"
                                       required="required" min="<fmt:formatDate value="${date}" pattern="yyyy-mm-dd" />"/>
                            </p>
                            <jsp:include page="wp_request_description.jsp"/>
                            <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.SEND_REQUEST}">
                            <input class="button is-danger" type="submit" value="send">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
