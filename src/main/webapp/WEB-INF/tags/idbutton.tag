<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<jsp:directive.attribute name="command" required="true" description="command"/>
<jsp:directive.attribute name="id" required="true" description="id"/>
<jsp:directive.attribute name="label" required="true" description="label"/>

<form action="${pageContext.request.contextPath}/" method="post">
    <input type="hidden" name="${AppConstants.COMMAND}" value="${command}">
    <input type="hidden" name="${AppConstants.ID}" value="${id}">
    <input class="button is-danger" type="submit" value="${label}">
</form>
