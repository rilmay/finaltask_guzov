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

<div class="column is-6 is-centered">
    <div class="box is-2">
        <article class="media">
            <div class="media-content">
                <p class="title is-4">Make a request</p>
                <p class="subtitle is-6">Please fill in the fields</p>
                <p class="has-text-warning"><c:out value="${error_message}"/></p>
                <div class="content">
                    <form action="${pageContext.request.contextPath}/" method="post">
                        <p>
                            <label for="reward" class="label">Reward</label>

                        <div class="field">
                            <div class="control">
                                <input type="text" id="reward" class="input is-normal" name="reward"
                                       required="required"
                                       placeholder="reward (USD)" pattern="[0-9]+"/>
                            </div>
                        </div>
                        <label for="application" class="label">Application date</label>

                        <div class="field">
                            <div class="control">
                                <input type="date" id="application" name="application_date"
                                       required="required" class="date-picker"
                                       min="<fmt:formatDate value="${date}" pattern="yyyy-mm-dd" />">
                            </div>
                        </div>

                        <label for="lead" class="label">Lead date</label>

                        <div class="field">
                            <div class="control">
                                <input type="date" id="lead" name="lead_date"
                                       required="required" class="date-picker"
                                       min="<fmt:formatDate value="${date}" pattern="yyyy-mm-dd" />"/>
                                </p>
                            </div>
                        </div>
                        <jsp:include page="wp_request_description.jsp"/>
                        <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.SEND_REQUEST}">
                        <input class="button is-block is-info is-normal is-fullwidth" type="submit" value="send">
                    </form>
                </div>
            </div>
        </article>
    </div>
</div>
