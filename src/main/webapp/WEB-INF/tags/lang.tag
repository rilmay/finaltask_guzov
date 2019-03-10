<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<jsp:directive.attribute name="key" required="true" description="key"/>
<c:set value="???${key}???" var="notInMap"/>
<c:if test="${not empty key}">
    <c:choose>
        <c:when test="${text[key] eq notInMap}">
            ${key}
        </c:when>
        <c:otherwise>
            <c:out value="${text[key]}"/>
        </c:otherwise>
    </c:choose>
</c:if>

