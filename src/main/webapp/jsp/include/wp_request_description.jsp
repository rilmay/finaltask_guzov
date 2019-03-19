<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 28.02.2019
  Time: 16:43
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

<c:if test="${not empty wp}">
    <p>
        request for search <a
            href="?${AppConstants.COMMAND}=${CommandType.SHOW_PERSON_DETAILS}&${AppConstants.ID}=${wp.id}">
        <my:display variable="${wp.firstName}"/> <my:display variable="${wp.lastName}"/></a>
    </p>
    <input type="hidden" name="${AppConstants.ID}" value="${wp.id}">
</c:if>
<c:if test="${empty wp}">
    <br>
    <p class="title is-4">${text['title.describe_wanted_person']}</p>
    <p class="subtitle is-6">${text['title.choose']} <a href="?${AppConstants.COMMAND}=${CommandType.SHOW_WANTED_PEOPLE}">
            ${text['title.the_list']}</a></p>
    <br>

    <my:inputfield label="${text['field.first_name']}" name="${FieldNames.FIRST_NAME}" requied="false"/>
    <my:inputfield label="${text['field.last_name']}" name="${FieldNames.LAST_NAME}" requied="false"/>
    <label for="status" class="label">${text['field.status']}:</label>
    <div class="field">
        <div class="control">
            <select required="required" id="status" class="select" name="${FieldNames.PERSON_STATUS}" size="1">
                <option value="missing">${text['status.missing']}</option>
                <option value="wanted">${text['status.wanted']}</option>
            </select>
        </div>
    </div>
    <label for="description" class="label">${text['field.description']}<i>(${text['title.required']})</i></label>
    <div class="field">
        <div class="control">
            <textarea required="required" class="textarea" name="${FieldNames.DESCRIPTION}" id="description"></textarea>
        </div>
    </div>
    <my:inputfield label="${text['field.birth_place']}" name="${FieldNames.BIRTH_PLACE}" requied="false"/>
    <label for="birth_date" class="label">${text['field.birth_date']}:</label>
    <div class="field">
        <div class="control">
            <input type="date" id="birth_date" name="${FieldNames.BIRTH_DATE}" class="date-picker"
                   max="<fmt:formatDate value="${date}" pattern="yyyy-mm-dd" />"/>
        </div>
    </div>
    <my:inputfield label="${text['field.search_area']}" name="${FieldNames.SEARCH_AREA}" requied="false"/>
    <my:inputfield label="${text['field.special_signs']}" name="${FieldNames.SPECIAL_SIGNS}" requied="false"/>
    <my:inputfield label="${text['field.photo']}" name="${FieldNames.PHOTO}" type="file" requied="false"/>
    <br>
</c:if>
