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
    <p class="title is-4">Describe the wanted person</p>
    <p class="subtitle is-6">Or choose him from <a href="?${AppConstants.COMMAND}=${CommandType.SHOW_WANTED_PEOPLE}">the
        list</a></p>
    <br>

    <label for="first_name" class="label">First name</label>

    <div class="field">
        <div class="control">
            <input type="text" id="first_name" class="input is-normal" name="first_name"
                   placeholder="first name"/>
        </div>
    </div>
    <label for="last_name" class="label">Last name</label>
    <div class="field">
        <div class="control">
            <input type="text" id="last_name" class="input is-normal" name="last_name"
                   placeholder="first name"/>
        </div>
    </div>
    <label for="status" class="label">Person status</label>
    <div class="field">
        <div class="control">
            <select required="required" id="status" class="select" name="status" size="1">
                <option value="missing">missing</option>
                <option value="wanted">wanted</option>
            </select>
        </div>
    </div>
    <label for="description" class="label">Description<i>(required)</i></label>
    <div class="field">
        <div class="control">
            <textarea required="required" class="textarea" name="description" id="description"></textarea>
        </div>
    </div>
    <label for="birth_place" class="label">Birth place</label>

    <div class="field">
        <div class="control">
            <input type="text" id="birth_place" class="input is-normal" name="birth_place"
                   placeholder="birth place"/>
        </div>
    </div>
    <label for="birth_date" class="label">Birth date</label>
    <div class="field">
        <div class="control">
            <input type="date" id="birth_date" name="birth_date" class="date-picker"
                   max="<fmt:formatDate value="${date}" pattern="yyyy-mm-dd" />"/>
        </div>
    </div>

    <label for="search_area" class="label">Search area</label>

    <div class="field">
        <div class="control">
            <input type="text" id="search_area" class="input is-normal" name="search_area"
                   placeholder="search area"/>
        </div>
    </div>

    <label for="special_signs" class="label">Special signs</label>

    <div class="field">
        <div class="control">
            <input type="text" id="special_signs" class="input is-normal" name="special_signs"
                   placeholder="special signs"/>
        </div>
    </div>

    <label for="photo" class="label">Photo</label>
    <div class="field">
        <div class="control">
            <input class="button is-block is-light is-normal is-fullwidth" required="required" type="file" id="photo" name="photo" />
        </div>
    </div>
</c:if>
