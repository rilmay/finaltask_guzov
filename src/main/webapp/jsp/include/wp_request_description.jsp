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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<c:if test="${not empty wp}">
    <p>
        request for search <a href="?${AppConstants.COMMAND}=${CommandType.SHOW_PERSON_DETAILS}&${AppConstants.ID}=${wp.id}">
            <my:display variable="${wp.firstName}"/> <my:display variable="${wp.lastName}"/></a>
    </p>
    <input type="hidden" name="${AppConstants.ID}" value="${wp.id}">
</c:if>
<c:if test="${empty wp}">
    <br>
    <p class="subtitle is-5">Describe the wanted person</p>
    <p class="subtitle is-6">Or choose him from <a href="?${AppConstants.COMMAND}=${CommandType.SHOW_WANTED_PEOPLE}">the list</a></p>
    <br>
    <p>
        <label for="first_name" class="field-label"> First name: </label>
        <input type="text" id="first_name" class="text-field is-right" name="first_name"
               placeholder="first name"/>
    </p>
    <p>
        <label for="last_name" class="field-label"> Last name: </label>
        <input type="text" id="last_name" class="text-field is-right" name="last_name"
               placeholder="first name"/>
    </p>
    <p>
        <label for="status" class="field-label"> Person status: </label>
        <select id="status" name="status" size="1">
            <option disabled>pick status</option>
            <option value="missing">missing</option>
            <option value="wanted">wanted</option>
        </select>
    </p>
    <p>
        <label for="description" class="field-label"> Description: </label>
        <textarea rows="5" cols="45" name="description" id="description"></textarea>
    </p>
    <p>
        <label for="birth_place" class="field-label"> Birth place: </label>
        <input type="text" id="birth_place" class="text-field is-right" name="birth_place"
               placeholder="birth place"/>
    </p>
    <p>
        <label for="birth_date" class="field-label"> Birth date: </label>
        <input type="date" id="birth_date" name="birth_date"
               max="<fmt:formatDate value="${date}" pattern="yyyy-mm-dd" />"/>
    </p>
    <p>
        <label for="search_area" class="field-label"> Search area: </label>
        <input type="text" id="search_area" class="text-field is-right" name="search_area"
               placeholder="search area"/>
    </p>
    <p>
        <label for="special_signs" class="field-label"> Special signs: </label>
        <input type="text" id="special_signs" class="text-field is-right" name="special_signs"
               placeholder="special signs"/>
    </p>
    <p>
        <label for="photo" class="field-label"> Photo: </label>
        <input type="text" id="photo" class="text-field is-right" name="photo"
               placeholder="photo"/>
    </p>
</c:if>
