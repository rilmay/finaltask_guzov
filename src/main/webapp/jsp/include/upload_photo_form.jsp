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
<%@ page import="by.guzov.finaltask.util.FieldNames" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<div class="column is-4 is-centered">
    <div class="box is-2">
        <article class="media">
            <div class="media-content">
                <p class="title is-4"><i class="fas fa-camera"></i> ${text['title.upload']}</p>
                <p class="subtitle is-6">${text['title.for']} <a href="?${AppConstants.COMMAND}=${CommandType.SHOW_PERSON_DETAILS}&${AppConstants.ID}=${person.id}">
                    <my:display variable="${person.firstName}"/> <my:display variable="${person.lastName}"/></a></p>
                <div class="content">
                    <form action="${pageContext.request.contextPath}/" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="${AppConstants.ID}" value="${person.id}">
                        <my:inputfield label="${text['field.photo']}" name="${FieldNames.PHOTO}" type="file"/>
                        <br>
                        <my:loadingbutton label="${text['button.upload_photo']}" command="${CommandType.UPLOAD_PHOTO}" type="is-success"/>
                    </form>
                </div>
            </div>
        </article>
    </div>
</div>
