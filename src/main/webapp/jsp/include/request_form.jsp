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
<%@ page import="by.guzov.finaltask.util.FieldNames" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="date" class="java.util.Date" />

<div class="column is-6 is-centered">
    <div class="box is-2">
        <article class="media">
            <div class="media-content">
                <p class="title is-4">${text['links.make_request']}</p>
                <p class="subtitle is-6">${text['title.fill_in']}</p>
                <div class="content">
                    <form action="${pageContext.request.contextPath}/" method="post" enctype="multipart/form-data">
                        <my:inputfield label="${text['field.reward']}" name="${FieldNames.REWARD}"/>

                        <label for="application" class="label">${text['field.application_date']}:</label>

                        <div class="field">
                            <div class="control">
                                <input type="date" id="application" name="${FieldNames.APPLICATION_DATE}"
                                       required="required" class="date-picker"
                                       min="<fmt:formatDate value="${date}" pattern="yyyy-mm-dd" />">
                            </div>
                        </div>

                        <label for="lead" class="label">${text['field.lead_date']}</label>

                        <div class="field">
                            <div class="control">
                                <input type="date" id="lead" name="${FieldNames.LEAD_DATE}"
                                       required="required" class="date-picker"
                                       min="<fmt:formatDate value="${date}" pattern="yyyy-mm-dd" />"/>
                            </div>
                        </div>
                        <jsp:include page="wp_request_description.jsp"/>
                        <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.SEND_REQUEST}">
                        <input class="button is-block is-info is-normal is-fullwidth" type="submit" value="${text['button.send']}">
                    </form>
                </div>
            </div>
        </article>
    </div>
</div>
