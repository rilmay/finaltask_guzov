<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 06.03.2019
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.guzov.finaltask.command.CommandType" %>
<%@ page import="by.guzov.finaltask.util.AppConstants" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<div class="column is-4 is-centered">
    <div class="box is-2">
        <article class="media">
            <div class="media-content">
                <p class="title is-4">Upload photo</p>
                <p class="subtitle is-6">for <my:display variable="${person.firstName}"/> <my:display variable="${person.lastName}"/></p>
                <p class="has-text-warning"><c:out value="${error_message}"/></p>
                <div class="content">
                    <form action="${pageContext.request.contextPath}/" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.UPLOAD_PHOTO}">
                        <input type="file" id="photo" name="photo" />
                        <input class="button is-block is-success is-normal is-fullwidth" type="submit" value="upload">
                    </form>
                </div>
            </div>
        </article>
    </div>
</div>
