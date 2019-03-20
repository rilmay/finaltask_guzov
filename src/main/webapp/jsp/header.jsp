<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 05.03.2019
  Time: 3:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ page import="by.guzov.finaltask.command.CommandType" %>
<%@ page import="by.guzov.finaltask.util.AppConstants" %>


<nav class="navbar is-light">
    <div class="container">
        <div class="navbar-brand">
            <a class="navbar-item brand-text" onclick="openCloseMenu()">
                ${text['app.title.interpol']}
            </a>
            <div class="navbar-burger burger" data-target="navMenu">
                <span></span>
                <span></span>
                <span></span>
            </div>
        </div>
        <div id="navMenu" class="navbar-menu">
            <div class="navbar-start">
                <a class="navbar-item"><i class="fas fa-home"></i></a>
                <a class="navbar-item" href="?${AppConstants.COMMAND}=${CommandType.SHOW_EMPTY_PAGE}">
                    ${text['links.home']}
                </a>
                <div class="navbar-item has-dropdown is-hoverable">
                    <a class="navbar-item"><i class="fas fa-walking"></i></a>
                    <a class="navbar-link">
                        ${text['links.wanted_people']}
                    </a>
                    <div class="navbar-dropdown">
                        <a class="navbar-item"
                           href="?${AppConstants.COMMAND}=${CommandType.SHOW_WANTED_PEOPLE}">
                            ${text['links.all']}
                        </a>
                        <a class="navbar-item"
                           href="?${AppConstants.COMMAND}=${CommandType.SHOW_WANTED_PEOPLE}&${AppConstants.ONLY}=relevant">
                            ${text['links.status_relevant']}
                        </a>
                        <a class="navbar-item"
                           href="?${AppConstants.COMMAND}=${CommandType.SHOW_WANTED_PEOPLE}&${AppConstants.ONLY}=found">
                            ${text['links.status_found']}
                        </a>
                    </div>
                </div>
                <div class="navbar-item has-dropdown is-hoverable">
                    <a class="navbar-item"><i class="fas fa-list-alt"></i></a>
                    <a class="navbar-link">
                        ${text['links.requests']}
                    </a>
                    <div class="navbar-dropdown">
                        <a class="navbar-item" href="?${AppConstants.COMMAND}=${CommandType.SHOW_REQUEST_LIST}">
                            ${text['links.all']}
                        </a>
                        <a class="navbar-item"
                           href="?${AppConstants.COMMAND}=${CommandType.SHOW_REQUEST_LIST}&${AppConstants.ONLY}=${AppConstants.STATUS_APPROVED}">
                            ${text['links.status_relevant']}
                        </a>
                        <a class="navbar-item"
                           href="?${AppConstants.COMMAND}=${CommandType.SHOW_REQUEST_LIST}&${AppConstants.ONLY}=${AppConstants.STATUS_COMPLETED}">
                            ${text['links.status_completed']}
                        </a>
                    </div>
                </div>
                <div class="navbar-item has-dropdown is-hoverable">
                    <a class="navbar-item"><i class="far fa-newspaper"></i></a>
                    <a class="navbar-link">${text['links.records']}</a>
                    <div class="navbar-dropdown">
                        <a class="navbar-item"
                           href="?${AppConstants.COMMAND}=${CommandType.SHOW_RECORD_LIST}">
                            ${text['links.status_relevant']}
                        </a>
                        <a class="navbar-item"
                           href="?${AppConstants.COMMAND}=${CommandType.SHOW_RECORD_LIST}&${AppConstants.ONLY}=expired">
                            ${text['links.archive']}
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="navbar-menu">
            <div class="navbar-end">
                <span class="navbar-item">
                    <i class="fas fa-globe-europe"></i>
                </span>
				<span class="navbar-item">
                    <button id="en" class="button is-light" type="button" onClick="changeLang('en')">
                        ${text['links.en']}
                    </button>
                </span>
                <span class="navbar-item">
                    <button id="ru" class="button is-light" type="button" onClick="changeLang('ru')">
                        ${text['links.ru']}
                    </button>
                </span>
            </div>
        </div>
        <div class="navbar-tabs">
            <c:if test="${empty sessionScope.get(AppConstants.SESSION_USER)}">
            <span class="navbar-item">
                <a href="?${AppConstants.COMMAND}=${CommandType.SHOW_REGISTRATION_PAGE}"><i class="fas fa-user-plus"></i> ${text['links.registration']}</a>
            </span>
                <span class="navbar-item">
                <a href="?${AppConstants.COMMAND}=${CommandType.SHOW_AUTHENTICATION_PAGE}"><i class="fas fa-sign-in-alt"></i> ${text['links.log_in']}</a>
            </span>
            </c:if>
            <c:if test="${not empty sessionScope.get(AppConstants.SESSION_USER)}">
            <span class="navbar-item">
                <a href="?${AppConstants.COMMAND}=${CommandType.SHOW_PERSONAL_PAGE}">
                    <i class="fas fa-user"></i> <c:out value="${sessionScope.get(AppConstants.SESSION_USER).login}"/>
                </a>
            </span>
                <span class="navbar-item">
                <a href="?${AppConstants.COMMAND}=${CommandType.LOG_OUT_USER}">
                    <i class="fas fa-sign-out-alt"></i> ${text['links.log_out']}
                </a>
                </span>
            </c:if>
        </div>
    </div>
    <script type="text/javascript">
        function changeLang(langToChange) {
            var currentLocation = window.location;
            var locationToGo;
            if (currentLocation.search) {
                if (currentLocation.search.indexOf("change_lang=") > 0) {
                    var spliced = currentLocation.search.split("change_lang=")[0];
                    locationToGo = spliced + 'change_lang=' + langToChange;
                } else {
                    locationToGo = currentLocation.search + '&change_lang=' + langToChange;
                }
            } else {
                locationToGo = currentLocation.search + '?change_lang=' + langToChange;
            }
            window.location.href = locationToGo;
        }

        function openCloseMenu() {
            var doc = document.getElementById("menu");
            var display = doc.style.display;
            if(display == "none"){
                doc.style.display = "block";
            }else {
                doc.style.display = "none";
            }
        }
    </script>
</nav>
