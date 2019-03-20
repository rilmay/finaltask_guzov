<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<jsp:directive.attribute name="type" description="button class"/>
<jsp:directive.attribute name="label" required="true" description="label"/>
<jsp:directive.attribute name="command" required="true" description="command"/>
<jsp:directive.attribute name="id" description="id"/>

<c:set value="b0" var="element"/>
<c:if test="${not empty id}">
    <div class="is-hidden">${element = id}</div>
</c:if>

<c:set value="is-info" var="style"/>
<c:if test="${not empty type}">
    <div class="is-hidden">${style = type}</div>
</c:if>

<input type="hidden" name="command" value="${command}">
<button class="button is-block is-normal is-fullwidth ${style}" type="submit" onclick="loading('${element}')">
    <div id="${element}"></div> ${label}
</button>
