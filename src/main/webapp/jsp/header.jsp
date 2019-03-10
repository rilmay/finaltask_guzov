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
                <my:lang key="interpol"/>
            </a>
            <div class="navbar-burger burger" data-target="navMenu">
                <span></span>
                <span></span>
                <span></span>
            </div>
        </div>
        <div id="navMenu" class="navbar-menu">
            <div class="navbar-start">
                <a class="navbar-item" href="?${AppConstants.COMMAND}=${CommandType.SHOW_EMPTY_PAGE}">
                    <my:lang key="home"/>
                </a>
                </div>
                <div class="navbar-item has-dropdown is-hoverable">
                    <a class="navbar-link">
                        <my:lang key="wanted_people"/>
                    </a>
                    <div class="navbar-dropdown">
                        <a class="navbar-item"
                           href="?${AppConstants.COMMAND}=${CommandType.SHOW_WANTED_PEOPLE}">
                            All
                        </a>
                        <a class="navbar-item"
                           href="?${AppConstants.COMMAND}=${CommandType.SHOW_WANTED_PEOPLE}&${AppConstants.ONLY}=relevant">
                            Relevant
                        </a>
                        <a class="navbar-item"
                           href="?${AppConstants.COMMAND}=${CommandType.SHOW_WANTED_PEOPLE}&${AppConstants.ONLY}=found">
                            Found
                        </a>
                    </div>
                </div>
                <div class="navbar-item has-dropdown is-hoverable">
                    <a class="navbar-link">
                        <my:lang key="requests"/>
                    </a>
                    <div class="navbar-dropdown">
                        <a class="navbar-item" href="?${AppConstants.COMMAND}=${CommandType.SHOW_REQUEST_LIST}">
                            All
                        </a>
                        <a class="navbar-item"
                           href="?${AppConstants.COMMAND}=${CommandType.SHOW_REQUEST_LIST}&${AppConstants.ONLY}=${AppConstants.STATUS_APPROVED}">
                            Relevant
                        </a>
                        <a class="navbar-item"
                           href="?${AppConstants.COMMAND}=${CommandType.SHOW_REQUEST_LIST}&${AppConstants.ONLY}=${AppConstants.STATUS_COMPLETED}">
                            Completed
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="navbar-menu">
            <div class="navbar-end">
				<span class="navbar-item">
                    <input id="en" class="button is-light" type="button" value="<my:lang key="en"/>"
                           onClick="changeLang('en')">
                </span>
                <span class="navbar-item">
                    <input id="ru" class="button is-light" type="button" value="<my:lang key="ru"/>"
                           onClick="changeLang('ru')">
                </span>
            </div>
        </div>
        <div class="navbar-tabs">
            <c:if test="${empty sessionScope.get(AppConstants.SESSION_USER)}">
            <span class="navbar-item">
                <a href="?${AppConstants.COMMAND}=${CommandType.SHOW_REGISTRATION_PAGE}"><my:lang key="registration"/></a>
            </span>
                <span class="navbar-item">
                <a href="?${AppConstants.COMMAND}=${CommandType.SHOW_AUTHENTICATION_PAGE}"><my:lang key="log_in"/></a>
            </span>
            </c:if>
            <c:if test="${not empty sessionScope.get(AppConstants.SESSION_USER)}">
            <span class="navbar-item">
                <a href="?${AppConstants.COMMAND}=${CommandType.SHOW_PERSONAL_PAGE}">
                    <c:out value="${sessionScope.get(AppConstants.SESSION_USER).login}"/>
                </a>
            </span>
                <span class="navbar-item">
                <a href="?${AppConstants.COMMAND}=${CommandType.LOG_OUT_USER}">
                    <my:lang key="log_out"/>
                </a>
                </span>
            </c:if>
        </div>
    </div>
    <script type="text/javascript">
        window.onload = function () {
            document.getElementById('${text.getLocaleTag()}').setAttribute("disabled","disabled")}

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
