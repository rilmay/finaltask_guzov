<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<jsp:directive.attribute name="key" required="true" description="key"/>

<c:if test="${not empty key}">
    <c:if test="${not empty text[key]}">
        <c:out value="${text[key]}"/>
    </c:if>
    <c:if test="${empty text[key]}">
        <c:out value="key"/>
    </c:if>
</c:if>

