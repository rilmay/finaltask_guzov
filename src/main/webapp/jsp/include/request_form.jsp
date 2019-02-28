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
<%@ page import="by.guzov.finaltask.util.ServletConst" %>
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
                    <div class="content">
                        <br>
                        <form action="${pageContext.request.contextPath}/" method="post">
                            <p>
                                <label for="reward" class="field-label"> Reward: </label>
                                <input type="text" id="reward" class="text-field is-right" name="reward" required="required"
                                       placeholder="reward (USD)" pattern="[0-9]+(\.[0-9]+)?"/>
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
                            <c:if test="${not empty wp_id}">
                                <input type="hidden" name="wp_id" value="${wp_id}">
                            </c:if>
                            <c:if test="${empty wp_id}">
                                <h3>Describe the wanted person</h3>
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
                            <input type="hidden" name="user_id" value="${(sessionScope.get(ServletConst.SESSION_USER)).id}">
                            <input type="hidden" name="${ServletConst.COMMAND}" value="${CommandType.SEND_REQUEST}">
                            <input class="button is-danger" type="submit" value="send">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
