<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 11.03.2019
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.guzov.finaltask.command.CommandType" %>
<%@ page import="by.guzov.finaltask.util.AppConstants" %>
<%@ page import="by.guzov.finaltask.util.FieldNames" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="column is-6 is-centered">
    <div class="box is-2">
        <article class="media">
            <div class="media-content">
                <p class="title is-4">Make a record</p>
                <p class="subtitle is-6">Please fill in the fields</p>
                <c:if test="${not empty error_messages}">
                    <c:forEach items="${error_messages}" var="error">
                        <p class="has-text-danger">${error}</p>
                    </c:forEach>
                </c:if>
                <div class="content">
                    <form action="${pageContext.request.contextPath}/" method="post" enctype="multipart/form-data">
                        <p>
                            <label for="title" class="label">Title</label>

                        <div class="field">
                            <div class="control">
                                <input type="text" id="title" class="input is-normal" name="${FieldNames.NAME}"
                                       required="required"
                                       placeholder="title"/>
                            </div>
                        </div>

                            <label for="rating" class="label">Rating</label>
                        <div class="field">
                            <div class="control">
                                <select required="required" id="rating" class="select" name="${FieldNames.RATING}" size="1">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                </select>
                            </div>
                        </div>


                        <label for="place" class="label">Place</label>

                        <div class="field">
                            <div class="control">
                                <input type="text" id="place" class="input is-normal" name="${FieldNames.PLACE}"
                                       required="required"
                                       placeholder="place"/>
                            </div>
                        </div>


                        <label for="date" class="label">Date</label>

                        <div class="field">
                            <div class="control">
                                <input type="date" id="date" name="${FieldNames.DATE}"
                                       required="required" class="date-picker"/>
                            </div>
                        </div>

                        <label for="description" class="label">Description</label>
                        <div class="field">
                            <div class="control">
                                <textarea required="required" class="textarea" name="${FieldNames.DESCRIPTION}" id="description"></textarea>
                            </div>
                        </div>


                        <label for="photo" class="label">Photo</label>
                        <div class="field">
                            <div class="control">
                                <input class="button is-block is-light is-normal is-fullwidth" type="file"
                                       id="photo" name="${FieldNames.PHOTO}" />
                            </div>
                        </div>

                        <input type="hidden" name="${AppConstants.COMMAND}" value="${CommandType.SEND_RECORD}">
                        <input class="button is-block is-info is-normal is-fullwidth" type="submit" value="send">
                    </form>
                </div>
            </div>
        </article>
    </div>
</div>
